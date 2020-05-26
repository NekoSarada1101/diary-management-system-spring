package com.example.diary.controller;

import com.example.diary.domain.model.StudentDiaryForm;
import com.example.diary.domain.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DiaryDeleteController {

    @Autowired
    DiaryService diaryService;

    @PostMapping("/diaryDeleteCheck")
    public String postDiaryDeleteCheck(@ModelAttribute StudentDiaryForm studentDiaryForm, Model model) {
        if (!diaryService.checkLogin()) return "sessionError";
        diaryService.addContentsAndTitle(model, "diaryDeleteCheck", "日誌削除確認");

        return "student/main";
    }

    @PostMapping("/diaryDeleteComplete")
    public String postDiaryDeleteComplete(@ModelAttribute StudentDiaryForm studentDiaryForm, HttpSession session, Model model) {
        if (!diaryService.checkLogin()) return "sessionError";
        diaryService.addContentsAndTitle(model, "diaryDeleteComplete", "日誌削除完了");

        int row = diaryService.deleteDiary(diaryService.setDiaryClass(studentDiaryForm, session));
        System.out.println(row);

        return "student/main";
    }
}
