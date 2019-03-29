package pl.arturzaczek.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
        return "/post/post-management";
    }
    @GetMapping ("/post/management")
    public String goToPostManagement (Model model){
        model.addAttribute("posts", postService.getPostList());
        return "/post/post-management";
    }
    @GetMapping ("/post/delete/{id}")
    public String deletePost (@PathVariable String id){
        long longId = Long.parseLong(id);
        postService.deleteOnePost(longId);
        return "/post/post-management";
    }
}