package com.example.diary.domain.service;

import com.example.diary.domain.model.Diary;
import com.example.diary.domain.repository.DiaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryService {

    @Autowired
    DiaryDao diaryDao;

    public List<Diary> fetchDiaryAll(){
        return diaryDao.fetchDiaryAll();
    }
}
