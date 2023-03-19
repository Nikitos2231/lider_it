package test.example.leader_it.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import test.example.leader_it.converters.DateConverter;

import javax.persistence.*;

@Entity
@Table(name = "player")
@Data
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "date_of_birth")
    @Convert(converter = DateConverter.class)
    private String dateOfBirth;

    @Column(name = "role_or_position")
    private String roleOrPosition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

}

