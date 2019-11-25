package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.*;

import java.util.Date;

/**
 * 章节目录
 * @author yangkaile
 * @date 2019-11-08 13:57:22
 *
 */
@TableAttribute(name = "chapter_info",comment = "笔记本的目录")
public class Chapter extends BaseEntity {
    @FieldAttribute
    @AutoIncrKeyAttribute
    private int id;

    @FieldAttribute("笔记本Id")
    @IndexAttribute
    private int notesId;

    @FieldAttribute("目录名称")
    private String name;

    @FieldAttribute("排序")
    @SortAttribute
    private int sort;

    @FieldAttribute
    private Date createTime = new Date();

    public Chapter() {
    }
    public Chapter(int id) {
        this.id = id;
    }
    public Chapter(int notesId, String name) {
        this.notesId = notesId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNotesId() {
        return notesId;
    }

    public void setNotesId(int notesId) {
        this.notesId = notesId;
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
        return "Chapter{" +
                "id=" + id +
                ", notesId=" + notesId +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                ", createTime=" + createTime +
                '}';
    }
}
