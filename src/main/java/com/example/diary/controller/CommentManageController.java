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
public class CommentManageController {

    @Autowired
    DiaryService diaryService;

    String[] key = {"insert_date", "good_point", "bad_point", "student_comment", "teacher_comment"};
    String[] value = {"登録日", "良い点", "悪い点", "学生コメント", "教員コメント"};

    //コメント管理画面/ソート
    @PostMapping("/commentManage")
    public String postCommentManage(@ModelAttribute DiarySortForm diarySortForm, @ModelAttribute DiarySearchForm diarySearchForm, Model model, HttpSession session) {
        if (!diaryService.checkLogin("teacher")) return "sessionError";
        diaryService.addContentsAndTitle(model, "teacher", "commentManage", "コメント管理");

        session.removeAttribute("diary");

        //選択されたselectBoxのoptionの値を取得
        String sortOptionCol = diarySortForm.getSortOptionCol();
        String sortOptionOrder = diarySortForm.getSortOptionOrder();

        //初期表示
        if (sortOptionCol == null) sortOptionCol = "insert_date";
        if (sortOptionOrder == null) sortOptionOrder = "desc";

        //ソートした日誌リスト取得
        List<Diary> diaryList = diaryService.fetchSortDiaryList(sortOptionCol, sortOptionOrder, "commentManage");
        model.addAttribute("diaryList", diaryList);

        //selectBoxのoptionの値を設定
        Map<String, String> selectColMap = diaryService.createSelectBoxOptionCol(key, value);
        Map<String, String> selectOrderMap = diaryService.createSelectBoxOptionOrder();
        model.addAttribute("sortOptionCol", selectColMap);
        model.addAttribute("sortOptionOrder", selectOrderMap);

        return "teacher/main";
    }

    //検索
    @PostMapping("/commentManageSearch")
    public String postCommentManageSearch(@ModelAttribute DiarySortForm diarySortForm, @ModelAttribute @Validated DiarySearchForm diarySearchForm, BindingResult bindingResult, Model model, HttpSession session) {
        if (!diaryService.checkLogin("teacher")) return "sessionError";
        diaryService.addContentsAndTitle(model, "teacher", "commentManage", "コメント管理");

        //selectBoxのoptionの値を設定
        Map<String, String> selectColMap = diaryService.createSelectBoxOptionCol(key, value);
        Map<String, String> selectOrderMap = diaryService.createSelectBoxOptionOrder();
        model.addAttribute("sortOptionCol", selectColMap);
        model.addAttribute("sortOptionOrder", selectOrderMap);

        if (bindingResult.hasErrors()) {
            //ソートした日誌リスト取得
            List<Diary> diaryList = diaryService.fetchSortDiaryList("insert_date", "desc", "diaryManage");
            model.addAttribute("diaryList", diaryList);

            return "teacher/main";
        }

        String searchWord = diarySearchForm.getSearchWord();

        //検索した日誌リスト取得
        List<Diary> diaryList = diaryService.fetchSearchDiaryList(searchWord, "commentManage");
        model.addAttribute("diaryList", diaryList);

        return "teacher/main";
    }
}
