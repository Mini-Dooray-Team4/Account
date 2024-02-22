package com.nhnacademy.project.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class UserRegisterDto {
    @NotBlank
    private String userId;

    @Length(min = 3, max = 30)
    @NotBlank
    private String userPassword;

    @NotBlank
    private String userName;

    @NotBlank
    private String userEmail;

}
