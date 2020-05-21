package com.example.diary.controller;

import com.example.diary.domain.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @Autowired
    DiaryService diaryService;

    @GetMapping("/studentMenu")
    public String getStudentMenu(Model model) {
        diaryService.addContentsAndTitle(model, "studentMenu", "学生メニュー");

        return "student/main";
    }
}
