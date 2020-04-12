package com.example.diary.domain.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class LoginForm {

    @NotBlank
    @Length(max = 7)
    private String studentId;

    @NotBlank
    @Length(max = 128)
    private String studentPassword;
}
