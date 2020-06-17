package com.example.diary.domain.service;

import com.example.diary.domain.model.Diary;
import com.example.diary.domain.model.Student;
import com.example.diary.domain.model.DiaryForm;
import com.example.diary.domain.repository.DiaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiaryService {

    @Autowired
    DiaryDao diaryDao;

    @Autowired
    HttpSession session;

    public boolean checkLogin(String sessionName) {
        if (session.getAttribute(sessionName) == null) {
            System.out.println("ログインチェックエラー");
            return false;
        }
        return true;
    }

    public List<Diary> fetchSortDiaryList(String sortOptionCol, String sortOptionOrder, String fromWhere) {
        //sortOptionColとsortOptionOrderの値が許可されたものかチェックする
        String[] allowedOrder = {"asc", "desc"};
        boolean isAllow = false;

        if (fromWhere.equals("diaryManage")) {
            String[] allowedCol = {"insert_date", "good_point", "bad_point", "student_comment"};
            isAllow = hasAllowedValue(sortOptionCol, allowedCol, sortOptionOrder, allowedOrder);

        } else if (fromWhere.equals("diaryDisplay")) {
            String[] allowedCol = {"insert_date", "student_id", "good_point", "bad_point", "student_comment", "teacher_comment"};
            isAllow = hasAllowedValue(sortOptionCol, allowedCol, sortOptionOrder, allowedOrder);

        } else if (fromWhere.endsWith("commentManage")) {
            String[] allowedCol = {"insert_date", "student_id", "good_point", "bad_point", "student_comment", "teacher_comment"};
            isAllow = hasAllowedValue(sortOptionCol, allowedCol, sortOptionOrder, allowedOrder);
        }

        if (!isAllow) return null;

        return diaryDao.fetchSortDiaryList(sortOptionCol, sortOptionOrder, fromWhere);
    }

    //ソート対象のカラムの正当性チェック
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

    public List<Diary> fetchSearchDiaryList(String searchWord, String fromWhere) {
        return diaryDao.fetchSearchDiaryList(searchWord, fromWhere);
    }

    public boolean hasDiaryInsertedToday(String classCode, String today) {
        return diaryDao.hasDiaryInsertedToday(classCode, today);
    }

    public int insertDiary(Diary diary) {
        return diaryDao.insertDiary(diary);
    }

    public int updateDiary(Diary diary) {
        return diaryDao.updateDiary(diary);
    }

    public int deleteDiary(Diary diary) {
        return diaryDao.deleteDiary(diary);
    }

    public Map<String, String> createSelectBoxOptionCol(String[] key, String[] value) {
        Map<String, String> selectColMap = new LinkedHashMap<>();

        for (int i = 0; i < key.length; i++) {
            selectColMap.put(key[i], value[i]);
        }

        return selectColMap;
    }

    public Map<String, String> createSelectBoxOptionOrder() {
        Map<String, String> selectOrderMap = new LinkedHashMap<>();
        selectOrderMap.put("asc", "昇順");
        selectOrderMap.put("desc", "降順");

        return selectOrderMap;
    }

    public Diary setDiaryClass(DiaryForm diaryForm, HttpSession session) {
        Diary diary = new Diary();
        diary.setClassCode(((Student) session.getAttribute("student")).getClassCode());
        diary.setInsertDate((String) session.getAttribute("today"));
        diary.setStudentId(((Student) session.getAttribute("student")).getStudentId());
        diary.setGoodPoint(diaryForm.getGoodPoint());
        diary.setBadPoint(diaryForm.getBadPoint());
        diary.setStudentComment(diaryForm.getStudentComment());
        diary.setTeacherComment(diaryForm.getTeacherComment());

        return diary;
    }

    public void addContentsAndTitle(Model model, String role, String contents, String title) {
        model.addAttribute("contents", role + "/" + contents + " :: " + contents + "_contents");
        model.addAttribute("title", title);
    }
}
