package com.example.diary.controller;

import com.example.diary.domain.model.GroupOrder;
import com.example.diary.domain.model.LoginForm;
import com.example.diary.domain.model.Student;
import com.example.diary.domain.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class LoginController {

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @GetMapping("/login")
    public String getLogin(@ModelAttribute LoginForm loginForm, Model model) {
        return "login";
    }

    @PostMapping("/auth")
    public String postLogin(@ModelAttribute @Validated(GroupOrder.class) LoginForm loginForm, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return getLogin(loginForm, model);
        }

        if (loginForm.getId().length() == 7) {
            Student student = studentService.login(loginForm.getId(), loginForm.getPassword());
            session.setAttribute("student", student);
            return "redirect:/studentMenu";
        } else {
            Teacher teacher = teacherService.login(loginForm.getId(), loginForm.getPassword());
            session.setAttribute("teacher", teacher);
            return "redirect:/teacherMenu";
        }
    }

    @GetMapping("/studentMenu")
    public String getStudentMenu(Model model, HttpSession session) {
        if (!studentService.checkLogin()) return "sessionError";
        model.addAttribute("contents", "student/studentMenu :: studentMenu_contents");
        model.addAttribute("title", "学生メニュー");

        //今日の日付を取得
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(cal.getTime());
        session.setAttribute("today", today);

        return "student/main";
    }

    @PostMapping("/logout")
    public String postLogout(HttpSession session) {
        session.invalidate();
        return "login";
    }
}

