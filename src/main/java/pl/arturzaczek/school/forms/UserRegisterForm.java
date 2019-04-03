package pl.arturzaczek.school.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class UserRegisterForm {
    @NotBlank
    private String formName;
    @NotBlank
    private String formLastName;
    @Email(message = "email example: example@gmail.com")
    private String email;
    @Size(min = 4, max = 20, message = "password should contain at least 4 characters")
    private String password;
}
