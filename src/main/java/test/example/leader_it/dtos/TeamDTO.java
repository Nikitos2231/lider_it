package test.example.leader_it.dtos;

import test.example.leader_it.models.SportType;

import java.util.Date;

public class TeamDTO {

    private String teamName;
    private SportType sportType;
    private Date dateOfCreate;

    public TeamDTO() {
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

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }
}
