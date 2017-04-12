package com.assignment2.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by u1357447 on 07/04/17.
 */
@Entity
public class Thread {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long threadID;

    @NotEmpty
    String heading;
    @NotEmpty
    String content;
    @NotEmpty
    String ownerID;
    @NotEmpty
    String ownerName;
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

    public String getOwnerName() { return ownerName; }

    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

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

    public List<Reply> getReplies() {
        return replies;
    }

    public void clearReplies() { this.replies.clear(); }

    @OneToMany
    private List<Reply> replies = new ArrayList<Reply>();
}
