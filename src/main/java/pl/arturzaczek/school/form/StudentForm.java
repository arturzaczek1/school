package pl.arturzaczek.school.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Setter
@Getter
public class StudentForm {
    @NotBlank
    private String studentName;
    @NotBlank
    private String studentLastName;
    @Email(message = "email example: example@gmail.com")
    private String studentEmail;
    @Past
    private LocalDate birthDate;
}
