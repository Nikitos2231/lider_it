package test.example.leader_it.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import test.example.leader_it.annotations.SportTypeChecker;
import test.example.leader_it.annotations.StringDatePattern;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class TeamDTO {

    @NotNull(message = "team name can't be null")
    @NotEmpty(message = "team name can't be empty")
    @Size(min = 3, max = 100, message = "the size of team name must contain from 3 to 100 characters ")
    private String teamName;

    @NotNull(message = "type of sport shouldn't be null")
    @NotEmpty(message = "sport type can't be empty")
    @SportTypeChecker(message = "invalid type of sport, please choose one of the follow variants: BASKETBALL, FOOTBALL, VOLLEYBALL")
    private String sportType;

    @NotNull(message = "date of create can't be null")
    @NotEmpty(message = "date of create can't be empty")
    @StringDatePattern(message = "invalid date")
    private String dateOfCreate;
}

