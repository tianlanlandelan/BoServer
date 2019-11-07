package com.justdoit.kyle.view;

import com.justdoit.kyle.entity.Rate;
import com.justdoit.kyle.entity.UserInfo;

/**
 * @author yangkaile
 * @date 2019-10-22 17:29:13
 *
 * 用户得分情况，用于在排行榜上展示
 */
public class UserScores {

    /**
     * 用户id
     */
    private int id;
    private String sid;
    private String email;
    private int topicId;
    private String topicTitle;
    private int exerciseId;
    private String exerciseTitle;
    private String feedback1;
    private String feedback2;
    private int type;

    /**
     * 用户得分
     */
    private int score;

    /**
     * 用户排名
     */
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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getFeedback1() {
        return feedback1;
    }

    public void setFeedback1(String feedback1) {
        this.feedback1 = feedback1;
    }

    public String getFeedback2() {
        return feedback2;
    }

    public void setFeedback2(String feedback2) {
        this.feedback2 = feedback2;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getExerciseTitle() {
        return exerciseTitle;
    }

    public void setExerciseTitle(String exerciseTitle) {
        this.exerciseTitle = exerciseTitle;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserScores{" +
                "id=" + id +
                ", sid='" + sid + '\'' +
                ", email='" + email + '\'' +
                ", topicId=" + topicId +
                ", topicTitle='" + topicTitle + '\'' +
                ", exerciseId=" + exerciseId +
                ", exerciseTitle='" + exerciseTitle + '\'' +
                ", feedback1='" + feedback1 + '\'' +
                ", feedback2='" + feedback2 + '\'' +
                ", type=" + type +
                ", score=" + score +
                ", sort=" + sort +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", avatarId=" + avatarId +
                ", percentage=" + percentage +
                '}';
    }
}
