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
import java.util.List;
import java.util.Map;

@Controller
public class DiaryDisplayController {

    @Autowired
    DiaryService diaryService;

    String[] key = {"insert_date", "student_id", "good_point", "bad_point", "student_comment", "teacher_comment"};
    String[] value = {"登録日", "学籍番号", "良い点", "悪い点", "学生コメント", "教員コメント"};

    @PostMapping("/diaryDisplay")
    public String postDiaryDisplay(@ModelAttribute DiarySortForm diarySortForm, @ModelAttribute DiarySearchForm diarySearchForm, Model model) {
        model.addAttribute("contents", "student/diaryDisplay :: diaryDisplay_contents");
        model.addAttribute("title", "日誌閲覧");

        //選択されたselectBoxのoptionの値を取得
        String sortOptionCol = diarySortForm.getSortOptionCol();
        String sortOptionOrder = diarySortForm.getSortOptionOrder();

        //初期表示
        if (sortOptionCol == null) sortOptionCol = "insert_date";
        if (sortOptionOrder == null) sortOptionOrder = "desc";

        //ソートした日誌リスト取得
        List<Diary> diaryList = diaryService.fetchSortDiaryList(sortOptionCol, sortOptionOrder, "diaryDisplay");
        model.addAttribute("diaryList", diaryList);

        //selectBoxのoptionの値を設定

        Map<String, String> selectColMap = diaryService.createSelectBoxOptionCol(key, value);
        Map<String, String> selectOrderMap = diaryService.createSelectBoxOptionOrder();
        model.addAttribute("sortOptionCol", selectColMap);
        model.addAttribute("sortOptionOrder", selectOrderMap);

        return "student/main";
    }

    //検索
    @PostMapping("/diaryDisplaySearch")
    public String postDiaryManageSearch(@ModelAttribute DiarySortForm diarySortForm, @ModelAttribute @Validated DiarySearchForm diarySearchForm, BindingResult bindingResult, Model model, HttpSession session) {

        model.addAttribute("contents", "student/diaryDisplay :: diaryDisplay_contents");
        model.addAttribute("title", "日誌管理");

        //selectBoxのoptionの値を設定
        Map<String, String> selectColMap = diaryService.createSelectBoxOptionCol(key, value);
        Map<String, String> selectOrderMap = diaryService.createSelectBoxOptionOrder();
        model.addAttribute("sortOptionCol", selectColMap);
        model.addAttribute("sortOptionOrder", selectOrderMap);

        if (bindingResult.hasErrors()) {
            //ソートした日誌リスト取得
            List<Diary> diaryList = diaryService.fetchSortDiaryList("insert_date", "desc", "diaryDisplay");
            model.addAttribute("diaryList", diaryList);

            return "student/main";
        }

        String searchWord = diarySearchForm.getSearchWord();

        //検索した日誌リスト取得
        List<Diary> diaryList = diaryService.fetchSearchDiaryList(searchWord, "diaryDisplay");
        model.addAttribute("diaryList", diaryList);

        return "student/main";
    }
}
