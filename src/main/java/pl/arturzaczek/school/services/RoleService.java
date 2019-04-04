package pl.arturzaczek.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturzaczek.school.entitis.Role;
import pl.arturzaczek.school.entitis.User;
import pl.arturzaczek.school.repositories.RoleRepository;
import pl.arturzaczek.school.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public List<Roles> getRolesList() {
        List<Roles> rolesList = Arrays.asList(Roles.values());
        return rolesList;
    }

    private Role checkIfRoleExistOrAddDefaultRole(String roles) {
        Optional<Role> roleName = roleRepository.findByRoleName(roles);
        if (!roleName.isPresent()) {
            Role role = new Role(roles);
            roleRepository.save(role);
            return role;
        }
        return roleName.get();
    }

    private boolean checkIfRoleExist(String roles) {
        Optional<Role> roleName = roleRepository.findByRoleName(roles);
        return roleName.isPresent();
    }

    private boolean checkIfUserHasRole(Long id, String roles) {
        User user = userRepository.findById(id).get();
        return user.getRoleSet().stream().anyMatch(a -> equals(roles));
    }

    public String addNewRole(String id, String roles) {
        Role role = checkIfRoleExistOrAddDefaultRole(roles);
        Long longId = Long.parseLong(id);
        Optional<User> optionalUser = userRepository.findById(longId);
        if (optionalUser.isPresent()) {
            if (!checkIfUserHasRole(longId, roles)) {
                User user = optionalUser.get();
                user.getRoleSet().add(role);
                userRepository.save(user);
                return "role added successfully";
            }
            return "user has same role";
        }
        return "user does't exist";
    }

    public String removeRole(String id, String roles) {
        Long roleId = Long.parseLong(roles);
        Optional<Role> roleToDelete = roleRepository.findById(roleId);
        Long userId = Long.parseLong(id);
        if (checkIfRoleExist(roles)) {
            return "role does't exist";
        }
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.getRoleSet().remove(roleToDelete.get());
            userRepository.save(user);
            return "role removed successfully";
        }
        return "user does't exist";
    }
}
