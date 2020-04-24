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

    public List<Diary> fetchSortDiaryList(String sortOptionCol, String sortOptionOrder) throws DataAccessException {
        boolean hasAllowed = hasAllowedValueForStudent(sortOptionCol, sortOptionOrder);
        if (!hasAllowed){
            return null;
        }

        List<Map<String, Object>> fetchDiaryList = jdbcTemplate.queryForList("SELECT * FROM diary ORDER BY " + sortOptionCol + " " + sortOptionOrder);

        List<Diary> diaryList = new ArrayList<>();

        for (Map<String, Object> map : fetchDiaryList) {
            Diary diary = new Diary();

            diary.setClassCode((String) map.get("class_code"));
            diary.setInsertDate(((Date) map.get("insert_date")).toString());
            diary.setStudentId((String) map.get("student_id"));
            diary.setGoodPoint((String) map.get("good_point"));
            diary.setBadPoint((String) map.get("bad_point"));
            diary.setStudentComment((String) map.get("student_comment"));
            diary.setTeacherComment((String) map.get("teacher_comment"));

            diaryList.add(diary);
        }

        return diaryList;
    }

    public boolean hasAllowedValueForStudent(String sortCol, String sortOrder) {
        switch (sortCol) {
            case "insert_date":
                break;
            case "good_point":
                break;
            case "bad_point":
                break;
            case "student_comment":
                break;
            default:
                return false;
        }

        switch (sortOrder) {
            case "asc":
                break;
            case "desc":
                break;
            default:
                return false;
        }

        return true;
    }

    public int insertDiary(Diary diary) throws DataAccessException {
        int row = jdbcTemplate.update("INSERT INTO diary(class_code, insert_date,student_id,good_point,bad_point,student_comment) VALUES (?,?,?,?,?,?)", diary.getClassCode(), diary.getInsertDate(), diary.getStudentId(), diary.getGoodPoint(), diary.getBadPoint(), diary.getStudentComment());

        return row;
    }

}
