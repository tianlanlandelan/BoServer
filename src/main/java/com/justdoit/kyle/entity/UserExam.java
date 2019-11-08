package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.*;

import java.util.Date;

/**
 * @author yangkaile
 * @date 2019-11-08 14:18:12
 * 考试记录表，记录用户考试情况：得分、用时等
 */
@TableAttribute(name = "user_exam",comment = "考试记录表，记录用户考试情况")
public class UserExam extends BaseEntity {
    @AutoIncrKeyAttribute
    @FieldAttribute
    private int id;

    @FieldAttribute("用户id")
    @IndexAttribute
    private int userId;

    @FieldAttribute("试卷ID")
    @IndexAttribute
    private int examId;

    @FieldAttribute("考试用时")
    private int time;

    @FieldAttribute("得分")
    @SortAttribute
    private int score;

    @FieldAttribute
    private Date createTime = new Date();

    public UserExam() {
    }

    public UserExam(int userId, int examId) {
        this.userId = userId;
        this.examId = examId;
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

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserExam{" +
                "id=" + id +
                ", userId=" + userId +
                ", examId=" + examId +
                ", time=" + time +
                ", score=" + score +
                ", createTime=" + createTime +
                '}';
    }
}
