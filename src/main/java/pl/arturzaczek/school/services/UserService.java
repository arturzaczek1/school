package pl.arturzaczek.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.arturzaczek.school.entitis.User;
import pl.arturzaczek.school.entitis.Role;
import pl.arturzaczek.school.forms.UserRegisterForm;
import pl.arturzaczek.school.repositories.UserRepository;
import pl.arturzaczek.school.repositories.RoleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

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
        Optional<User> userOptional = userRepository.findFirstByEmail(email);
        if(userOptional.isPresent()){
            return true;
        }
        return false;
    }
    private void getORCreateDefaultRole(User user) {
        Role role = roleRepository.findByRoleName(Roles.ROLE_USER.toString())
                .orElseGet(() -> roleRepository.save(new Role(Roles.ROLE_USER.toString())));
        user.addRole(role);
    }
    public List<User> getUserList(){
        List<User> users = userRepository.findAll();
        return users;
    }

}
