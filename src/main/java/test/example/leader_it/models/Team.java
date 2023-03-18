package test.example.leader_it.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "team")
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
    private LocalDate dateOfCreate;

    public Team() {
    }

    public LocalDate getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDate dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }
}

