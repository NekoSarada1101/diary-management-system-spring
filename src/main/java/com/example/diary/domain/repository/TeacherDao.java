package com.example.diary.domain.repository;

import com.example.diary.domain.model.Teacher;
import org.springframework.dao.DataAccessException;

public interface TeacherDao {
    public Teacher login(String teacherId, String password) throws DataAccessException;
}
