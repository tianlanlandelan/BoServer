package com.example.demo.entity;

import com.example.demo.common.mybatis.BaseEntity;
import com.example.demo.common.mybatis.annotation.AutoIncrKeyAttribute;
import com.example.demo.common.mybatis.annotation.FieldAttribute;
import com.example.demo.common.mybatis.annotation.SortAttribute;
import com.example.demo.common.mybatis.annotation.TableAttribute;

import java.util.Date;

/**
 * @author yangkaile
 * @date 2019-10-15 15:06:32
 */
@TableAttribute(name = "topic_info",comment = "课程详情")
public class TopicInfo extends BaseEntity {

    @FieldAttribute
    @AutoIncrKeyAttribute
    private int id;

    @FieldAttribute("排序")
    @SortAttribute
    private Integer sort;

    @FieldAttribute(value = "课程标题",length = 200)
    private String title;

    @FieldAttribute(value = "课程内容",length = 5000)
    private String content;

    @FieldAttribute(value = "视频标题",length = 200)
    private String videoTitle;

    @FieldAttribute(value = "视频地址",length = 400)
    private String videoUrl;

    @FieldAttribute
    private Date createTime = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TopicInfo{" +
                "id=" + id +
                ", sort=" + sort +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
