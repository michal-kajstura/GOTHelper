package help.got.model;


import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class SignIn {
    @Size(min = 1, message = "Wpisz coś!")
    private String login;
    private String password;
}
