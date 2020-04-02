package com.example.diary.domain.repository.jdbc;

import com.example.diary.domain.model.Student;
import com.example.diary.domain.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoJdbcImpl implements StudentDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Student login() {
        return null;
    }
}
