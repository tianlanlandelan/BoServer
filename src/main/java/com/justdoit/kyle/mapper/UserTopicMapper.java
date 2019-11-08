package com.justdoit.kyle.mapper;

import com.justdoit.kyle.common.mybatis.BaseMapper;
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
