package help.got.data;
import help.got.model.Tourist;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface TouristRepository extends CrudRepository<Tourist, Long> {
    Tourist findByLogin(String login);
    Tourist findByEmail(String email);
}
