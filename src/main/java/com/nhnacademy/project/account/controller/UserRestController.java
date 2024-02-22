package com.nhnacademy.project.account.controller;

import com.nhnacademy.project.account.domain.ResponseMessage;
import com.nhnacademy.project.account.domain.UserDto;
import com.nhnacademy.project.account.domain.UserRegisterDto;
import com.nhnacademy.project.account.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity
                .ok()
                .body(userService.getUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") String userId) {
        return ResponseEntity
                .ok()
                .body(userService.getUser(userId));
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createUser(@Valid @RequestBody UserRegisterDto userRegisterDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message=bindingResult.getAllErrors()
                    .stream()
                    .map(error -> new StringBuilder().append("ObjectName=").append(error.getObjectName())
                            .append(",Message=").append(error.getDefaultMessage())
                            .append(",code=").append(error.getCode()))
                    .collect(Collectors.joining(" | "));
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(message));
        }


        if (Objects.isNull(userService.createUser(userRegisterDto))) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ResponseMessage("이미 존재하는 회원입니다."));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseMessage("회원가입 성공"));
    }

}
