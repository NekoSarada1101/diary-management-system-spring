package com.example.diary.domain.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class DiaryForm {

    private String insertDate;

    private String classCode;

    @NotBlank(groups = ValidGroup1.class)
    @Length(max = 30, groups = ValidGroup2.class)
    private String goodPoint;

    @NotBlank(groups = ValidGroup1.class)
    @Length(max = 30, groups = ValidGroup2.class)
    private String badPoint;

    @NotBlank(groups = ValidGroup1.class)
    @Length(max = 30, groups = ValidGroup2.class)
    private String studentComment;

    @Length(max = 30, groups = ValidGroup2.class)
    private String teacherComment;
}
