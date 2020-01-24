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
class SignUpEmailTest {

    @Mock
    TouristRepository repo = Mockito.mock(TouristRepository.class);

    @Mock
    SignUp signUp = Mockito.mock(SignUp.class);

    SignUpValidator validator;

    @BeforeEach
    public void prepareSignup() {
        Mockito.when(repo.findByLogin("jacekplacek123")).thenReturn(null);
        Mockito.when(repo.findByEmail("jace2k@plac.ek")).thenReturn(null);
        Mockito.when(signUp.getLogin()).thenReturn("jacekPlacek123");
        Mockito.when(signUp.getPassword()).thenReturn("matching");
        Mockito.when(signUp.getRepeatedPassword()).thenReturn("matching");
        validator = new SignUpValidator(repo);
    }

    @Test
    void validateEmailValid() {
        Mockito.when(signUp.getEmail()).thenReturn("jacek@plac.ek");
        var errors = new BeanPropertyBindingResult(signUp, "objectName");
        validator.validate(signUp, errors);
        assertEquals(errors.getAllErrors().size(), 0);
    }

    @Test
    void validateEmailExists() {
        Mockito.when(repo.findByEmail("jacek@plac.ek")).thenReturn(new Tourist());
        Mockito.when(signUp.getEmail()).thenReturn("jacek@plac.ek");
        var errors = new BeanPropertyBindingResult(signUp, "objectName");
        validator.validate(signUp, errors);
        var errorsList = errors.getAllErrors();
        if (errorsList.size() != 0) {
            var fe = (FieldError) errorsList.get(0);
            assertEquals(fe.getField(), "email");
        } else {
            fail("Is empty");
        }
    }

    @Test
    void validateEmailIncorrect() {
        Mockito.when(repo.findByEmail("jacek@plac.ek")).thenReturn(new Tourist());
        Mockito.when(signUp.getEmail()).thenReturn("jacek@pl@ac.ek");
        var errors = new BeanPropertyBindingResult(signUp, "objectName");
        validator.validate(signUp, errors);
        var errorsList = errors.getAllErrors();
        if (errorsList.size() != 0) {
            var fe = (FieldError) errorsList.get(0);
            assertEquals(fe.getField(), "email");
        } else {
            fail("Is empty");
        }
    }
}
