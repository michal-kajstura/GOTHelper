package help.got.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name="wycieczki")
//@NoArgsConstructor
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idT;

    @NonNull
    private String name;
    @NonNull
    private boolean status;
    @NonNull
    private Date startDate;
    @NonNull
    private Date endDate;
    @NonNull
    private float distance;
    @NonNull
    private int score;
    @NonNull
    private String track;  //Path type

    private String category;
    private String mountain;

}
