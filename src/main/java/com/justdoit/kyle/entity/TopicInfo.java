package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.AutoIncrKeyAttribute;
import com.justdoit.kyle.common.mybatis.annotation.FieldAttribute;
import com.justdoit.kyle.common.mybatis.annotation.SortAttribute;
import com.justdoit.kyle.common.mybatis.annotation.TableAttribute;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 课程，包含内容和视频
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

    @FieldAttribute(value = "课程标题1",length = 200)
    private String title1;

    @FieldAttribute(value = "课程内容",length = 5000)
    private String content;

    @FieldAttribute(value = "ppt预览地址",length = 400)
    private String pptUrl;

    @FieldAttribute(value = "视频地址",length = 400)
    private String videoUrl;

    @FieldAttribute
    private Date createTime = new Date();

    /**
     * 课程进行状态
     */
    private int status;

    /**
     * 视频观看状态
     */
    private int videoStatus;

    /**
     * 是不是当前要学习的课程
     */
    private boolean current = false;
    private Integer timer;

    private List<ExerciseInfo> list = new ArrayList<>();

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

    public List<ExerciseInfo> getList() {
        return list;
    }

    public void setList(List<ExerciseInfo> list) {
        this.list = list;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public int getVideoStatus() {
        return videoStatus;
    }

    public void setVideoStatus(int videoStatus) {
        this.videoStatus = videoStatus;
    }

    public String getPptUrl() {
        return pptUrl;
    }

    public void setPptUrl(String pptUrl) {
        this.pptUrl = pptUrl;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public Integer getTimer() {
        return timer;
    }

    public void setTimer(Integer timer) {
        this.timer = timer;
    }

    @Override
    public String toString() {
        return "TopicInfo{" +
                "id=" + id +
                ", sort=" + sort +
                ", title='" + title + '\'' +
                ", title1='" + title1 + '\'' +
                ", content='" + content + '\'' +
                ", pptUrl='" + pptUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", videoStatus=" + videoStatus +
                ", current=" + current +
                ", timer=" + timer +
                ", list=" + list +
                '}';
    }
}
