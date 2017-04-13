package com.assignment2.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by u1357447 on 07/04/17.
 */
public interface ReplyRepository extends JpaRepository<Reply, Long>{
    @Query("SELECT r FROM Reply r WHERE r.ownerID = ?1")
    List<Reply> findAllByUser(String id);
}
