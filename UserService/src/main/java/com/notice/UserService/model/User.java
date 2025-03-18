package com.notice.UserService.model;

/***
 create type task_status as ENUM ('PENDING','IN_PROGRESS' ,'COMPLETED');

 create type task_priority as ENUM ('HIGH','MEDIUM' ,'LOW');

 create table authors(
 id bigserial primary key,
 username varchar(255) unique not null,
 email varchar(255) not null,
 pswr varchar(255) not null,
 role VARCHAR(50) NOT NULL
 );
 ***/


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name ="authors")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role{
        ADMIN, USER
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
