package com.nhnacademy.project.account.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.project.account.domain.*;
import com.nhnacademy.project.account.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserRestControllerTest {
    @MockBean
    UserRepository userRepository;
    @Autowired
    MockMvc mockMvc;


    @Test
    void testGetUsers() throws Exception{
        UserDto testUser = new UserDto("test", "테스트", "test@nhn.com");

        given(userRepository.getAllBy()).willReturn(List.of(testUser));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].userId", equalTo("test")));
    }

    @Test
    void testGetUser() throws Exception{
        UserDto testUser = new UserDto("test", "테스트", "test@nhn.com");
        String testId = "test";

        given(userRepository.findByUserId(anyString())).willReturn(testUser);

        mockMvc.perform(get("/users/{id}", testId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName", equalTo("테스트")));
    }

    @Test
    void testCreateUser() throws Exception {
        User user = new User("test", "123", "테스트", "test@nhn.com", UserStatus.JOIN);
        UserRegisterDto userRegisterDto = new UserRegisterDto("test", "123", "테스트", "test@nhn.com");
        ObjectMapper objectMapper = new ObjectMapper();
        given(userRepository.existsById(anyString())).willReturn(false);

        given(userRepository.save(any())).willReturn(user);

        mockMvc.perform(
                        post("/users")
                                .content(objectMapper.writeValueAsString(userRegisterDto))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", equalTo("회원가입 성공")));
    }

}