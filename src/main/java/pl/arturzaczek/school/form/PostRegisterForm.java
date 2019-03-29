package pl.arturzaczek.school.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class PostRegisterForm {
    @NotBlank (message = "post title can't be empty")
    @Size (min = 5, message = "at least 5 characters")
    private String postTitle;
    @NotBlank (message = "post content can't be empty")
    @Size (min = 10 ,message = "at least 10 characters")
    private String postBody;
}
