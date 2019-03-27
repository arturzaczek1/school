package pl.arturzaczek.school.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {
    @GetMapping(value = "/employee/login")
    public String getLoginForm(){
        return "employee/login-form";
    }

    @GetMapping(value = "employee/register")
    public String getRegisterForm (){
        return "employee/register-form";
    }
}
