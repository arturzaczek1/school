package pl.arturzaczek.school.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.arturzaczek.school.form.SchoolClassRegisterForm;

import javax.validation.Valid;

@Controller
public class SchoolClassController {
    @GetMapping ("/s-c/management")
    public String goToSchoolClassManagement(Model model){
        return "school-class/s-c-management";
    }
    @GetMapping ("/s-c/new-class-form")
    public String getNewClassForm(Model model){
        model.addAttribute("class_form", new SchoolClassRegisterForm());
        return "school-class/new-class-form";
    }
    @PostMapping("/s-c/register/newClass")
    public String addSchoolClass (@Valid @ModelAttribute SchoolClassRegisterForm scRegisterForm, BindingResult bindingResult, Model model){
       if(bindingResult.hasErrors()){
           model.addAttribute("class_form", scRegisterForm);
           return "school-class/new-class-form";
       }
        System.out.println("class added");//TODO
        return "/school-class/s-c-management";
    }

}