package com.example.personal_finance_tracker_api.user.entity;

import com.example.personal_finance_tracker_api.common.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users_table")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name = "assigning_role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
