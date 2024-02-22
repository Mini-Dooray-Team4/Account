package com.nhnacademy.project.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.project.account.domain.LoginDto;
import com.nhnacademy.project.account.domain.UserDto;
import com.nhnacademy.project.account.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoginRestControllerTest {
    @MockBean
    UserRepository userRepository;
    @Autowired
    MockMvc mockMvc;

    UserDto testUser;
    LoginDto loginDto;
    ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        testUser = new UserDto("test", "테스트", "test@nhn.com");
        loginDto = new LoginDto("test", "123");
        objectMapper = new ObjectMapper();
    }

    @Test
    void testDoLogin() throws Exception {

        given(userRepository.existsByUserIdAndUserPassword(anyString(), anyString()))
                .willReturn(testUser);

        mockMvc.perform(post("/login")
                        .content(objectMapper.writeValueAsBytes(loginDto))
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName", equalTo("테스트")));
    }
}