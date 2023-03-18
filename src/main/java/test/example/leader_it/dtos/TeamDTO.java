package test.example.leader_it.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.example.leader_it.models.SportType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
public class TeamDTO {

    @NotNull(message = "team name shouldn't be null")
    @NotEmpty(message = "team name shouldn't be empty")
    @Size(min = 3, max = 100, message = "the size of team name must contain from 3 to 100 characters ")
    private String teamName;
    @NotNull(message = "type of sport shouldn't be null")
    private SportType sportType;
    private Date dateOfCreate;
}

