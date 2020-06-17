package com.example.diary.controller;

import com.example.diary.domain.model.Diary;
import com.example.diary.domain.model.DiaryForm;
import com.example.diary.domain.model.GroupOrder;
import com.example.diary.domain.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentUpdateController {

    @Autowired
    DiaryService diaryService;

    @PostMapping("/commentUpdateInput")
    public String postCommentUpdateInput(@ModelAttribute DiaryForm diaryForm, Model model, HttpSession session) {
        if (!diaryService.checkLogin("teacher")) return "sessionError";
        diaryService.addContentsAndTitle(model, "teacher", "commentUpdateInput", "コメント更新入力");

        return "teacher/main";
    }

    @PostMapping("/commentUpdateCheck")
    public String postCommentUpdateCheck(@ModelAttribute @Validated(GroupOrder.class) DiaryForm diaryForm, BindingResult bindingResult, Model model, HttpSession session) {
        if (!diaryService.checkLogin("teacher")) return "sessionError";
        if (bindingResult.hasErrors()) {
            return postCommentUpdateInput(diaryForm, model, session);
        }
        diaryService.addContentsAndTitle(model, "teacher", "commentUpdateCheck", "コメント更新確認");

        Diary diary = new Diary();
        diary.setInsertDate(diaryForm.getInsertDate());
        diary.setClassCode(diaryForm.getClassCode());
        diary.setGoodPoint(diaryForm.getGoodPoint());
        diary.setBadPoint(diaryForm.getBadPoint());
        diary.setStudentComment(diaryForm.getStudentComment());
        diary.setTeacherComment(diaryForm.getTeacherComment());
        session.setAttribute("diary", diary);

        return "teacher/main";
    }

    @PostMapping("/commentUpdateComplete")
    public String postCommentUpdateComplete(HttpSession session, Model model) {
        if (!diaryService.checkLogin("teacher")) return "sessionError";
        diaryService.addContentsAndTitle(model, "teacher", "commentUpdateComplete", "コメント更新完了");

        int row = diaryService.updateDiary((Diary) session.getAttribute("diary"));
        System.out.println(row);

        return "teacher/main";
    }

}
