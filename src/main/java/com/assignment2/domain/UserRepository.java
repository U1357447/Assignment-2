package com.assignment2.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by u1357447 on 07/04/17.
 */
public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM User u WHERE u.username = ?1 and u.password = ?2")
    List<User> checkUserInput(String username, String password);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    List<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User getUser(String username);

    @Query("SELECT u FROM User u WHERE u.username LIKE ?1")
    List<User> searchUsers(String username);
}
