package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 橙鼠鼠
 */
@Controller
public class MainController {
    @RequestMapping({"/toIndex", "/index", "/"})
    public String toIndex() {
        return "index";
    }

    @GetMapping({"/user/tologin"})
    public String toLogin() {
        return "user/login";
    }
}
