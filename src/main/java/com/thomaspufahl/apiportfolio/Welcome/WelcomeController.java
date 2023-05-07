package com.thomaspufahl.apiportfolio.Welcome;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/welcome")
public class WelcomeController {
    @GetMapping
    public String sendWelcomeMessage() {
        return "Welcome to my app";
    }
}
