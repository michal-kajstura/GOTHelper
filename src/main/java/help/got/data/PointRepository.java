package help.got.data;

import help.got.model.Line;
import help.got.model.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointRepository extends CrudRepository<Point, Long> {
    Point findPointByIdP(Long IdP);

    List<Point> findAll();

    @Query(value = "SELECT * FROM punkty p " +
            "WHERE p.idP IN (SELECT o.punkt1 FROM odcinki o WHERE o.punkt2 = :idP OR o.punkt1 = :idP)", nativeQuery = true)
    List<Point> getNeighbours(@Param("idP") Long idP);

    @Query(value = "SELECT * FROM odcinki o WHERE o.punkt1 = :idP", nativeQuery = true)
    List<Long> getNeighbourPaths(@Param("idP") Long idP);
}
