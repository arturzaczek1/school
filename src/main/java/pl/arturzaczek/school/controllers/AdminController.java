package pl.arturzaczek.school.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.arturzaczek.school.services.AdminService;
import pl.arturzaczek.school.services.RoleService;
import pl.arturzaczek.school.services.Roles;
import pl.arturzaczek.school.services.UserService;


@Controller
public class AdminController {

    private RoleService roleService;
    private UserService userService;
    private AdminService adminService;

    @Autowired
    public AdminController(RoleService roleService, UserService userService, AdminService adminService) {
        this.roleService = roleService;
        this.userService = userService;
        this.adminService = adminService;
    }

    @GetMapping("/admin/panel")
    public String goToAdminPanel() {
        return "admin/panel";
    }

    @GetMapping("/admin/roles")
    public String goToRoleManagement(Model model) {
        model.addAttribute("users", userService.getUserList());
        return "admin/role-management";
    }

    @PostMapping("/admin/role-management/user")
    public String goToChangeRole(@RequestParam String user_id, Model model) {
        Long longId = Long.parseLong(user_id);
        model.addAttribute("rolesEnum", Roles.values());
        model.addAttribute("roles", roleService.getRolesList());
        model.addAttribute("userDto", adminService.getUserDto(longId));
        return "admin/user";
    }

    @PostMapping("/admin/roles/new")
    public String addNewRole(@RequestParam(defaultValue = "ROLE_USER") String new_role, @RequestParam String user_id, Model model) {
        System.out.println(user_id + " " + new_role);
        String success = roleService.addNewRole(user_id, new_role);
        model.addAttribute("success", success);
        model.addAttribute("rolesEnum", Roles.values());
        model.addAttribute("roles", roleService.getRolesList());
        model.addAttribute("userDto", adminService.getUserDto(Long.parseLong(user_id)));
        return "admin/user";
    }

    @PostMapping("/admin/roles/remove")
    public String removeRole(@RequestParam String remove_role, @RequestParam String user_id, Model model) {
        System.out.println(user_id + " " + remove_role);
        String result = roleService.removeRole(user_id, remove_role);
        model.addAttribute("success", result);
        model.addAttribute("rolesEnum", Roles.values());
        model.addAttribute("roles", roleService.getRolesList());
        model.addAttribute("userDto", adminService.getUserDto(Long.parseLong(user_id)));
        return "admin/user";
    }
}
