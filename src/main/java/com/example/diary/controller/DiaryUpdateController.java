package com.example.diary.controller;

import com.example.diary.domain.model.Diary;
import com.example.diary.domain.model.GroupOrder;
import com.example.diary.domain.model.Student;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class DiaryUpdateController {

    @Autowired
    DiaryService diaryService;

    @GetMapping("/diaryUpdateInput")
    public String getDiaryUpdateInput(@ModelAttribute StudentDiaryForm studentDiaryForm,
                                      @RequestParam("insertDate") String insertDate,
                                      @RequestParam("goodPoint") String goodPoint,
                                      @RequestParam("badPoint") String badPoint,
                                      @RequestParam("studentComment") String studentComment,
                                      Model model,
                                      HttpSession session) {

        model.addAttribute("contents", "student/diaryUpdateInput :: diaryUpdateInput_contents");
        model.addAttribute("title", "日誌修正入力");

        model.addAttribute("insertDate", insertDate);
        model.addAttribute("goodPoint", goodPoint);
        model.addAttribute("badPoint", badPoint);
        model.addAttribute("studentComment", studentComment);

        session.setAttribute("insertDate", insertDate);

        return "student/main";
    }

    @PostMapping("diaryUpdateCheck")
    public String postDiaryUpdateCheck(@ModelAttribute @Validated(GroupOrder.class) StudentDiaryForm studentDiaryForm, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return getDiaryUpdateInput(studentDiaryForm, (String) session.getAttribute("insertDate"), studentDiaryForm.getGoodPoint(), studentDiaryForm.getBadPoint(), studentDiaryForm.getStudentComment(), model, session);
        }

        model.addAttribute("contents", "student/diaryUpdateCheck :: diaryUpdateCheck_contents");
        model.addAttribute("title", "日誌修正確認");

        model.addAttribute("insertDate", (String) session.getAttribute("insertDate"));

        Diary diary = new Diary();
        diary.setClassCode(((Student) session.getAttribute("student")).getClassCode());
        diary.setInsertDate((String) session.getAttribute("insertDate"));
        diary.setStudentId(((Student) session.getAttribute("student")).getStudentId());
        diary.setGoodPoint(studentDiaryForm.getGoodPoint());
        diary.setBadPoint(studentDiaryForm.getBadPoint());
        diary.setStudentComment(studentDiaryForm.getStudentComment());

        session.setAttribute("diary", diary);
        return "student/main";
    }

    @PostMapping("/diaryUpdateComplete")
    public String postDiaryUpdateComplete(HttpSession session, Model model) {
        model.addAttribute("contents", "student/diaryUpdateComplete :: diaryUpdateComplete_contents");
        model.addAttribute("title", "日誌修正完了");

        diaryService.updateDiary((Diary) session.getAttribute("diary"));

        return "student/main";
    }
}
