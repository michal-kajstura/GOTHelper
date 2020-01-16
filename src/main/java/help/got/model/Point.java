package help.got.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="punkty")
@NoArgsConstructor
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idP;

    @Column(name="nazwa")
    private String name;

    @NonNull
    private double lat;

    @NonNull
    private double lon;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "odcinki",
        joinColumns = { @JoinColumn(name = "punkt2") },
        inverseJoinColumns = { @JoinColumn(name = "punkt1") }
    )
    private List<Point> neighbours;
}
