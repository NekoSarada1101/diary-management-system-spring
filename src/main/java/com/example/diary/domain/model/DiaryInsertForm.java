package com.example.diary.domain.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class DiaryInsertForm {

    @NotNull
    @Length(max = 30)
    private String goodPoint;

    @NotNull
    @Length(max = 30)
    private String badPoint;

    @NotNull
    @Length(max = 30)
    private String studentComment;
}
