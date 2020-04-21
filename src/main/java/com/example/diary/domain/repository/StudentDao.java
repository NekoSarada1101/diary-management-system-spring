package com.example.diary.domain.repository;

import com.example.diary.domain.model.Student;
import org.springframework.dao.DataAccessException;

public interface StudentDao {
    public Student login(String studentId, String password) throws DataAccessException;
}
