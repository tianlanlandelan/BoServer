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
@TableAttribute(name = "score_info",comment = "用户得分明细")
public class ScoreInfo extends BaseEntity {

    @FieldAttribute
    @AutoIncrKeyAttribute
    private int id;

    @FieldAttribute
    @IndexAttribute
    private int userId;

    @FieldAttribute
    @IndexAttribute
    private int exerciseId;

    @FieldAttribute
    private int score;

    @FieldAttribute
    private Date createTime = new Date();

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

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
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

    @Override
    public String toString() {
        return "ScoreInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", exerciseId=" + exerciseId +
                ", score=" + score +
                ", createTime=" + createTime +
                '}';
    }
}
