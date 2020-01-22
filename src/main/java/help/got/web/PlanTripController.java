package help.got.web;

import help.got.data.LineRepository;
import help.got.data.PointRepository;
import help.got.model.Line;
import help.got.model.Point;
import help.got.model.Trip;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/plan_trip")
public class PlanTripController {

	private PointRepository pointRepo;
	private LineRepository lineRepo;
	private Path imageDir =
			Paths.get("", "src", "main", "resources", "static", "images");
	private String DEFAULT_IMAGE_NAME = "map.png";

	@Autowired
	public PlanTripController(PointRepository repo, LineRepository lineRepo) {
		pointRepo = repo;
		this.lineRepo = lineRepo;
	}

	@GetMapping
	public String showMap(Model model) {
		return "trip_planning";
	}

	private byte[] getImageAsBytes() {

		var imageFile = Paths.get(imageDir.toString(), DEFAULT_IMAGE_NAME).toFile();
		byte[] imageBytes = null;

		try {
			var image = ImageIO.read(imageFile);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", baos);
			baos.flush();
			imageBytes = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageBytes;
	}

	@RequestMapping(value = "/points/{pointId}", method = RequestMethod.GET)
	public @ResponseBody List<Line> getNeighbours(@PathVariable("pointId") Long pointId) {
	    return lineRepo.getNeighbourPaths(pointId);
	}


	@RequestMapping(value = "/points/all", method = RequestMethod.GET)
	public @ResponseBody List<Point> getAllPoints() {
	    var points = pointRepo.findAll();
		return points;
	}

	@RequestMapping(value = "/lines/all", method = RequestMethod.GET)
	public @ResponseBody List<Line> getAllLines() {
	    return lineRepo.findAll();
	}

	@RequestMapping(value = "/save_trip", method = RequestMethod.POST)
	public @ResponseBody void saveTrip(@RequestBody Trip trip) {
		System.out.println(trip);
	}

	@RequestMapping(value = "/points/map", method = RequestMethod.GET)
	public @ResponseBody String getMap() {
		var imageBytes = getImageAsBytes();
		var imageBase64 = Base64.encodeBase64String(imageBytes);
		return imageBase64;

	}


}
