package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.*;

/**
 * @author yangkaile
 * @date 2019-11-08 14:06:21
 * 用户学习进度，记录用户在某课程中的学习进度
 */
@TableAttribute(name="rate",comment = "学习进度表")
public class Rate extends BaseEntity {
    @AutoIncrKeyAttribute
    @FieldAttribute
    private int id;

    @FieldAttribute("用户id")
    @IndexAttribute
    private int userId;

    @FieldAttribute("课程Id")
    @IndexAttribute
    private int courseId;

    @FieldAttribute("用户当前在学的课时")
    private Integer topicId;

    @FieldAttribute("课程是否学完了,0 未学完；1 学完了")
    private int isOver;


    public static int OVER;

    public Rate() {
    }

    public Rate(int userId, int courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public int getIsOver() {
        return isOver;
    }

    public void setIsOver(int isOver) {
        this.isOver = isOver;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", userId=" + userId +
                ", courseId=" + courseId +
                ", topicId=" + topicId +
                ", isOver=" + isOver +
                '}';
    }
}
