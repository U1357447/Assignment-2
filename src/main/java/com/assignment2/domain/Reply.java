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
    Long threadID;

    @NotEmpty
    String heading;
    @NotEmpty
    String content;
    @NotEmpty
    String ownerID;
    @NotEmpty
    String timeCreated;
    @NotEmpty
    String timeUpdated;

    public Long getThreadID() {
        return threadID;
    }

    public void setThreadID(Long threadID) {
        this.threadID = threadID;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
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
