package help.got.validators;

import help.got.data.TouristRepository;
import help.got.model.SignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class SignUpValidator implements Validator {

    private TouristRepository touristRepository;

    @Autowired
    public SignUpValidator(TouristRepository repo) {
        touristRepository = repo;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SignUp.class.equals(aClass);
    }

    public void validate(Object object, Errors e) {
        var sign = (SignUp) object;
        if (!sign.getPassword().equals(sign.getRepeatedPassword())) {
            e.rejectValue("password", "pass.no.match");
        }

        var tourist = touristRepository.findByLogin(sign.getLogin());
        if (tourist != null) {
            e.rejectValue("login", "login.exists");
        }

        tourist = touristRepository.findByEmail(sign.getEmail());
        if (tourist != null) {
            e.rejectValue("email", "email.exists");
        }

    }
}
