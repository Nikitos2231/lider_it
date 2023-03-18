package test.example.leader_it.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import test.example.leader_it.annotations.DatePattern;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class TeamFilterRequest {

    @Pattern(regexp = "^(\\d+)$|^(%)$", message = "sport type should be figure: 0,1,2....")
    private String sportType = "%";

    @DatePattern(message = "invalid date")
    private String startDate = "1900-03-03";

    @DatePattern(message = "invalid date")
    private String endDate = "3000-03-03";

    @Pattern(regexp = "[1-9]\\d*", message = "page should be grater than 0")
    private String page = "1";

    @Pattern(regexp = "[1-9]\\d*", message = "max count should be grater than 0")
    private String maxCount = "20";
}
