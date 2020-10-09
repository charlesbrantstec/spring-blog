package com.example.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RollDiceController {

    @GetMapping ("/roll-dice")
    public String viewRollDice(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String guessedNumberResult(@PathVariable int n, Model model){
        String message ="";
        int rolledNum = (int) ((Math.random()*6) +1);
        String roll = String.valueOf(rolledNum);
        String guessedNum = String.valueOf(n);

        if(roll.equals(guessedNum)) {
            message = "You guessed the correct number:" + n + "";
        }else{
            message = "You guessed the wrong number";
        }
        model.addAttribute("message", message);
        return "roll-dice";
    }
}
