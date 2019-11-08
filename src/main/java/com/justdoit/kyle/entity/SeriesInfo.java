package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.AutoIncrKeyAttribute;
import com.justdoit.kyle.common.mybatis.annotation.FieldAttribute;
import com.justdoit.kyle.common.mybatis.annotation.SortAttribute;
import com.justdoit.kyle.common.mybatis.annotation.TableAttribute;

import java.util.Date;

/**
 * @author yangkaile
 * @date 2019-11-06 14:01:45
 */
@TableAttribute(name = "series_info",comment = "专题，一个专题下面有多个课程")
public class SeriesInfo extends BaseEntity {
    @FieldAttribute
    @AutoIncrKeyAttribute
    private int id;

    @FieldAttribute(value = "标题",length = 200)
    private String title;

    @FieldAttribute(value = "副标题",length = 200)
    private String subTitle;

    @FieldAttribute(value = "系列介绍里要展示的图片",length = 200)
    private String img;

    @FieldAttribute(value = "系列介绍",length = 4000)
    private String overview;

    @FieldAttribute()
    @SortAttribute
    private Date createTime;

    public SeriesInfo() {
    }

    public SeriesInfo(int id) {
        this.id = id;
    }

    public SeriesInfo(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SeriesInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", img='" + img + '\'' +
                ", overview='" + overview + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
