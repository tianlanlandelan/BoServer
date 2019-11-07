package com.justdoit.kyle.mapper;

import com.justdoit.kyle.common.mybatis.BaseMapper;
import com.justdoit.kyle.entity.TopicInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TopicInfoMapper extends BaseMapper<TopicInfo> {
    String topic = "topic_info";

    @Select("select id,sort,title,title1 from " + topic)
    List<TopicInfo> selectAll();


}
