package com.example.diary.domain.service;

import com.example.diary.domain.model.Student;
import com.example.diary.domain.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentDao studentDao;

    public Student login(String studentId, String password) {
        return studentDao.login(studentId, password);
    }
}
