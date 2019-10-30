package com.example.demo.mapper;

import com.example.demo.common.mybatis.BaseMapper;
import com.example.demo.entity.UserExercise;
import com.example.demo.entity.UserTopic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserTopicMapper extends BaseMapper<UserTopic> {

    @Select("select userId,topicId,time " +
            "from user_topic,user_info " +
            "where user_info.id = user_topic.userId and user_info.type = #{type}")
    List<UserTopic> selectByUserType(int type);
}
