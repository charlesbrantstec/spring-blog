package com.example.blog.controllers;

import com.example.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.blog.repositories.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postRepo;

    public PostController(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

//TODO:    GET	/posts	        posts index page
//         GET	/posts/{id}	    view an individual post
//         GET	/posts/create	view the form for creating a post
//         POST	/posts/create	create a new post

    @GetMapping("/posts")
//    @ResponseBody
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

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String createPost(@RequestParam(name = "title") String title,
                             @RequestParam(name = "body") String body,
                             Model model) {
        Post post = new Post();
        post.setTitle(title);
        post.setBody(body);
        postRepo.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/edit/{id}")
    public String editAd(@PathVariable long id, Model model) {
        Post post = postRepo.getPostById(id);
        model.addAttribute("post", post);
        return "ads/edit";
    }

    @PostMapping("/posts/edit")
    public String updateAd(@RequestParam(name = "id") long id,
                           @RequestParam(name = "title") String title,
                           @RequestParam(name = "body") String body) {
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setBody(body);
        postRepo.save(post);
        return "redirect:/ads/" + post.getId();
    }

}
