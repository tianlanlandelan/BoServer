package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.annotation.AutoIncrKeyAttribute;
import com.justdoit.kyle.common.mybatis.annotation.FieldAttribute;
import com.justdoit.kyle.common.mybatis.annotation.IndexAttribute;
import com.justdoit.kyle.common.mybatis.annotation.TableAttribute;

import java.util.Date;

/**
 * @author yangkaile
 * @date 2019-11-08 13:57:22
 * 课程章节信息，每个课程下面有多个章节，每个章节下面有多个课时
 */
@TableAttribute(name = "chapter_info",comment = "课程章节详情表")
public class ChapterInfo {
    @FieldAttribute
    @AutoIncrKeyAttribute
    private int id;

    @FieldAttribute("课程id")
    @IndexAttribute
    private int CourseId;

    @FieldAttribute("章节名称")
    private String name;

    @FieldAttribute
    private Date createTime = new Date();



}
