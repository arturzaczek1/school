package pl.arturzaczek.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.arturzaczek.school.forms.SchoolClassRegisterForm;
import pl.arturzaczek.school.services.SchoolClassService;
import pl.arturzaczek.school.services.TeachersService;

import javax.validation.Valid;

@Controller
public class SchoolClassController {

    private SchoolClassService schoolClassService;
    private TeachersService teachersService;

    @Autowired
    public SchoolClassController(SchoolClassService schoolClassService, TeachersService teachersService) {
        this.schoolClassService = schoolClassService;
        this.teachersService = teachersService;
    }

    @GetMapping("/s-c/management")
    public String goToSchoolClassManagement(Model model) {
        model.addAttribute("schoolClass", schoolClassService.getAllSC());
        model.addAttribute("teachers", teachersService.getTeachersList());
        return "school-class/s-c-management";
    }

    @GetMapping("/s-c/new-class-form")
    public String getNewClassForm(Model model) {
        model.addAttribute("class_form", new SchoolClassRegisterForm());
        return "school-class/new-class-form";
    }

    @PostMapping("/s-c/register/new")
    public String addSchoolClass(@Valid @ModelAttribute SchoolClassRegisterForm scRegisterForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("class_form", scRegisterForm);
            return "school-class/new-class-form";
        }
        String info = schoolClassService.registerSC(scRegisterForm);
        model.addAttribute("schoolClass", schoolClassService.getAllSC());
        model.addAttribute("info", info);
        return "/school-class/s-c-management";
    }
    @PostMapping("/c-s/teacher/add")
    public String connectTeacherWithSchoolClass(@RequestParam String add_educator, @RequestParam String school_class_id, Model model){
        String info = teachersService.addTeacherToClass(add_educator, school_class_id);
        model.addAttribute("info", info);
        model.addAttribute("schoolClass", schoolClassService.getAllSC());
        return "/school-class/s-c-management";
    }
}
