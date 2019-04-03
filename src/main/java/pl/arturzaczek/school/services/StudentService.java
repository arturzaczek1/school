package pl.arturzaczek.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturzaczek.school.entitis.Role;
import pl.arturzaczek.school.entitis.User;
import pl.arturzaczek.school.forms.StudentForm;
import pl.arturzaczek.school.repositories.RoleRepository;
import pl.arturzaczek.school.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class StudentService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public StudentService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void registerStudent (StudentForm studentForm){
        User user = new User();
        user.setAddedDate(LocalDateTime.now());
        user.setName(studentForm.getStudentName());
        user.setLastName(studentForm.getStudentLastName());
        user.setEmail(studentForm.getStudentEmail());
        user.setBirthDate(studentForm.getBirthDate());
        getORCreateStudentRole(user);
        userRepository.save(user);
    }
    public boolean checkIfStudentExist (String email){
        Optional<User> userOptional = userRepository.findFirstByEmail(email);
        if(userOptional.isPresent()){
            return true;
        }
        return false;
    }
    private void getORCreateStudentRole(User user) {
        Role role = roleRepository.findByRoleName(Roles.STUDENT.getRole())
                .orElseGet(() -> roleRepository.save(new Role(Roles.STUDENT.getRole())));
        user.addRole(role);
    }
}
