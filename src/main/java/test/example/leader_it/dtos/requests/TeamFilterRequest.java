package test.example.leader_it.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamFilterRequest {

    private String sportType = "%";
    private String startDate = "1900-03-03";
    private String endDate = "3000-03-03";
    private String page = "1";
    private String maxCount = "20";
}
