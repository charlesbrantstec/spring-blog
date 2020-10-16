package com.example.blog.controllers;

import com.example.blog.models.Post;
import com.example.blog.repositories.UserRepository;
import com.example.blog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.blog.repositories.PostRepository;
import com.example.blog.models.User;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

//    private final PostRepository postRepo;
//
//    public PostController(PostRepository postRepo) {
//        this.postRepo = postRepo;
//    }
//
////TODO:    GET	/posts	        posts index page
////         GET	/posts/{id}	    view an individual post
////         GET	/posts/create	view the form for creating a post
////         POST	/posts/create	create a new post
//
//    @GetMapping("/posts")
////    @ResponseBody
//    public String posts(Model model){
//        List<Post> postList = new ArrayList<>();
//        postList.add(new Post(0, "First Post", "This is the first post"));
//        postList.add(new Post(0, "Second Post", "This is the second post"));
//        model.addAttribute("posts", postList);
//        return "posts/index";
//    }
//
//    @GetMapping("/posts/{id}")
//    public String postsId(@PathVariable long id, Model model){
//        Post post = new Post(0, "Single Post", "This is the body for the single post");
//        model.addAttribute("post", post);
//        return "posts/show";
//    }
//
//    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
//    public String createPostForm(Model model) {
//        model.addAttribute("post", new Post());
//        return "posts/create";
//    }
//
//    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
//    public String createPost(@RequestParam(name = "title") String title,
//                             @RequestParam(name = "body") String body,
//                             Model model) {
//        Post post = new Post();
//        post.setTitle(title);
//        post.setBody(body);
//        postRepo.save(post);
//        return "redirect:/posts/" + post.getId();
//    }
//
//    @GetMapping("/posts/edit/{id}")
//    public String editAd(@PathVariable long id, Model model) {
//        Post post = postRepo.getPostById(id);
//        model.addAttribute("post", post);
//        return "ads/edit";
//    }
//
//    @PostMapping("/posts/edit")
//    public String updateAd(@RequestParam(name = "id") long id,
//                           @RequestParam(name = "title") String title,
//                           @RequestParam(name = "body") String body) {
//        Post post = new Post();
//        post.setId(id);
//        post.setTitle(title);
//        post.setBody(body);
//        postRepo.save(post);
//        return "redirect:/ads/" + post.getId();
//    }

    private final PostRepository postRepo;
    private final UserRepository userRepo;
    private final EmailService emailService;

    public PostController(PostRepository postRepo, UserRepository userRepo, EmailService emailService) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String showPosts(Model model) {
        List<Post> posts = postRepo.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable Integer id, Model model) {
        Post post = postRepo.getPostById(id);
        if (post.getUser() == null) {
            List <User> users = userRepo.findAll();
            post.setUser(users.get(0));
        }
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showCreatePost (Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {

        // Send the create email
        if (post.getId() == 0) {
            post.setUser(userRepo.findAll().get(0)); // kluge to set a current user
            emailService.prepareAndSend(post.getUser().getEmail(),
                    "Created Post: " + post.getTitle(),
                    post.getTitle() + "\n\n" + post.getBody());
        }

        // Send email for an edit
        else {
            post.setUser(postRepo.getPostById(post.getId()).getUser()); // Get the user from the database
            emailService.prepareAndSend(post.getUser().getEmail(),
                    "Edited Post: " + post.getTitle(),
                    post.getTitle() + "\n\n" + post.getBody());
        }
        postRepo.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/delete/{id}")
    public String deleteAd(@PathVariable long id) {
        Post post = postRepo.getPostById(id);
        post.setUser(postRepo.getPostById(post.getId()).getUser()); // Get the user from the database

        // send email for a post delete
        emailService.prepareAndSend(post.getUser().getEmail(),
                "Created Post: " + post.getTitle(),
                post.getTitle() + "\n\n" + post.getBody());
        postRepo.delete(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String editAd(@PathVariable long id, Model model) {
        Post post = postRepo.getPostById(id);
        model.addAttribute("post", post);
        return "posts/create";
    }

    @PostMapping("/posts/edit")
    public String updateAd(@ModelAttribute Post post) {
        postRepo.save(post);
        return "redirect:/posts/" + post.getId();
    }
}
