package pl.arturzaczek.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.arturzaczek.school.entitis.User;
import pl.arturzaczek.school.entitis.Role;
import pl.arturzaczek.school.form.UserRegisterForm;
import pl.arturzaczek.school.repositories.UserRepository;
import pl.arturzaczek.school.repositories.RoleRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private static final String ROLE_USER = "ROLE_USER";

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser (UserRegisterForm userRegisterForm){
        User user = new User();
        user.setAddedDate(LocalDateTime.now());
        user.setName(userRegisterForm.getFormName());
        user.setLastName(userRegisterForm.getFormLastName());
        user.setEmail(userRegisterForm.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterForm.getPassword()));
        getORCreateDefaultRole(user);
        userRepository.save(user);
    }

    public boolean checkIfUserExist (String email){
        Optional<User> employeeOptional = userRepository.findFirstByEmail(email);
        if(employeeOptional.isPresent()){
            return true;
        }
        return false;
    }
    private void getORCreateDefaultRole(User user) {
        Role role = roleRepository.findByRoleName(ROLE_USER)
                .orElseGet(() -> roleRepository.save(new Role(ROLE_USER)));
        user.addRole(role);
    }

}
