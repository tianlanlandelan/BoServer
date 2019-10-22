package com.example.demo.entity;

import com.example.demo.common.mybatis.BaseEntity;
import com.example.demo.common.mybatis.annotation.FieldAttribute;
import com.example.demo.common.mybatis.annotation.KeyAttribute;
import com.example.demo.common.mybatis.annotation.TableAttribute;

@TableAttribute(name="rate",comment = "学习进度表")
public class Rate extends BaseEntity {

    @KeyAttribute
    @FieldAttribute("用户id")
    private int id;

    @FieldAttribute("用户当前在学的课程")
    private Integer topicId;

    @FieldAttribute("课程视频是否看过")
    private int videoStatus;

    @FieldAttribute("用户当前在做的练习")
    private Integer exerciseId;

    @FieldAttribute("用户答题剩余时间")
    private Integer timer;

    @FieldAttribute("课程是否学完了")
    private int topicOver;

    @FieldAttribute("总分")
    private int score;


    public static final int VODEO_OVER = 1;

    public static final int OVER = 1;

    public Rate() {
    }

    public Rate(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }


    public Integer getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Integer getTimer() {
        return timer;
    }

    public void setTimer(Integer timer) {
        this.timer = timer;
    }

    public int getTopicOver() {
        return topicOver;
    }

    public void setTopicOver(int topicOver) {
        this.topicOver = topicOver;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getVideoStatus() {
        return videoStatus;
    }

    public void setVideoStatus(int videoStatus) {
        this.videoStatus = videoStatus;
    }


    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", topicId=" + topicId +
                ", videoStatus=" + videoStatus +
                ", exerciseId=" + exerciseId +
                ", timer=" + timer +
                ", topicOver=" + topicOver +
                ", score=" + score +
                '}';
    }
}
