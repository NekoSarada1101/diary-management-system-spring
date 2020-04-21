package com.example.diary.domain.model;

import lombok.Data;

@Data
public class Diary {
    private String classCode;
    private String insertDate;
    private String studentId;
    private String goodPoint;
    private String badPoint;
    private String studentComment;
    private String teacherComment;
}
