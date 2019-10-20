package com.example.demo.service;

import com.example.demo.common.response.ResultData;
import com.example.demo.entity.UserTopic;
import com.example.demo.mapper.UserTopicMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserTopicService {
    @Resource
    private UserTopicMapper mapper;

    public ResultData save(UserTopic userTopic){
        userTopic.setBaseKyleUseAnd(true);
        List<UserTopic> list = mapper.baseSelectByCondition(userTopic);
        if(list == null || list .size() < 1){
            mapper.baseInsertAndReturnKey(userTopic);
        }
        return ResultData.success();
    }

}
