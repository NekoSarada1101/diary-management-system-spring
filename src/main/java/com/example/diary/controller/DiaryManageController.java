package com.example.diary.controller;

import com.example.diary.domain.model.Diary;
import com.example.diary.domain.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DiaryManageController {

    @Autowired
    DiaryService diaryService;

    @GetMapping("/diaryManage")
    public String getDiaryManage(Model model) {
        model.addAttribute("contents", "student/diaryList :: diaryList_contents");
        model.addAttribute("title", "日誌管理");

        List<Diary> diaryList = diaryService.fetchDiaryAll();

        model.addAttribute("diaryList", diaryList);

        return "student/main";
    }

    @PostMapping("/diaryManage")
    public String postDiaryManage(Model model) {
        return "student/diaryManage";
    }
}
