package pl.arturzaczek.school.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping (value = {"/","/home","/index"}, method = RequestMethod.GET)
    public String getHome (){
        return "index";
    }
}
