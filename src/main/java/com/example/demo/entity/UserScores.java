package com.example.demo.entity;

import com.example.demo.common.mybatis.annotation.FieldAttribute;
import com.example.demo.common.mybatis.annotation.KeyAttribute;
import com.example.demo.common.mybatis.annotation.TableAttribute;

/**
 * 用户总分数
 * @author yangkaile
 * @2019-10-20 14:01:43
 */
@TableAttribute(name = "user_score",comment = "用户总成绩")
public class UserScores {
    @FieldAttribute("用户ID")
    @KeyAttribute
    private int id;

    @FieldAttribute("总分")
    private int score;

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

    @Override
    public String toString() {
        return "UserScores{" +
                "id=" + id +
                ", score=" + score +
                '}';
    }
}
