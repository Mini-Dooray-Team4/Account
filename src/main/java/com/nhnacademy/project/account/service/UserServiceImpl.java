package com.nhnacademy.project.account.service;

import com.nhnacademy.project.account.domain.*;
import com.nhnacademy.project.account.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.getAllBy();
    }


    @Override
    public UserDto getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User createUser(UserRegisterDto userRegisterDto) {
        User user = new User();
        BeanUtils.copyProperties(userRegisterDto, user);
        user.setUserStatus(UserStatus.JOIN);
        if (userRepository.existsById(user.getUserId())) {
            return null;
        }
        return userRepository.save(user);
    }

    @Override
    public UserDto matches(LoginDto loginDto) {
        return userRepository.existsByUserIdAndUserPassword(loginDto.getUserId(),loginDto.getUserPassword());
    }
}
