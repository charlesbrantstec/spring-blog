package com.example.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {

//TODO:       /add/3/and/4	        7
//            /subtract/3/from/10	7
//            /multiply/4/and/5	    20
//            /divide/6/by/3	    2

    @RequestMapping(path = "/add/{number1}/and/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String add(@PathVariable int number1, @PathVariable int number2){
        return number1 + " plus " + number2 + " is " + (number1 + number2) + ".";
    }

    @RequestMapping(path = "/subtract/{number1}/and/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String subtract(@PathVariable int number1, @PathVariable int number2){
        return number2 + " minus " + number1 + " is " + (number2 - number1) + ".";
    }

    @RequestMapping(path = "/multiply/{number1}/and/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String multiply(@PathVariable int number1, @PathVariable int number2){
        return number1 + " times " + number2 + " is " + (number1 * number2) + ".";
    }

    @RequestMapping(path = "/divide/{number1}/and/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String divide(@PathVariable int number1, @PathVariable int number2){
        return number1 + " divided by " + number2 + " is " + (number1 / number2) + ".";
    }

}
