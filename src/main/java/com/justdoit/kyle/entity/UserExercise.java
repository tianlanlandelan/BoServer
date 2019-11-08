package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.AutoIncrKeyAttribute;
import com.justdoit.kyle.common.mybatis.annotation.FieldAttribute;
import com.justdoit.kyle.common.mybatis.annotation.IndexAttribute;
import com.justdoit.kyle.common.mybatis.annotation.TableAttribute;

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

    @FieldAttribute("练习题Id")
    @IndexAttribute
    private Integer exerciseId;

    @FieldAttribute("用户的回答")
    private String answer;

    public UserExercise() {
    }

    public UserExercise(Integer userId, Integer exerciseId, String answer) {
        this.userId = userId;
        this.exerciseId = exerciseId;
        this.answer = answer;
    }

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserExercise{" +
                "id=" + id +
                ", userId=" + userId +
                ", exerciseId=" + exerciseId +
                ", answer='" + answer + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
