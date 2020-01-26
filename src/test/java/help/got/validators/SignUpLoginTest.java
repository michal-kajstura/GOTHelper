package help.got.validators;

import help.got.data.TouristRepository;
import help.got.model.SignUp;
import help.got.model.Tourist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(JUnit4.class)
class SignUpLoginTest {

    @Mock
    TouristRepository repo = Mockito.mock(TouristRepository.class);

    @Mock
    SignUp signUp = Mockito.mock(SignUp.class);

    SignUpValidator validator;

    @BeforeEach
    public void prepareSignup() {
        Mockito.when(repo.findByLogin("jacekplacek123")).thenReturn(null);
        Mockito.when(signUp.getEmail()).thenReturn("valid@email.com");
        Mockito.when(signUp.getPassword()).thenReturn("matching");
        Mockito.when(signUp.getRepeatedPassword()).thenReturn("matching");
        validator = new SignUpValidator(repo);
    }

    @Test
    void validateLoginValid() {
        Mockito.when(signUp.getLogin()).thenReturn("jacekplacek123");
        var errors = new BeanPropertyBindingResult(signUp, "objectName");
        validator.validate(signUp, errors);
        assertEquals(errors.getAllErrors().size(), 0);
    }

    @Test
    void validateLoginInvalid() {
        Mockito.when(signUp.getLogin()).thenReturn("jac;;ekpl@cek");
        var errors = new BeanPropertyBindingResult(signUp, "objectName");
        validator.validate(signUp, errors);
        var errorsList = errors.getAllErrors();
        if (errorsList.size() != 0) {
            var fe = (FieldError) errorsList.get(0);
            assertEquals(fe.getField(), "login");
        } else {
            fail("Is empty");
        }
    }

    @Test
    void validateLoginEmpty () {
        Mockito.when(signUp.getLogin()).thenReturn("");
        var errors = new BeanPropertyBindingResult(signUp, "objectName");
        validator.validate(signUp, errors);
        var errorsList = errors.getAllErrors();
        if (errorsList.size() != 0) {
            var fe = (FieldError) errorsList.get(0);
            assertEquals(fe.getField(), "login");
        } else {
            fail("Is empty");
        }
    }

    @Test
    void validateLoginAlreadyExists () {
        Mockito.when(repo.findByLogin("jacekplacek")).thenReturn(new Tourist());
        Mockito.when(signUp.getLogin()).thenReturn("jacekplacek");
        var errors = new BeanPropertyBindingResult(signUp, "objectName");
        validator.validate(signUp, errors);
        var errorsList = errors.getAllErrors();
        if (errorsList.size() != 0) {
            var fe = (FieldError) errorsList.get(0);
            assertEquals(fe.getField(), "login");
        } else {
            fail("Is empty");
        }
    }
}