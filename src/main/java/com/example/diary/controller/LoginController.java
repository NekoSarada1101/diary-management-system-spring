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

@Controller
public class LoginController {

    @Autowired
    StudentService studentService;

    @GetMapping("/login")
    public String getLogin(@ModelAttribute LoginForm loginForm, Model model) {
        return "login";
    }

    @PostMapping("/auth")
    public String postLogin(@ModelAttribute @Validated(GroupOrder.class) LoginForm loginForm, BindingResult bindingResult, Model model, HttpSession session) {
        model.addAttribute("contents", "student/studentMenu :: studentMenu_contents");
        model.addAttribute("title", "学生メニュー");

        if(bindingResult.hasErrors()){
            return getLogin(loginForm, model);
        }

        Student student = studentService.login(loginForm.getStudentId(), loginForm.getStudentPassword());

        session.setAttribute("student", student);

        return "student/main";
    }
}
