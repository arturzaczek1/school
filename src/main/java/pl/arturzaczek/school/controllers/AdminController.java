package pl.arturzaczek.school.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.arturzaczek.school.services.RoleService;
import pl.arturzaczek.school.services.UserService;


@Controller
public class AdminController {

    private RoleService roleService;
    private UserService userService;

    @Autowired
    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/admin/panel")
        public String goToAdminPanel (){
        return "admin/panel";
    }
    @GetMapping("/admin/roles")
    public String goToRoleManagement (Model model){
        model.addAttribute("roles", roleService.getRolesList());
        model.addAttribute("users", userService.getUserList());
        return "admin/role-management";
    }
}
