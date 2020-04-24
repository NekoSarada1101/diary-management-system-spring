package com.example.diary.domain.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DiaryManageSortForm {

    @NotBlank
    private String sortOptionCol;

    @NotBlank
    private String sortOptionOrder;
}
