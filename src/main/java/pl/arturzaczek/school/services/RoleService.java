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

    public boolean addNewRole(String id, Roles roles) {
        Role role = checkIfRoleExistOrAddRole(roles);
        Long longId = Long.parseLong(id);
        Optional<User> optionalUser = userRepository.findById(longId);
        if (optionalUser.isPresent()) {
            if (!checkIfUserHasRole(longId, roles)) {
                optionalUser.get().getRoleSet().add(role);
                return true;
            }
        }
        return false;
    }

    private Role checkIfRoleExistOrAddRole(Roles roles) {
        Optional<Role> roleName = roleRepository.findByRoleName(roles.toString());
        if (!roleName.isPresent()) {
            Role role = new Role(roles.getRole());
            roleRepository.save(role);
            return role;
        }
        return roleName.get();
    }

    private boolean checkIfUserHasRole(Long id, Roles roles) {
        User user = userRepository.findById(id).get();
        return user.getRoleSet().stream().anyMatch(a -> equals(roles));
    }

}
