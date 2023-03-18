package test.example.leader_it.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import test.example.leader_it.models.SportType;

import java.util.Date;

@Data
@NoArgsConstructor
public class TeamDTO {

    private String teamName;
    private SportType sportType;
    private Date dateOfCreate;
}

