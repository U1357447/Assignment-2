package com.assignment2.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by u1357447 on 07/04/17.
 */
public interface ThreadRepository extends JpaRepository<Thread, Long>{
    @Query("SELECT t FROM Thread t WHERE t.threadID = ?1")
    List<Thread> findAllByUser(Long id);
}
