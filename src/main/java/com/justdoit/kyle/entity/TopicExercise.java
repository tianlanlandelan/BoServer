package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.*;

import java.util.Date;

/**
 * @author yangkaile
 * @date 2019-11-08 10:25:40
 */
@TableAttribute(name = "topic_exercise",comment = "课时对应的练习")
public class TopicExercise extends BaseEntity {
    @FieldAttribute
    @AutoIncrKeyAttribute
    private int id;
    @FieldAttribute
    @IndexAttribute
    private Integer topicId;

    @FieldAttribute
    private Integer exerciseId;

    @FieldAttribute("分值、权值")
    private int weighting;

    @FieldAttribute("排序")
    @SortAttribute
    private Integer sort;

    @FieldAttribute
    private Date createTime = new Date();

    public TopicExercise() {
    }

    public TopicExercise(Integer topicId, Integer exerciseId) {
        this.topicId = topicId;
        this.exerciseId = exerciseId;
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

    public int getWeighting() {
        return weighting;
    }

    public void setWeighting(int weighting) {
        this.weighting = weighting;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "TopicExercise{" +
                "id=" + id +
                ", topicId=" + topicId +
                ", exerciseId=" + exerciseId +
                ", weighting=" + weighting +
                ", sort=" + sort +
                ", createTime=" + createTime +
                '}';
    }
}
