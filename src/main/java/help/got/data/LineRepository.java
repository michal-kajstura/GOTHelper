package help.got.data;

import help.got.model.Line;
import help.got.model.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LineRepository extends CrudRepository<Line, Long> {
    @Query(value = "SELECT * FROM odcinki o WHERE o.punkt_start = :idP", nativeQuery = true)
    List<Line> getNeighbourPaths(@Param("idP") Long idP);

    List<Line> findAll();
}
