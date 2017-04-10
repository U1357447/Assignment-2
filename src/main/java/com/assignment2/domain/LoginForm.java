package com.assignment2.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by u1357447 on 10/04/17.
 */
public class LoginForm {
    @NotEmpty
    String username;
    @NotEmpty
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
