package pl.arturzaczek.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.arturzaczek.school.forms.StudentForm;
import pl.arturzaczek.school.services.SchoolClassService;
import pl.arturzaczek.school.services.StudentService;

import javax.validation.Valid;

@Controller
public class StudentController {

    private StudentService studentService;
    private SchoolClassService schoolClassService;

    @Autowired
    public StudentController(StudentService studentService, SchoolClassService schoolClassService) {
        this.studentService = studentService;
        this.schoolClassService = schoolClassService;
    }

    @GetMapping("/student/management")
    public String goToStudentManagement(Model model) {
        model.addAttribute("students", studentService.getStudentsList());
        model.addAttribute("classes", schoolClassService.getAllSC());
        return "student/students-management";
    }

    @GetMapping("/student/add")
    public String getNewStudentForm(Model model) {
        model.addAttribute("studentForm", new StudentForm());
        return "student/new-student-form";
    }

    @PostMapping("/student/register/new")
    public String registerNewStudent(@Valid @ModelAttribute StudentForm studentform, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("studentForm", studentform);
            model.addAttribute("info", "The form contains errors");
            return "student/new-student-form";
        }
        String info = studentService.registerStudent(studentform);
        model.addAttribute("students", studentService.getStudentsList());
        model.addAttribute("info", info);
        return "student/students-management";
    }

    @PostMapping("/student/s-c/add")
    public String connectStudentWithSchoolClass(@RequestParam String add_class, @RequestParam String student_id, Model model) {
        String info = studentService.connectWithSchoolClass(student_id, add_class);
        model.addAttribute("info", info);
        model.addAttribute("classes", schoolClassService.getAllSC());
        return "student/students-management";
    }
}
