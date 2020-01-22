package help.got.model;

import lombok.Data;

import java.util.List;

@Data
public class Trip {
    private String tripName;
    private List<List<Point>> path;
}
