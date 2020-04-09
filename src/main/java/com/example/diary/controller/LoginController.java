package com.example.diary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    StudentService studentService;

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(Model model) {
        model.addAttribute("contents", "student/studentMenu :: studentMenu_contents");
        model.addAttribute("title","学生メニュー");
        return "student/main";
    }
}
