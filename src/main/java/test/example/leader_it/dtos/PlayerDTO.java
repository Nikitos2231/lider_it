package test.example.leader_it.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerDTO {

    private String name;

    private String surname;

    private String patronymic;

    private String dateOfBirth;

    private String roleOrPosition;
}
