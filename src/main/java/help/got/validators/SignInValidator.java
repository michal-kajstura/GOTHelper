package help.got.validators;

import help.got.data.TouristRepository;
import help.got.model.SignIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class SignInValidator implements Validator {

    private TouristRepository touristRepository;

    @Autowired
    public SignInValidator(TouristRepository repo) {
        touristRepository = repo;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SignIn.class.equals(aClass);
    }

    public void validate(Object object, Errors e) {
        var signIn = (SignIn) object;
        var tourist = touristRepository.findByLogin(signIn.getLogin());

		if (tourist == null) {
		    e.rejectValue("login", "no.user.login");
		    return;
		}

		if (! signIn.getPassword().equals(tourist.getPassword())) {
		    e.rejectValue("password", "wrong.password");
		}
    }
}

