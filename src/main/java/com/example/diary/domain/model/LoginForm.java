package com.example.diary.domain.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class LoginForm {

    @NotBlank(groups = ValidGroup1.class)
    @Length(max = 7, min = 3, groups = ValidGroup2.class)
    @Pattern(regexp = "[0-9]+$", groups = ValidGroup2.class)
    private String id;

    @NotBlank(groups = ValidGroup1.class)
    @Length(max = 128, groups = ValidGroup2.class)
    private String password;
}
