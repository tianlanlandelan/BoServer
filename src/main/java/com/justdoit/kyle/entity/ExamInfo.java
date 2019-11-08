package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.AutoIncrKeyAttribute;
import com.justdoit.kyle.common.mybatis.annotation.FieldAttribute;
import com.justdoit.kyle.common.mybatis.annotation.TableAttribute;

import java.util.Date;

/**
 * @author yangkaile
 * @date 2019-11-08 14:05:01
 * 试卷，定义试卷标题、总分、答题时间
 */
@TableAttribute(name = "exam_info",comment = "试卷信息")
public class ExamInfo extends BaseEntity {
    @AutoIncrKeyAttribute
    @FieldAttribute
    private int id;

    @FieldAttribute("试卷标题")
    private String title;

    @FieldAttribute("总分")
    private int score;

    @FieldAttribute("答题时间")
    private int time;

    @FieldAttribute
    private Date createTime = new Date();

    public ExamInfo() {
    }

    public ExamInfo(String title, int score, int time) {
        this.title = title;
        this.score = score;
        this.time = time;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ExamInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", score=" + score +
                ", time=" + time +
                ", createTime=" + createTime +
                '}';
    }
}
