package pl.arturzaczek.school.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.arturzaczek.school.form.StudentForm;
import pl.arturzaczek.school.services.Roles;

import javax.validation.Valid;

@Controller
public class StudentController {
    @GetMapping ("/student/management")
    public String goToStudentManagement (){
        return "student/students-management";
    }
    @GetMapping("/student/add")
    public String getNewStudentForm (Model model){
        model.addAttribute("studentForm", new StudentForm());
        return "student/new-student-form";
    }
    @PostMapping ("/student/register/new")
    public String registerNewStudent (@Valid @ModelAttribute StudentForm studentform, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("studentForm", studentform);
            System.out.println(studentform.getBirthDate() + " " + studentform.getStudentEmail() + " " + studentform.getStudentName());
            return "student/new-student-form";
        }
        System.out.println(studentform.getBirthDate() + " " + studentform.getStudentEmail() + " " + studentform.getStudentName());
        return "student/students-management";
    }
}
