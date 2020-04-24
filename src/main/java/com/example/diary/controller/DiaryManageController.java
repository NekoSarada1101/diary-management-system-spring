package com.example.diary.controller;

import com.example.diary.domain.model.Diary;
import com.example.diary.domain.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Controller
public class DiaryManageController {

    @Autowired
    DiaryService diaryService;

    @GetMapping("/diaryManage")
    public String getDiaryManage(Model model, HttpSession session) {
    @PostMapping("/diaryManage")
    public String postDiaryManage(@ModelAttribute DiaryManageSortForm diaryManageSortForm, Model model, HttpSession session) {
        model.addAttribute("contents", "student/diaryList :: diaryList_contents");
        model.addAttribute("title", "日誌管理");

        List<Diary> diaryList = diaryService.fetchDiaryAll();

        model.addAttribute("diaryList", diaryList);

        //今日の日付を取得
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(cal.getTime());

        session.setAttribute("today", today);

        return "student/main";
    }
}
