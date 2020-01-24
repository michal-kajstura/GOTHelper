package help.got.validators;

import help.got.data.TouristRepository;
import help.got.model.SignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * This class is used for tourist sign up validation
 */
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

    /**
     * @param object SignUp object to be validated
     * @param e an object containing errors
     */
    public void validate(Object object, Errors e) {
        var sign = (SignUp) object;

        if (!sign.getPassword().equals(sign.getRepeatedPassword())) {
            e.rejectValue("password", "pass.no.match");
        }

        var loginIsValid = validateLogin(sign.getLogin());
        if (!loginIsValid) {
            e.rejectValue("login", "login.invalid");
        } else {
            var loginExist = fieldExist(sign.getLogin(), touristRepository::findByLogin);
            if (loginExist) {
                e.rejectValue("login", "login.exists");
            }
        }

        var emailIsValid = validateEmail(sign.getEmail());
        if (!emailIsValid) {
            e.rejectValue("email", "email.incorrect");
        } else {
            var emailExists = fieldExist(sign.getEmail(), touristRepository::findByEmail);
            if (emailExists) {
                e.rejectValue("email", "email.exists");
            }
        }
    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static Pattern IS_ALPHANUM = Pattern.compile("^[a-zA-Z0-9]*$");

    private static boolean validateEmail(String email) {
        var matcher  = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.matches();
    }


    private static boolean validateLogin(String login) {
        if (login.length() == 0) {
            return false;
        }
        var matcher = IS_ALPHANUM.matcher(login);
        return matcher.matches();
    }

    private static <T, R> boolean fieldExist(T query, Function<T, R> repoMethod)  {
        var result = repoMethod.apply(query);
        return result != null;
    }
}
