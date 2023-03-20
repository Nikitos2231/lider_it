package test.example.leader_it.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import test.example.leader_it.annotations.StringDatePattern;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class PlayerDTO {

    @Size(min = 2, max = 100, message = "name should be from 3 to 100 characters size")
    @NotNull(message = "name can't be null")
    private String name;

    @Size(min = 2, max = 100, message = "surname should be from 3 to 100 characters size")
    @NotNull(message = "surname can't be null")
    private String surname;

    @Size(min = 2, max = 100, message = "patronymic should be from 3 to 100 characters size")
    @NotNull(message = "patronymic can't be null")
    private String patronymic;

    @NotNull(message = "dateOfBirth can't be null")
    @NotEmpty(message = "dateOfBirth can't be empty")
    @StringDatePattern(message = "dateOfBirth should be valid")
    private String dateOfBirth;

    @NotNull(message = "roleOrPosition can't be null")
    @NotEmpty(message = "roleOrPosition can't be empty")
    private String roleOrPosition;
}
