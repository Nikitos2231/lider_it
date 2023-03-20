package test.example.leader_it.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import test.example.leader_it.annotations.StringDatePattern;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@ToString
public class TeamFilterRequest {

    @Pattern(regexp = "^(\\d+)$|^(%)$", message = "sport type should be figure: 0,1,2....")
    private String sportType = "%";

    @StringDatePattern(message = "invalid date")
    private String startDate = "1200-03-03";

    @StringDatePattern(message = "invalid date")
    private String endDate = "3000-03-03";

    @Pattern(regexp = "[1-9]\\d*", message = "page should be grater than 0")
    private String page = "1";

    @Pattern(regexp = "[1-9]\\d*", message = "max count should be grater than 0")
    private String maxCount = "20";
}
