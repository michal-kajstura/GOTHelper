package help.got.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="odcinki")
@NoArgsConstructor
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineId;

    @OneToOne(targetEntity = Point.class)
    @JoinColumn(name = "punkt_start")
    private Point startPoint;

    @OneToOne(targetEntity = Point.class)
    @JoinColumn(name = "punkt_koniec")
    private Point endPoint;
}
