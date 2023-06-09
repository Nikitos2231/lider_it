package test.example.leader_it.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import test.example.leader_it.converters.DateConverter;

import javax.persistence.*;
import java.util.List;

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
    @Convert(converter = DateConverter.class)
    private String dateOfCreate;

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<Player> players;
}

