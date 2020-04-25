package com.example.diary.domain.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DiarySearchForm {

    @NotBlank
    private String searchWord;
}
