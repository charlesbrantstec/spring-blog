package com.example.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

//TODO:    GET	/posts	        posts index page
//         GET	/posts/{id}	    view an individual post
//         GET	/posts/create	view the form for creating a post
//         POST	/posts/create	create a new post

    @RequestMapping(path = "/add/{number1}/and/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String add(@PathVariable int number1, @PathVariable int number2){
        return number1 + " plus " + number2 + " is " + (number1 + number2) + ".";
    }
}
