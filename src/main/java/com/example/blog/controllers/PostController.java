package com.example.blog;

import com.example.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.blog.repositories.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

//TODO:    GET	/posts	        posts index page
//         GET	/posts/{id}	    view an individual post
//         GET	/posts/create	view the form for creating a post
//         POST	/posts/create	create a new post

    @GetMapping("/posts")
    @ResponseBody
    public String posts(Model model){
        List<Post> postList = new ArrayList<>();
        postList.add(new Post(0, "First Post", "This is the first post"));
        postList.add(new Post(0, "Second Post", "This is the second post"));
        model.addAttribute("posts", postList);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postsId(@PathVariable long id, Model model){
        Post post = new Post(0, "Single Post", "This is the body for the single post");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String postsCreateForm(){
        return "<h1>Here is where my form for creating a post would go if I had one.</h1>";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String postsCreate(){
        return "<h1>Here is where my form for creating a post would go if I had one.</h1>";
    }

    @PostMapping("posts/edit")
    @ResponseBody
    public void postsEdit(){

    }

    @PostMapping("posts/delete")
    @ResponseBody
    public void postsDelete(){

    }
}
