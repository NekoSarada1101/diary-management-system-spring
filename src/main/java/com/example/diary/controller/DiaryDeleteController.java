package com.example.diary.controller;

import com.example.diary.domain.model.Diary;
import com.example.diary.domain.model.Student;
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
        model.addAttribute("contents", "student/diaryDeleteCheck :: diaryDeleteCheck_contents");
        model.addAttribute("title", "日誌削除確認");

        return "student/main";
    }

    @PostMapping("/diaryDeleteComplete")
    public String postDiaryDeleteComplete(@ModelAttribute StudentDiaryForm studentDiaryForm, HttpSession session, Model model) {
        model.addAttribute("contents", "student/diaryDeleteComplete :: diaryDeleteComplete_contents");
        model.addAttribute("title", "日誌削除完了");

        Diary diary = new Diary();
        diary.setClassCode(((Student) session.getAttribute("student")).getClassCode());
        diary.setInsertDate(studentDiaryForm.getInsertDate());

        int row = diaryService.deleteDiary(diary);
        System.out.println(row);

        return "student/main";
    }
}
