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
}
