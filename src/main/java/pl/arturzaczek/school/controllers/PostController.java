package pl.arturzaczek.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.arturzaczek.school.form.PostRegisterForm;
import pl.arturzaczek.school.services.PostService;

import javax.validation.Valid;

@Controller
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping ("post/register")
    public String getNewPostForm (Model model){
        model.addAttribute("postRegisterForm", new PostRegisterForm());
        return "post/new-post-form";
    }
    @PostMapping ("post/register/new")
    public String registerPost (@ModelAttribute @Valid PostRegisterForm postRegisterForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("postRegisterForm", postRegisterForm);
            return "post/new-post-form";
        }
        postService.saveNewPost(postRegisterForm);
        return "/index";
    }
}