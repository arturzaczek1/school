package pl.arturzaczek.school.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.arturzaczek.school.services.RoleService;


@Controller
public class AdminController {

    private RoleService roleService;

    @Autowired
    public AdminController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/admin/panel")
        public String goToAdminPanel (){
        return "admin/panel";
    }
    @GetMapping("/admin/roles")
    public String goToRoleManagement (Model model){
        model.addAttribute("roles", roleService.getRolesList());
        return "admin/role-management";
    }
}
