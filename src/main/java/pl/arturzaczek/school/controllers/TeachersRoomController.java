package pl.arturzaczek.school.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeachersRoomController {
    @GetMapping ("/teacherRoom")
    public String goToTeachersRoom(){
        return "teacher/teachers-room";
    }
}
