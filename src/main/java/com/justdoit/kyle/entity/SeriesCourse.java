package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.*;

import java.util.Date;

/**
 * 专题课程对应表
 * @author yangkaile
 * @date 2019-11-12 08:44:08
 */
@TableAttribute(name = "series_course",comment = "专题对应的课程")
public class SeriesCourse extends BaseEntity {

    @AutoIncrKeyAttribute
    @FieldAttribute
    private int id;

    @FieldAttribute("专题Id")
    @IndexAttribute
    private int seriesId;

    @FieldAttribute("课程ID")
    @SortAttribute
    private int sort;

    @FieldAttribute("排序")
    @IndexAttribute
    private int courseId;

    @FieldAttribute
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SeriesCourse{" +
                "id=" + id +
                ", seriesId=" + seriesId +
                ", sort=" + sort +
                ", courseId=" + courseId +
                ", createTime=" + createTime +
                '}';
    }
}
