package test.example.leader_it.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class PlayerFilterRequest {

    @Pattern(regexp = "^(\\w+)$|^(%)$", message = "sport type should be figure: 0,1,2....")
    private String roleOrPosition = "%";

    @Pattern(regexp = "[1-9]\\d*", message = "page should be grater than 0")
    private String page = "1";

    @Pattern(regexp = "[1-9]\\d*", message = "max count should be grater than 0")
    private String maxCount = "20";
}
