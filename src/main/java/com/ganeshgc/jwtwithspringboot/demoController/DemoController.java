package com.ganeshgc.jwtwithspringboot.demoController;

import org.springframework.ui.Model; // Correct import for Model
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController { // Renamed class to avoid conflict

    @GetMapping("/home")
    public String showHomePage() {
        return "Hello World!";

    }
}
