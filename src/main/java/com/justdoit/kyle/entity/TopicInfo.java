package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.*;
import java.util.Date;

/**
 * 课时内容
 * @author yangkaile
 * @date 2019-10-15 15:06:32
 */
@TableAttribute(name = "topic_info",comment = "课时内容")
public class TopicInfo extends BaseEntity {

    @FieldAttribute
    @AutoIncrKeyAttribute
    private int id;

    @FieldAttribute("课程id")
    @IndexAttribute
    private Integer courseId;

    @FieldAttribute("章节id")
    @IndexAttribute
    private Integer chapterId;

    @FieldAttribute("排序")
    @SortAttribute
    private Integer sort;

    @FieldAttribute(value = "课时标题",length = 200)
    private String title;

    @FieldAttribute(value = "课时内容",length = 5000,detailed = true)
    private String contentMD;

    @FieldAttribute(value = "课时内容",length = 5000,detailed = true)
    private String content;

    @FieldAttribute(value = "视频地址",length = 400)
    private String videoUrl;

    @FieldAttribute
    private Date createTime = new Date();

    @FieldAttribute("课时类型，0 视频课程；1 练习")
    private int type;

    @FieldAttribute("试题id,如果该课时是练习题时设置该字段")
    private Integer examId;

    @FieldAttribute("课时标签，如：练习课、实验课等")
    private String label;

    public TopicInfo() {
    }

    public TopicInfo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContentMD() {
        return contentMD;
    }

    public void setContentMD(String contentMD) {
        this.contentMD = contentMD;
    }

    @Override
    public String toString() {
        return "TopicInfo{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", chapterId=" + chapterId +
                ", sort=" + sort +
                ", title='" + title + '\'' +
                ", contentMD='" + contentMD + '\'' +
                ", content='" + content + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", createTime=" + createTime +
                ", type=" + type +
                ", examId=" + examId +
                ", label='" + label + '\'' +
                '}';
    }
}
