package com.example.diary.domain.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class DiaryInsertForm {

    @NotBlank(groups = ValidGroup1.class)
    @Length(max = 30, groups = ValidGroup2.class)
    private String goodPoint;

    @NotBlank(groups = ValidGroup1.class)
    @Length(max = 30, groups = ValidGroup2.class)
    private String badPoint;

    @NotBlank(groups = ValidGroup1.class)
    @Length(max = 30, groups = ValidGroup2.class)
    private String studentComment;
}
