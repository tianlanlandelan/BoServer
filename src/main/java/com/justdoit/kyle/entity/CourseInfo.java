package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.*;

import java.util.Date;

/**
 * @author yangkaile
 * @date 2019-11-06 14:02:26
 * 课程，一个课程下有多个课时，课程可以单独存在，也可以属于某个专题
 */
@TableAttribute(name = "course_info",comment = "课程，一个课程下面有多个课时")
public class CourseInfo extends BaseEntity {
    @FieldAttribute
    @AutoIncrKeyAttribute
    private int id;

    @FieldAttribute(value = "标题",length = 200)
    private String title;

    @FieldAttribute(value = "副标题",length = 200)
    private String subTitle;

    @FieldAttribute(value = "课程介绍里要展示的图片",length = 200)
    private String img;

    @FieldAttribute(value = "课程介绍",length = 4000)
    private String overview;

    @FieldAttribute("价格")
    private float price;

    @FieldAttribute()
    @SortAttribute
    private Date createTime = new Date();

    @FieldAttribute("课程状态: 0 正常，1 未发布，2 删除")
    @IndexAttribute
    private int status;

    @FieldAttribute("课时数")
    private int topicNumber;

    @FieldAttribute("已学习人数")
    private int gotNumber;

    @FieldAttribute("评价数")
    private int evaluatedNumber;

    @FieldAttribute("评分 0~5分")
    private float evaluation;

    public static final int SAVE = 1;
    public static final int DELETE = 2;

    public CourseInfo() {
    }

    public CourseInfo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTopicNumber() {
        return topicNumber;
    }

    public void setTopicNumber(int topicNumber) {
        this.topicNumber = topicNumber;
    }

    public int getGotNumber() {
        return gotNumber;
    }

    public void setGotNumber(int gotNumber) {
        this.gotNumber = gotNumber;
    }

    public int getEvaluatedNumber() {
        return evaluatedNumber;
    }

    public void setEvaluatedNumber(int evaluatedNumber) {
        this.evaluatedNumber = evaluatedNumber;
    }

    public float getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(float evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public String toString() {
        return "CourseInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", img='" + img + '\'' +
                ", overview='" + overview + '\'' +
                ", price=" + price +
                ", createTime=" + createTime +
                ", status=" + status +
                ", topicNumber=" + topicNumber +
                ", gotNumber=" + gotNumber +
                ", evaluatedNumber=" + evaluatedNumber +
                ", evaluation=" + evaluation +
                '}';
    }
}
