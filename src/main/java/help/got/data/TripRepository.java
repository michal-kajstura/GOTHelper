package help.got.data;

import help.got.model.Trip;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TripRepository extends CrudRepository<Trip, Long> {
    List<Trip> findByName(String name);
}
