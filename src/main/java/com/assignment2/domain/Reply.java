package com.assignment2.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by u1357447 on 07/04/17.
 */
@Entity
public class Reply {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long replyID;

    @NotEmpty
    String content;
    @NotEmpty
    String ownerID;
    @NotEmpty
    String threadID;
    @NotEmpty
    String timeCreated;
    @NotEmpty
    String timeUpdated;

    public Long getReplyID() {
        return replyID;
    }

    public void setReplyID(Long replyID) {
        this.replyID = replyID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getThreadID() {
        return threadID;
    }

    public void setThreadID(String threadID) {
        this.threadID = threadID;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(String timeUpdated) {
        this.timeUpdated = timeUpdated;
    }
}
