package help.got.model;

import java.util.List;

public class Path {
    private List<Point> points;

    public void addPoint(Point point) {
        points.add(point);
    }

    public List<Point> getNeighbours() {
        var point = points.get(points.size() - 1);
        return point.getNeighbours();
    }
}
