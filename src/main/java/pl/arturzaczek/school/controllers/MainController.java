package pl.arturzaczek.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.arturzaczek.school.services.PostService;

@Controller
public class MainController {

    private PostService postService;

    @Autowired
    public MainController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping (value = {"/","/home","/index"}, method = RequestMethod.GET)
    public String getHome (Model model){
        model.addAttribute("posts", postService.getPostList());
        return "index";
    }
}
