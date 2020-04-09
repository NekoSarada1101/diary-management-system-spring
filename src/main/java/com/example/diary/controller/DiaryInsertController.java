package com.example.diary.controller;

import com.example.diary.domain.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiaryInsertController {

    @Autowired
    DiaryService diaryService;

    @GetMapping("/diaryInsert")
    public String getDiaryInsert(Model model) {
        model.addAttribute("contents", "student/diaryInsert :: diaryInsert_contents");
        model.addAttribute("title", "日誌登録");
        return "student/main";
    }

    @GetMapping("/diaryInsertCheck")
    public String getDiaryInsertCheck(Model model) {
        model.addAttribute("contents", "student/diaryInsertCheck :: diaryInsertCheck_contens");
        model.addAttribute("title", "日誌登録確認");
        return "student/main";
    }

    @GetMapping("/diaryInsertComplete")
    public String getDiaryInsertComplete(Model model){
        model.addAttribute("contents", "student/diaryInsertComplete :: diaryInsertComplete_contents");
        model.addAttribute("title", "日誌登録完了");
        return "student/main";
    }
}
