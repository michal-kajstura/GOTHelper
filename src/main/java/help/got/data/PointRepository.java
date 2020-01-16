package help.got.data;

import help.got.model.Point;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PointRepository extends CrudRepository<Point, Long> {
    Point findPointByIdP(Long IdP);

    List<Point> findAll();
}
