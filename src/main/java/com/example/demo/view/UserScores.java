package com.example.demo.view;

import com.example.demo.entity.Rate;
import com.example.demo.entity.UserInfo;

public class UserScores {

    private int id;

    private int score;

    private int sort;

    private String firstName;

    private String lastName;

    private int avatarId;

    public UserScores() {
    }

    public UserScores(UserInfo userInfo, Rate rate ) {
        id = userInfo.getId();
        score = rate.getScore();
        firstName = userInfo.getFirstName();
        lastName = userInfo.getLastName();
        avatarId = userInfo.getAvatarId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    @Override
    public String toString() {
        return "UserScores{" +
                "id=" + id +
                ", score=" + score +
                ", sort=" + sort +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", avatarId=" + avatarId +
                '}';
    }
}
