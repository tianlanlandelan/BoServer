package com.example.demo.entity;

import com.example.demo.common.mybatis.BaseEntity;
import com.example.demo.common.mybatis.annotation.AutoIncrKeyAttribute;
import com.example.demo.common.mybatis.annotation.FieldAttribute;
import com.example.demo.common.mybatis.annotation.TableAttribute;

/**
 * @author yangkaile
 * @date 2019-11-06 14:02:26
 * 课程，一个课程下有多个课时
 */
@TableAttribute(name = "course_info",comment = "课程，一个课程下面有多个课时")
public class CourseInfo extends BaseEntity {
    @FieldAttribute
    @AutoIncrKeyAttribute
    private int id;

    @FieldAttribute(value = "标题",length = 200)
    private String title;

    @FieldAttribute(value = "副标题",length = 200)
    private String subTitle;

    @FieldAttribute(value = "课程介绍里要展示的图片",length = 200)
    private String img;

    @FieldAttribute(value = "课程介绍",length = 4000)
    private String overview;

    @FieldAttribute("价格")
    private Float price;





}
