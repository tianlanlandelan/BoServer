package com.example.demo.view;

import com.example.demo.entity.Rate;
import com.example.demo.entity.UserInfo;

/**
 * @author yangkaile
 * @date 2019-10-22 17:29:13
 *
 * 用户得分情况，用于在排行榜上展示
 */
public class UserScores {

    private int id;

    private int score;

    private int sort;

    private String firstName;

    private String lastName;

    private int avatarId;

    /**
     * 相对于第1名得分的百分比
     */
    private int percentage;

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

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
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
                ", percentage=" + percentage +
                '}';
    }
}
