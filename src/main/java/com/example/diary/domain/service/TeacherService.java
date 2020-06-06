package com.example.diary.domain.service;

import com.example.diary.domain.model.Teacher;
import com.example.diary.domain.repository.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class TeacherService {

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    HttpSession session;

    public Teacher login(String teacherId, String password) {
        return teacherDao.login(teacherId, password);
    }

    public boolean checkLogin() {
        if (session.getAttribute("teacher") == null) {
            System.out.println("ログインチェックエラー");
            return false;
        }
        return true;
    }
}
