package help.got.web;

import help.got.data.PointRepository;
import help.got.model.Drawer;
import help.got.model.Point;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/plan_trip")
public class PlanTripController {

	private CrudRepository pointRepo;
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

		drawPointsOnMap(points);

		model.addAttribute("img_filename", "map_temp.png");

		return "trip_planning";
	}

	private void drawPointsOnMap(List<Point> points) {
		var imageFile = Paths.get(imageDir.toString(), DEFAULT_IMAGE_NAME).toFile();

		try {
			var image = ImageIO.read(imageFile);
			drawer = new Drawer(image, Color.BLUE, 5);
			drawPoints(points);
			var outPath = Paths.get(imageDir.toString(), "map_temp.png");

			var p = points.get(0);
			var scaledLon = (int) (p.getLon() * 22000) + 270;
			var scaledLat = 550 - (int) (p.getLat() * 22000);

			drawer.setColor(Color.RED)
			drawer.drawPoint(scaledLon, scaledLat, 10);
			drawer.saveFile(outPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void drawPoints(List<Point> points) {
		for (var p: points) {
			var scaledLon = (int) (p.getLon() * 25000) + 270;
			var scaledLat = 550 - (int) (p.getLat() * 22000);
			drawer.drawPoint(scaledLon, scaledLat, 5);
		}
	}


}
