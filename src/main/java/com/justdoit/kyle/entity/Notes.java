package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.*;

import java.util.Date;

/**
 * 笔记本
 * @author yangkaile
 * @date 2019-11-06 14:02:26
 */
@TableAttribute(name = "notes_info",comment = "笔记本")
public class Notes extends BaseEntity {
    @FieldAttribute
    @AutoIncrKeyAttribute
    private int id;

    @FieldAttribute("用户Id")
    @IndexAttribute
    private Integer userId;

    @FieldAttribute(value = "标题",length = 200)
    private String title;

    @FieldAttribute(value = "副标题",length = 200)
    private String subTitle;

    @FieldAttribute("创建日期")
    private Date createTime = new Date();

    @FieldAttribute("笔记数量")
    private int noteNumber;

    @FieldAttribute("排序")
    private int sort;

    public Notes() {
    }

    public Notes(int id) {
        this.id = id;
    }

    public Notes(int id, Integer userId) {
        this.id = id;
        this.userId = userId;
    }

    public Notes(Integer userId, String title) {
        this.userId = userId;
        this.title = title;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getNoteNumber() {
        return noteNumber;
    }

    public void setNoteNumber(int noteNumber) {
        this.noteNumber = noteNumber;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", createTime=" + createTime +
                ", noteNumber=" + noteNumber +
                ", sort=" + sort +
                '}';
    }
}
