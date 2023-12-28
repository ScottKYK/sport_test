package com.example.sport_test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;
    private boolean state;
    private List<Course> CourseList;

    public User(String username, String password, String email, String role, boolean state,List<Course> CourseList) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.state = state;
        this.CourseList = CourseList;
    }
}
