package com.example.diary.domain.service;

import com.example.diary.domain.model.Student;
import com.example.diary.domain.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class StudentService {

    @Autowired
    StudentDao studentDao;

    @Autowired
    HttpSession session;

    public Student login(String studentId, String password) {
        return studentDao.login(studentId, password);
    }

    public boolean checkLogin() {
        if (session.getAttribute("student") == null) {
            System.out.println("ログインチェックエラー");
            return false;
        }
        return true;
    }
}
