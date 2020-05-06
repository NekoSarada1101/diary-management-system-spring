package com.example.diary.controller;

import com.example.diary.domain.model.Diary;
import com.example.diary.domain.model.DiarySearchForm;
import com.example.diary.domain.model.DiarySortForm;
import com.example.diary.domain.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DiaryManageController {

    @Autowired
    DiaryService diaryService;

    //日誌管理画面/ソート
    @PostMapping("/diaryManage")
    public String postDiaryManage(@ModelAttribute DiarySortForm diarySortForm, @ModelAttribute DiarySearchForm diarySearchForm, Model model, HttpSession session) {

        model.addAttribute("contents", "student/diaryManage :: diaryManage_contents");
        model.addAttribute("title", "日誌管理");

        session.removeAttribute("diary");

        //選択されたselectBoxのoptionの値を取得
        String sortOptionCol = diarySortForm.getSortOptionCol();
        String sortOptionOrder = diarySortForm.getSortOptionOrder();

        //初期表示
        if (sortOptionCol == null) sortOptionCol = "insert_date";
        if (sortOptionOrder == null) sortOptionOrder = "desc";

        //ソートした日誌リスト取得
        List<Diary> diaryList = diaryService.fetchSortDiaryList(sortOptionCol, sortOptionOrder);
        model.addAttribute("diaryList", diaryList);

        //今日の日付を取得
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(cal.getTime());
        session.setAttribute("today", today);

        //selectBoxのoptionの値を設定
        Map<String, String> selectColMap = createSelectBoxOptionCol();
        Map<String, String> selectOrderMap = createSelectBoxOptionOrder();
        model.addAttribute("sortOptionCol", selectColMap);
        model.addAttribute("sortOptionOrder", selectOrderMap);

        return "student/main";
    }

    //検索
    @PostMapping("/diaryManageSearch")
    public String postDiaryManageSearch(@ModelAttribute DiarySortForm diarySortForm, @ModelAttribute @Validated DiarySearchForm diarySearchForm, BindingResult bindingResult, Model model, HttpSession session) {

        model.addAttribute("contents", "student/diaryManage :: diaryManage_contents");
        model.addAttribute("title", "日誌管理");

        //selectBoxのoptionの値を設定
        Map<String, String> selectColMap = createSelectBoxOptionCol();
        Map<String, String> selectOrderMap = createSelectBoxOptionOrder();
        model.addAttribute("sortOptionCol", selectColMap);
        model.addAttribute("sortOptionOrder", selectOrderMap);

        if (bindingResult.hasErrors()) {
            //ソートした日誌リスト取得
            List<Diary> diaryList = diaryService.fetchSortDiaryList("insert_date", "desc");
            model.addAttribute("diaryList", diaryList);

            return "student/main";
        }

        String searchWord = diarySearchForm.getSearchWord();

        //検索した日誌リスト取得
        List<Diary> diaryList = diaryService.fetchSearchDiaryList(searchWord, "diaryManage");
        model.addAttribute("diaryList", diaryList);

        return "student/main";
    }

    public Map<String, String> createSelectBoxOptionCol() {
        Map<String, String> selectColMap = new LinkedHashMap<>();
        selectColMap.put("insert_date", "登録日");
        selectColMap.put("good_point", "良い点");
        selectColMap.put("bad_point", "悪い点");
        selectColMap.put("student_comment", "学生コメント");

        return selectColMap;
    }

    public Map<String, String> createSelectBoxOptionOrder() {
        Map<String, String> selectOrderMap = new LinkedHashMap<>();
        selectOrderMap.put("asc", "昇順");
        selectOrderMap.put("desc", "降順");

        return selectOrderMap;
    }
}
