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

    //ソート
    public List<Diary> fetchSortDiaryList(String sortOptionCol, String sortOptionOrder) throws DataAccessException {
        //sortOptionColとsortOptionOrderの値が許可されたものかチェックする
        String[] allowedCol = {"insert_date", "good_point", "bad_point", "student_comment"};
        String[] allowedOrder = {"asc", "desc"};
        if (!hasAllowedValue(sortOptionCol, allowedCol, sortOptionOrder, allowedOrder)) {
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

    public boolean hasAllowedValue(String sortOptionCol, String[] allowedCol, String sortOptionOrder, String[] allowedOrder) {
        for (int i = 0; i < allowedCol.length; i++) {
            if (sortOptionCol.equals(allowedCol[i])) break;
            if (i == allowedCol.length - 1) return false;
        }

        for (int i = 0; i < allowedOrder.length; i++) {
            if (sortOptionOrder.equals(allowedOrder[i])) break;
            if (i == allowedOrder.length - 1) return false;
        }

        return true;
    }

    //検索
    public List<Diary> fetchSearchDiaryList(String searchWord, String fromWhere) {
        searchWord = "%" + searchWord + "%";
        String sql;
        List<Map<String, Object>> fetchDiaryList = null;
        if (fromWhere.equals("diaryManage")) {
            sql = "SELECT * FROM diary WHERE insert_date LIKE ? OR good_point LIKE ? OR bad_point LIKE ? OR student_comment LIKE ?";
            fetchDiaryList = jdbcTemplate.queryForList(sql, searchWord, searchWord, searchWord, searchWord);
        }

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

            System.out.println(diary.getInsertDate());
            diaryList.add(diary);
        }

        return diaryList;
    }

    //登録
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
}
