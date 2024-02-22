package com.nhnacademy.project.account.service;

import com.nhnacademy.project.account.domain.LoginDto;
import com.nhnacademy.project.account.domain.User;
import com.nhnacademy.project.account.domain.UserDto;
import com.nhnacademy.project.account.domain.UserRegisterDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto getUser(String userId);

    User createUser(UserRegisterDto userRegisterDto);

    UserDto matches(LoginDto loginDto);

}
