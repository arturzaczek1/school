package pl.arturzaczek.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturzaczek.school.entitis.Post;
import pl.arturzaczek.school.entitis.User;
import pl.arturzaczek.school.form.PostRegisterForm;
import pl.arturzaczek.school.repositories.PostRepository;
import pl.arturzaczek.school.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private UserContextService userContextService;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, UserContextService userContextService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.userContextService = userContextService;
    }

    public void saveNewPost (PostRegisterForm postRegisterForm){
        Post post =  new Post();
        User user = new User();
        Optional <User> userOptional = userRepository.findFirstByEmail(userContextService.getLoggedAs());
        if(userOptional.isPresent()){
            user = userOptional.get();
        }else {
            user.setEmail("unknown");
        }
        post.setAddedDate(LocalDateTime.now());
        post.setPostAuthor(user);
        post.setPostTitle(postRegisterForm.getPostTitle());
        post.setPostBody(postRegisterForm.getPostBody());
        postRepository.save(post);
    }
    public List<Post> getPostList (){
        List<Post> posts = postRepository.findAll();
        posts.sort(Post::compareTo);
        return posts;
    }
    public void deleteOnePost (Long id){
        postRepository.deleteById(id);
    }
}
