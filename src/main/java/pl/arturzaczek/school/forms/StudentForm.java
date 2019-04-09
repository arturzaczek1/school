package pl.arturzaczek.school.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Setter
@Getter
public class StudentForm {
    @NotBlank
    private String studentName;
    @NotBlank
    private String studentLastName;
    @Email(message = "email example: example@gmail.com")
    private String studentEmail;
    @NotBlank
    private String birthDate;
}
