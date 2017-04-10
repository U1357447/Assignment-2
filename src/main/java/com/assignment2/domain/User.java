package com.assignment2.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by u1357447 on 07/04/17.
 */
@Entity @Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}, name = "notunique")})
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty @Column(unique = true)
    String Username;
    @NotEmpty
    String firstname;
    @NotEmpty
    String lastname;
    @NotEmpty
    String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) { Username = username; }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
