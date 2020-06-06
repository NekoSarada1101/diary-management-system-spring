package com.example.diary.domain.repository.jdbc;

import com.example.diary.domain.model.Teacher;
import com.example.diary.domain.repository.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TeacherDaoJdbcImpl implements TeacherDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Teacher login(String teacherId, String password) throws DataAccessException {
        Map<String, Object> teacherInfo = jdbcTemplate.queryForMap("SELECT * FROM teacher WHERE teacher_code = ? AND teacher_password = ?", teacherId, password);

        Teacher teacher = new Teacher();

        teacher.setTeacherCode((String) teacherInfo.get("teacher_code"));
        teacher.setTeacherName((String) teacherInfo.get("teacher_name"));

        return teacher;
    }
}
