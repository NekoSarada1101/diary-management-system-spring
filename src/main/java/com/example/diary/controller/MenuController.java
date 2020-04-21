package com.example.diary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/studentMenu")
    public String getStudentMenu(Model model) {
        model.addAttribute("contents", "student/studentMenu :: studentMenu_contents");
        model.addAttribute("title", "学生メニュー");

        return "student/main";
    }
}
