package com.nhnacademy.project.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDto {
    private String userId;
    private String userPassword;
}
