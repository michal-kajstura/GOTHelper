package help.got.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="turysci")
public class Tourist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idT;
    private String login;
    private String email;
    private String password;
}
