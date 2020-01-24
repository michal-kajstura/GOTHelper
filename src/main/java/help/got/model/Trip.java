package help.got.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "wycieczki")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTrip;

    private String name;
    @ManyToMany
    @JoinTable(
            name = "wycieczki_odcinki",
            joinColumns = { @JoinColumn(name = "id_trip")}
    )
    private List<Line> path;
}
