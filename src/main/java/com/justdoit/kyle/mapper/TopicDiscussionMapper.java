package com.justdoit.kyle.mapper;

import com.justdoit.kyle.common.mybatis.BaseMapper;
import com.justdoit.kyle.entity.TopicDiscussion;
import org.apache.ibatis.annotations.Mapper;

/**
 * 课堂讨论持久层
 * @author yangkaile
 * @date 2019-11-21 14:41:05
 */
@Mapper
public interface TopicDiscussionMapper extends BaseMapper<TopicDiscussion> {
}
