package help.got.model;

import java.util.List;

public class History {
    private List<Tour> tourList;
    public void addTour(Tour tour) {
        tourList.add(tour);
    }
}
