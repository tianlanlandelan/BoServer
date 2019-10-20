package com.example.demo.entity;

import com.example.demo.common.mybatis.BaseEntity;
import com.example.demo.common.mybatis.annotation.AutoIncrKeyAttribute;
import com.example.demo.common.mybatis.annotation.FieldAttribute;
import com.example.demo.common.mybatis.annotation.IndexAttribute;
import com.example.demo.common.mybatis.annotation.TableAttribute;

@TableAttribute(name = "user_topic",comment = "记录用户学过的课程")
public class UserTopic extends BaseEntity {

    @FieldAttribute
    @AutoIncrKeyAttribute
    public int id;

    @FieldAttribute
    @IndexAttribute
    public int userId;

    @FieldAttribute
    public int topicId;

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

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "UserTopic{" +
                "id=" + id +
                ", userId=" + userId +
                ", topicId=" + topicId +
                '}';
    }
}
