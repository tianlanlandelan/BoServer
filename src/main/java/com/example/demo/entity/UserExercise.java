package com.example.demo.entity;

import com.example.demo.common.mybatis.BaseEntity;
import com.example.demo.common.mybatis.annotation.AutoIncrKeyAttribute;
import com.example.demo.common.mybatis.annotation.FieldAttribute;
import com.example.demo.common.mybatis.annotation.IndexAttribute;
import com.example.demo.common.mybatis.annotation.TableAttribute;

import java.util.Date;

/**
 * 用户得分，记录每一题的得分
 * @author yangkaile
 * @date 2019-10-20 14:06:41
 */
@TableAttribute(name = "user_exercise",comment = "用户得分明细")
public class UserExercise extends BaseEntity {

    @FieldAttribute
    @AutoIncrKeyAttribute
    private int id;

    @FieldAttribute("用户id")
    @IndexAttribute
    private Integer userId;

    @FieldAttribute("练习题id")
    @IndexAttribute
    private Integer exerciseId;

    @FieldAttribute("练习题答案")
    private String answer;

    @FieldAttribute("得分")
    private int score;

    @FieldAttribute("答题所花费的时间")
    private int time;

    @FieldAttribute("用户的回答")
    private String userAnswer;

    @FieldAttribute
    private Date createTime = new Date();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "UserExercise{" +
                "id=" + id +
                ", userId=" + userId +
                ", exerciseId=" + exerciseId +
                ", answer='" + answer + '\'' +
                ", score=" + score +
                ", time=" + time +
                ", userAnswer='" + userAnswer + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
