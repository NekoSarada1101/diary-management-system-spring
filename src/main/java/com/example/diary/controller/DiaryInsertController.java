package com.example.diary.controller;

import com.example.diary.domain.model.Diary;
import com.example.diary.domain.model.DiaryInsertForm;
import com.example.diary.domain.model.Student;
import com.example.diary.domain.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DiaryInsertController {

    @Autowired
    DiaryService diaryService;

    @GetMapping("/diaryInsert")
    public String getDiaryInsert(@ModelAttribute DiaryInsertForm diaryInsertForm, Model model) {
        model.addAttribute("contents", "student/diaryInsert :: diaryInsert_contents");
        model.addAttribute("title", "日誌登録");
        return "student/main";
    }

    @PostMapping("/diaryInsertCheck")
    public String postDiaryInsertCheck(@ModelAttribute DiaryInsertForm diaryInsertForm, Model model, HttpSession session) {
        model.addAttribute("contents", "student/diaryInsertCheck :: diaryInsertCheck_contents");
        model.addAttribute("title", "日誌登録確認");

        Diary diary = new Diary();
        diary.setClassCode(((Student) session.getAttribute("student")).getClassCode());
        diary.setInsertDate((String) session.getAttribute("today"));
        diary.setStudentId(((Student) session.getAttribute("student")).getStudentId());
        diary.setGoodPoint(diaryInsertForm.getGoodPoint());
        diary.setBadPoint(diaryInsertForm.getBadPoint());
        diary.setStudentComment(diaryInsertForm.getStudentComment());

        System.out.println(diary.getClassCode());
        System.out.println(diary.getInsertDate());
        System.out.println(diary.getStudentId());
        System.out.println(diary.getGoodPoint());
        System.out.println(diary.getBadPoint());
        System.out.println(diary.getStudentComment());

        session.setAttribute("diary", diary);
        return "student/main";
    }

    @PostMapping("/diaryInsertComplete")
    public String getDiaryInsertComplete(HttpSession session, Model model) {
        model.addAttribute("contents", "student/diaryInsertComplete :: diaryInsertComplete_contents");
        model.addAttribute("title", "日誌登録完了");

        int row = diaryService.insertDiary((Diary) session.getAttribute("diary"));
        System.out.println(row);

        return "student/main";
    }
}
