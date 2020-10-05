package com.example.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

//TODO:    GET	/posts	        posts index page
//         GET	/posts/{id}	    view an individual post
//         GET	/posts/create	view the form for creating a post
//         POST	/posts/create	create a new post

    @GetMapping("/posts")
    @ResponseBody
    public String posts(){
        return "<h1>Index</h1>";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postsId(@PathVariable long id){
        return "<h1>You are viewing index page " + id + ".</h1>";
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


}
