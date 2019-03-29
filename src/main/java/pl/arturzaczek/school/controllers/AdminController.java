package pl.arturzaczek.school.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {

    @GetMapping("/admin/panel")
        public String goToAdminPanel (){
        return "admin/panel";
    }
}
