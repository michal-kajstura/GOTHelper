package help.got.model;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SignUp {

    @Size(min=5, message="Login jest zbyt krótki")
    private String login;

    @Size(min=5, message="Email jest zbyt krótki")
    @Pattern(regexp="[^@]+@[^\\.]+\\..+", message="Email nie pasuje do wzorca")
    private String email;

    @Size(min=5, message="Hasło jest zbyt krótkie")
    private String password;

    private String repeatedPassword;
}
