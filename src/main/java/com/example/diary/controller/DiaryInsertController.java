package com.example.diary.controller;

import com.example.diary.domain.model.Diary;
import com.example.diary.domain.model.GroupOrder;
import com.example.diary.domain.model.StudentDiaryForm;
import com.example.diary.domain.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DiaryInsertController {

    @Autowired
    DiaryService diaryService;

    @GetMapping("/diaryInsertInput")
    public String getDiaryInsertInput(@ModelAttribute StudentDiaryForm studentDiaryForm, Model model, HttpSession session) {
        diaryService.addContentsAndTitle(model, "diaryInsertInput", "日誌登録入力");

        try {
            studentDiaryForm.setInsertDate((String) session.getAttribute("today"));
            studentDiaryForm.setGoodPoint(((Diary) session.getAttribute("diary")).getGoodPoint());
            studentDiaryForm.setBadPoint(((Diary) session.getAttribute("diary")).getBadPoint());
            studentDiaryForm.setStudentComment(((Diary) session.getAttribute("diary")).getStudentComment());
        } catch (NullPointerException e) {
        }

        return "student/main";
    }

    @PostMapping("/diaryInsertCheck")
    public String postDiaryInsertCheck(@ModelAttribute @Validated(GroupOrder.class) StudentDiaryForm studentDiaryForm, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return getDiaryInsertInput(studentDiaryForm, model, session);
        }
        diaryService.addContentsAndTitle(model, "diaryInsertCheck", "日誌登録確認");

        session.setAttribute("diary", diaryService.setDiaryClass(studentDiaryForm, session));

        return "student/main";
    }

    @PostMapping("/diaryInsertComplete")
    public String postDiaryInsertComplete(HttpSession session, Model model) {
        diaryService.addContentsAndTitle(model, "diaryInsertComplete", "日誌登録完了");

        int row = diaryService.insertDiary((Diary) session.getAttribute("diary"));
        System.out.println(row);

        return "student/main";
    }
}
