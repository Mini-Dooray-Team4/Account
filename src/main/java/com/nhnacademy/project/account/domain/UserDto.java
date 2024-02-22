package com.nhnacademy.project.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private String userId;
    private String userName;
    private String userEmail;
}
