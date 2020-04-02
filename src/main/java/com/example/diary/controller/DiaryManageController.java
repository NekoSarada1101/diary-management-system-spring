package com.example.diary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DiaryManageController {


    @GetMapping("/diaryManage")
    public String getDiaryManage(Model model) {
        return "student/diaryManage";
    }

    @PostMapping("/diaryManage")
    public String postDiaryManage(Model model) {
        return "student/diaryManage";
    }
}
