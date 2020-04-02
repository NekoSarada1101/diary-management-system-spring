package com.example.diary.domain.repository;

import com.example.diary.domain.model.Diary;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface DiaryDao {
    public List<Diary> fetchDiaryAll() throws DataAccessException;
}
