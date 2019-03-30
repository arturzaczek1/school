package pl.arturzaczek.school.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleService {

    public List<Roles> getRolesList(){
        List<Roles> rolesList = Arrays.asList(Roles.values());
        return rolesList;
    }
}
