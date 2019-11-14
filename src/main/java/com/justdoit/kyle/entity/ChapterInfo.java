package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.*;

import java.util.Date;

/**
 * @author yangkaile
 * @date 2019-11-08 13:57:22
 * 课程章节信息，每个课程下面有多个章节，每个章节下面有多个课时
 */
@TableAttribute(name = "chapter_info",comment = "课程章节详情表")
public class ChapterInfo extends BaseEntity {
    @FieldAttribute
    @AutoIncrKeyAttribute
    private int id;

    @FieldAttribute("课程id")
    @IndexAttribute
    private int courseId;

    @FieldAttribute("章节名称")
    private String name;

    @FieldAttribute("排序")
    @SortAttribute
    private int sort;

    @FieldAttribute
    private Date createTime = new Date();

    public ChapterInfo() {
    }
    public ChapterInfo(int id) {
        this.id = id;
    }
    public ChapterInfo(int courseId, String name) {
        this.courseId = courseId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ChapterInfo{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                ", createTime=" + createTime +
                '}';
    }
}
