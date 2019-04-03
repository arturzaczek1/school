package pl.arturzaczek.school.dtos;

import lombok.*;
import pl.arturzaczek.school.entitis.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private LocalDateTime addedDate;
    private String name;
    private String lastName;
    private String email;
    private Set<Role> roleSet;
    private LocalDate birthDate;

}
