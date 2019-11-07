package com.example.demo.entity;

import com.example.demo.common.mybatis.BaseEntity;
import com.example.demo.common.mybatis.annotation.AutoIncrKeyAttribute;
import com.example.demo.common.mybatis.annotation.FieldAttribute;
import com.example.demo.common.mybatis.annotation.TableAttribute;

/**
 * @author yangkaile
 * @date 2019-11-06 14:01:45
 */
@TableAttribute(name = "series_info",comment = "专题，一个专题下面有多个课程")
public class SeriesInfo extends BaseEntity {
    @FieldAttribute
    @AutoIncrKeyAttribute
    private int id;

}
