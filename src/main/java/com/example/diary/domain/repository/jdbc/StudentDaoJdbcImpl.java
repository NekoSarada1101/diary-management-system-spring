package com.example.diary.domain.repository.jdbc;

import com.example.diary.domain.model.Student;
import com.example.diary.domain.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class StudentDaoJdbcImpl implements StudentDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Student login(String studentId, String password) throws DataAccessException {
        Map<String, Object> studentInfo = jdbcTemplate.queryForMap("SELECT * FROM student WHERE student_id = ? AND student_password = ?", studentId, password);

        Student student = new Student();

        student.setStudentId((String) studentInfo.get("student_id"));
        student.setClassCode((String) studentInfo.get("class_code"));
        student.setStudentName((String) studentInfo.get("student_Name"));

        return student;
    }
}
