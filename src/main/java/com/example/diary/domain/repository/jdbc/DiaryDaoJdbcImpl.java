package com.example.diary.domain.repository.jdbc;

import com.example.diary.domain.model.Diary;
import com.example.diary.domain.repository.DiaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DiaryDaoJdbcImpl implements DiaryDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Diary> fetchDiaryAll() throws DataAccessException {
        List<Map<String, Object>> fetchDiaryList = jdbcTemplate.queryForList("SELECT * FROM diary");

        List<Diary> diaryList = new ArrayList<>();

        for (Map<String, Object> map : fetchDiaryList) {
            Diary diary = new Diary();

            diary.setClassCode((String) map.get("class_code"));
            diary.setInsertDate((Date) map.get("insert_date"));
            diary.setStudentId((String) map.get("student_id"));
            diary.setGoodPoint((String) map.get("good_point"));
            diary.setBadPoint((String) map.get("bad_point"));
            diary.setStudentComment((String) map.get("student_comment"));
            diary.setTeacherComment((String) map.get("teacher_comment"));

            diaryList.add(diary);
        }

        return diaryList;
    }


}
