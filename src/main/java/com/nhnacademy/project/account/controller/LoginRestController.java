package com.nhnacademy.project.account.controller;

import com.nhnacademy.project.account.domain.LoginDto;
import com.nhnacademy.project.account.domain.UserDto;
import com.nhnacademy.project.account.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@Slf4j
@RestController
@RequestMapping("/login")
public class LoginRestController {
    private final UserService userService;

    public LoginRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> doLogin(@RequestBody LoginDto loginDto){
        UserDto userInfo = userService.matches(loginDto);

        if (Objects.isNull(userInfo)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .ok()
                .body(userInfo);
    }

}
