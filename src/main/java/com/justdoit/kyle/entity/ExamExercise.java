package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.*;

import java.util.Date;

/**
 * @author yangkaile
 * @date 2019-11-08 14:02:09
 * 试卷中的试题，多个试题构成一套试卷
 */
@TableAttribute(name = "exam_exercise",comment = "试卷中的试题")
public class ExamExercise extends BaseEntity {
    @FieldAttribute
    @AutoIncrKeyAttribute
    private int id;

    @FieldAttribute("课程id")
    @IndexAttribute
    private int examId;

    @FieldAttribute("习题ID")
    private int exerciseId;

    @FieldAttribute("分值")
    private int score;

    @FieldAttribute("排序")
    @SortAttribute
    private int sort;

    @FieldAttribute
    private Date createTime = new Date();

    public ExamExercise() {
    }

    public ExamExercise(int examId, int exerciseId, int score) {
        this.examId = examId;
        this.exerciseId = exerciseId;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
        return "ExamExercise{" +
                "id=" + id +
                ", examId=" + examId +
                ", exerciseId=" + exerciseId +
                ", score=" + score +
                ", sort=" + sort +
                ", createTime=" + createTime +
                '}';
    }
}
