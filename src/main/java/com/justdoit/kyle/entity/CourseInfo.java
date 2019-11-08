package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.*;

import java.util.Date;

/**
 * @author yangkaile
 * @date 2019-11-06 14:02:26
 * 课程，一个课程下有多个课时
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
    private Float price;

    @FieldAttribute()
    @SortAttribute
    private Date createTime;


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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
                '}';
    }
}
