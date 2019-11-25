package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.*;
import java.util.Date;

/**
 * 笔记
 * @author yangkaile
 * @date 2019-10-15 15:06:32
 */
@TableAttribute(name = "topic_info",comment = "笔记")
public class Note extends BaseEntity {

    @FieldAttribute
    @AutoIncrKeyAttribute
    private int id;

    @FieldAttribute("用户ID")
    @IndexAttribute
    private Integer userId;

    @FieldAttribute("笔记本Id")
    @IndexAttribute
    private Integer notesId;

    @FieldAttribute("目录Id")
    @IndexAttribute
    private Integer chapterId;

    @FieldAttribute(value = "标题",length = 200)
    private String title;

    @FieldAttribute(value = "MD内容",length = 5000,detailed = true)
    private String contentMD;

    @FieldAttribute(value = "内容",length = 5000,detailed = true)
    private String content;

    @FieldAttribute("排序")
    @SortAttribute
    private Integer sort;

    @FieldAttribute
    private Date createTime = new Date();



    public Note() {
    }

    public Note(int id) {
        this.id = id;
    }

    public Note(Integer userId, Integer notesId, Integer chapterId, String title) {
        this.userId = userId;
        this.notesId = notesId;
        this.chapterId = chapterId;
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

    public Integer getNotesId() {
        return notesId;
    }

    public void setNotesId(Integer notesId) {
        this.notesId = notesId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentMD() {
        return contentMD;
    }

    public void setContentMD(String contentMD) {
        this.contentMD = contentMD;
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
        return "Note{" +
                "id=" + id +
                ", userId=" + userId +
                ", notesId=" + notesId +
                ", chapterId=" + chapterId +
                ", sort=" + sort +
                ", title='" + title + '\'' +
                ", contentMD='" + contentMD + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
