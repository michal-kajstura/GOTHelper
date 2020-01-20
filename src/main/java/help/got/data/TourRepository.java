package help.got.data;

import help.got.model.Tour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends CrudRepository<Tour, Long> {
    List<Tour> findAll();
    Tour findTourByIdT(Long idT);
    Tour findTourByName(String name);
}
