package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.AutoIncrKeyAttribute;
import com.justdoit.kyle.common.mybatis.annotation.FieldAttribute;
import com.justdoit.kyle.common.mybatis.annotation.IndexAttribute;
import com.justdoit.kyle.common.mybatis.annotation.TableAttribute;

import java.util.Date;

/**
 * 用户学习情况
 * @author yangkaile
 * @date 2019-10-29 08:45:20
 */
@TableAttribute(name = "user_topic",comment = "用户学习记录")
public class UserTopic extends BaseEntity {
    @FieldAttribute
    @AutoIncrKeyAttribute
    private int id;

    @FieldAttribute("用户Id")
    @IndexAttribute
    private Integer userId;

    @FieldAttribute("课程ID")
    @IndexAttribute
    private Integer topicId;

    @FieldAttribute("学习时间")
    private int time;

    @FieldAttribute
    private Date createTime = new Date();

    public UserTopic() {
    }

    public UserTopic(Integer userId, Integer topicId) {
        this.userId = userId;
        this.topicId = topicId;
    }

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

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserTopic{" +
                "id=" + id +
                ", userId=" + userId +
                ", topicId=" + topicId +
                ", time=" + time +
                ", createTime=" + createTime +
                '}';
    }
}
