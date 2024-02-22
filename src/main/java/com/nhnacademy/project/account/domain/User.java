package com.nhnacademy.project.account.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
public class User{

    @Id
    @Column(name = "user_id")
    private String userId;

    @NotNull
    @Column(name = "user_password")
    private String userPassword;
    @NotNull
    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    @ColumnDefault("'JOIN'")
    private UserStatus userStatus;

}
