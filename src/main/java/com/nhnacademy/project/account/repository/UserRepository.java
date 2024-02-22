package com.nhnacademy.project.account.repository;

import com.nhnacademy.project.account.domain.User;
import com.nhnacademy.project.account.domain.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<UserDto> getAllBy();

    UserDto findByUserId(String userId);
    @Query(value = "select new com.nhnacademy.project.account.domain.UserDto(u.userId,u.userName,u.userEmail) " +
            "from User u " +
            "where u.userId=?1 and u.userPassword=?2")
    UserDto existsByUserIdAndUserPassword(String userId, String userPassword);

}

