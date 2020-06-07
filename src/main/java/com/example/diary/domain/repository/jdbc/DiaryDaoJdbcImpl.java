package com.example.diary.domain.repository.jdbc;

import com.example.diary.domain.model.Diary;
import com.example.diary.domain.repository.DiaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DiaryDaoJdbcImpl implements DiaryDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //ソート
    public List<Diary> fetchSortDiaryList(String sortOptionCol, String sortOptionOrder, String fromWhere) throws DataAccessException {
        List<Map<String, Object>> fetchDiaryList = jdbcTemplate.queryForList("SELECT * FROM diary ORDER BY " + sortOptionCol + " " + sortOptionOrder);

        List<Diary> diaryList = new ArrayList<>();

        for (Map<String, Object> map : fetchDiaryList) {
            Diary diary = new Diary();

            diary.setClassCode((String) map.get("class_code"));
            diary.setInsertDate((map.get("insert_date")).toString());
            diary.setStudentId((String) map.get("student_id"));
            diary.setGoodPoint((String) map.get("good_point"));
            diary.setBadPoint((String) map.get("bad_point"));
            diary.setStudentComment((String) map.get("student_comment"));
            diary.setTeacherComment((String) map.get("teacher_comment"));

            diaryList.add(diary);
        }

        return diaryList;
    }

    //検索
    public List<Diary> fetchSearchDiaryList(String searchWord, String fromWhere) {
        searchWord = "%" + searchWord + "%";
        String sql;
        List<Map<String, Object>> fetchDiaryList = null;
        if (fromWhere.equals("diaryManage")) {
            sql = "SELECT * FROM diary WHERE " +
                    "insert_date LIKE ? OR " +
                    "good_point LIKE ? OR " +
                    "bad_point LIKE ? OR " +
                    "student_comment LIKE ?";
            fetchDiaryList = jdbcTemplate.queryForList(sql, searchWord, searchWord, searchWord, searchWord);

        } else if (fromWhere.equals("diaryDisplay")) {
            sql = "SELECT * FROM diary WHERE " +
                    "insert_date LIKE ? OR " +
                    "student_id LIKE ? OR " +
                    "good_point LIKE ? OR " +
                    "bad_point LIKE ? OR " +
                    "student_comment LIKE ? OR " +
                    "teacher_comment LIKE ?";
            fetchDiaryList = jdbcTemplate.queryForList(sql, searchWord, searchWord, searchWord, searchWord, searchWord, searchWord);
        }

        List<Diary> diaryList = new ArrayList<>();

        for (Map<String, Object> map : fetchDiaryList) {
            Diary diary = new Diary();

            diary.setClassCode((String) map.get("class_code"));
            diary.setInsertDate((map.get("insert_date")).toString());
            diary.setStudentId((String) map.get("student_id"));
            diary.setGoodPoint((String) map.get("good_point"));
            diary.setBadPoint((String) map.get("bad_point"));
            diary.setStudentComment((String) map.get("student_comment"));
            diary.setTeacherComment((String) map.get("teacher_comment"));

            System.out.println(diary.getInsertDate());
            diaryList.add(diary);
        }

        return diaryList;
    }

    public boolean hasDiaryInsertedToday(String classCode, String today) {
        int count = jdbcTemplate.queryForObject("SELECT count(*) FROM diary WHERE class_code = ? AND insert_date = ?", Integer.class, classCode, today);
        if (count == 0) return false;
        return true;
    }

    //登録
    //TODO INSERT文が2回実行されている 原因不明
    public int insertDiary(Diary diary) throws DataAccessException {
        int row = jdbcTemplate.update("INSERT INTO diary(class_code, insert_date,student_id,good_point,bad_point,student_comment) VALUES (?,?,?,?,?,?)",
                diary.getClassCode(),
                diary.getInsertDate(),
                diary.getStudentId(),
                diary.getGoodPoint(),
                diary.getBadPoint(),
                diary.getStudentComment());

        return row;
    }

    //更新
    public int updateDiary(Diary diary) throws DataAccessException {
        int row = jdbcTemplate.update("UPDATE diary SET good_point = ?, bad_point = ?, student_comment = ? WHERE insert_date = ? AND class_code = ?",
                diary.getGoodPoint(),
                diary.getBadPoint(),
                diary.getStudentComment(),
                diary.getInsertDate(),
                diary.getClassCode());

        return row;
    }

    //削除
    public int deleteDiary(Diary diary) throws DataAccessException {
        int row = jdbcTemplate.update("DELETE FROM diary WHERE class_code = ? AND insert_date = ?", diary.getClassCode(), diary.getInsertDate());

        return row;
    }
}
