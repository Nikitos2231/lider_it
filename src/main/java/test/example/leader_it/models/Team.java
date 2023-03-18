package test.example.leader_it.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "team")
@Data
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "sport_type")
    @Enumerated(value = EnumType.ORDINAL)
    private SportType sportType;

    @Column(name = "date_of_create")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfCreate;
}

