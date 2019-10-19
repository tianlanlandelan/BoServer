package com.example.demo.mapper;

import com.example.demo.common.mybatis.BaseMapper;
import com.example.demo.entity.TopicInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TopicInfoMapper extends BaseMapper<TopicInfo> {
    String topic = "topic_info";

    @Select("select id,sort,title,videoTitle from " + topic)
    List<TopicInfo> selectAll();


}
