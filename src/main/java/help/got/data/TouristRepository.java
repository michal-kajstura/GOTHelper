package help.got.data;
import help.got.model.Tourist;
import org.springframework.data.repository.CrudRepository;


public interface TouristRepository extends CrudRepository<Tourist, Long> {
    Tourist findByLogin(String login);
    Tourist findByEmail(String email);
}
