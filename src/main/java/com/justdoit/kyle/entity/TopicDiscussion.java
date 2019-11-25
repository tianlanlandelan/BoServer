package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.AutoIncrKeyAttribute;
import com.justdoit.kyle.common.mybatis.annotation.FieldAttribute;
import com.justdoit.kyle.common.mybatis.annotation.IndexAttribute;
import com.justdoit.kyle.common.mybatis.annotation.TableAttribute;

import java.util.Date;

/**
 * 课堂讨论
 * @author yangkaile
 * @date 2019-11-21 14:30:38
 */
@TableAttribute(name = "topic_discussion",comment = "课堂讨论")
public class TopicDiscussion extends BaseEntity {
    @AutoIncrKeyAttribute
    @FieldAttribute
    private int id;

    @FieldAttribute("父ID")
    @IndexAttribute
    private Integer fatherId;

    @FieldAttribute("课程id")
    @IndexAttribute
    private Integer courseId;

    @FieldAttribute("课时ID")
    @IndexAttribute
    private Integer topicId;

    @FieldAttribute("用户ID")
    @IndexAttribute
    private Integer userId;

    @FieldAttribute(value = "讨论内容",length = 600)
    private String content;

    @FieldAttribute
    private Date createTime = new Date();

    public TopicDiscussion() {
    }

    public TopicDiscussion(int id) {
        this.id = id;
    }

    public TopicDiscussion(Integer courseId, Integer topicId, Integer userId) {
        this.courseId = courseId;
        this.topicId = topicId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TopicDiscussion{" +
                "id=" + id +
                ", fatherId=" + fatherId +
                ", courseId=" + courseId +
                ", topicId=" + topicId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
