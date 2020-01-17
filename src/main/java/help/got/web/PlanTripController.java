package help.got.web;

import help.got.data.PointRepository;
import help.got.model.Drawer;
import help.got.model.Point;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/plan_trip")
public class PlanTripController {

	private PointRepository pointRepo;
	private Drawer drawer;
	private Path imageDir =
			Paths.get("", "src", "main", "resources", "static", "images");
	private String DEFAULT_IMAGE_NAME = "map.png";

	@Autowired
	public PlanTripController(PointRepository repo) {
		pointRepo = repo;
	}

	@GetMapping
	public String showMap(Model model) {
		var points = new ArrayList<Point>();
		Iterable<Point> pointIterable = pointRepo.findAll();
		pointIterable.forEach(points::add);

		var minLat = points.stream()
				.map(Point::getLat)
				.min(Comparator.naturalOrder())
				.get();
		var minLon = points.stream()
				.map(Point::getLon)
				.min(Comparator.naturalOrder())
				.get();

		for (var p : points) {
			p.setLat(p.getLat() - minLat);
			p.setLon(p.getLon() - minLon);
		}

		var imageBytes = drawPointsOnMap(points);
		var imageBase64 = Base64.encodeBase64String(imageBytes);
		model.addAttribute("img", imageBase64);
		model.addAttribute("points", points);


		return "trip_planning";
	}

	private byte[] drawPointsOnMap(List<Point> points) {

		var imageFile = Paths.get(imageDir.toString(), DEFAULT_IMAGE_NAME).toFile();
		byte[] imageBytes = null;

		try {
			var image = ImageIO.read(imageFile);
			drawer = new Drawer(image, Color.BLUE, 5);
			drawPoints(points);

			var p = points.get(0);
			var scaledLon = (int) (p.getLon() * 22000) + 270;
			var scaledLat = 550 - (int) (p.getLat() * 22000);

			drawer.setColor(Color.RED);
			drawer.drawPoint(scaledLon, scaledLat, 10);
			imageBytes = drawer.getImageAsBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageBytes;
	}

	private void drawPoints(List<Point> points) {
		for (var p: points) {
			var scaledLon = (int) (p.getLon() * 25000) + 270;
			var scaledLat = 550 - (int) (p.getLat() * 22000);
			drawer.drawPoint(scaledLon, scaledLat, 5);
			drawer.drawText(p.getName(), scaledLon, scaledLat);
		}
	}

	@RequestMapping(value = "/points/{pointId}", method = RequestMethod.GET)
	public @ResponseBody List<Point> getNeighbours(@PathVariable("pointId") Long pointId) {
	    var pointOpt = pointRepo.findById(pointId);
	    if (pointOpt.isPresent()) {
	    	var p = (Point) pointOpt.get();
	    	return p.getNeighbours();
		} else {
	        return Collections.emptyList();
		}
	}

	@RequestMapping(value = "/points/all", method = RequestMethod.GET)
	public @ResponseBody List<Point> getAllPoints() {
	    return pointRepo.findAll();
	}

}
