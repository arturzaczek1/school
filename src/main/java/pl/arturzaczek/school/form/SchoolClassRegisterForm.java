package pl.arturzaczek.school.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SchoolClassRegisterForm {
    @NotBlank (message = "can't be empty")
    @Size(min = 5, message = "at least 5 characters")
    private String classProfile;
    @NotBlank (message = "can't be empty")
    @Size (max=5, message = "max 5 characters")
    private String className;
}
