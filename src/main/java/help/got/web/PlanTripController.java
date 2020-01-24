package help.got.web;

import help.got.data.LineRepository;
import help.got.data.PointRepository;
import help.got.data.TripRepository;
import help.got.model.Line;
import help.got.model.Point;
import help.got.model.Trip;
import help.got.utils.ImageUtils;
import help.got.validators.TripValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import help.got.validators.TripValidator.Error;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/plan_trip")
public class PlanTripController {

	private PointRepository pointRepository;
	private LineRepository lineRepository;
	private TripRepository tripRepository;
	private TripValidator tripValidator;
	private Path imageDir =
			Paths.get("", "src", "main", "resources", "static", "images");
	private String DEFAULT_IMAGE_NAME = "map.png";

	@Autowired
	public PlanTripController(PointRepository pointRepository,
							  LineRepository lineRepository,
							  TripRepository tripRepository,
							  TripValidator tripValidator) {
	    this.pointRepository = pointRepository;
	    this.lineRepository = lineRepository;
		this.tripRepository = tripRepository;
	    this.tripValidator = tripValidator;
	}

	@GetMapping
	public String showMap() {
		return "trip_planning";
	}

	@RequestMapping(value = "/points/{pointId}", method = RequestMethod.GET)
	public @ResponseBody List<Line> getNeighbours(@PathVariable("pointId") Long pointId) {
	    return lineRepository.getNeighbourPaths(pointId);
	}

	@RequestMapping(value = "/points/all", method = RequestMethod.GET)
	public @ResponseBody List<Point> getAllPoints() {
	    return pointRepository.findAll();
	}

	@RequestMapping(value = "/lines/all", method = RequestMethod.GET)
	public @ResponseBody List<Line> getAllLines() {
	    return lineRepository.findAll();
	}

	@RequestMapping(value = "/save_trip", method = RequestMethod.POST)
	public @ResponseBody List<Error> saveTrip(@RequestBody Trip trip) {
	    var errors = tripValidator.validate(trip);
	    if (errors.size() == 0) {
	        tripRepository.save(trip);
		}
	    return errors;
	}

	@RequestMapping(value = "/points/map", method = RequestMethod.GET)
	public @ResponseBody String getMap() {
		var imageFile = Paths.get(imageDir.toString(), DEFAULT_IMAGE_NAME).toFile();
		try {
			var imageBytes = ImageUtils.getImageAsBytes(imageFile);
			return Base64.encodeBase64String(imageBytes);
		} catch (IOException e) {
			return e.getMessage();
		}
	}
}
