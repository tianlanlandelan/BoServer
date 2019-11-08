package com.justdoit.kyle.service;

import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.entity.*;
import com.justdoit.kyle.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AdminService {
    @Resource
    private UserExerciseMapper userExerciseMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private RateMapper rateMapper;

    @Resource
    private TopicInfoMapper topicInfoMapper;

    @Resource
    private ExerciseMapper exerciseMapper;


    public ResultData getByEmail(String email){
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
        return ResultData.success(userInfoMapper.baseSelectByCondition(userInfo));
    }



    private HashMap<Integer,String> getTopicTitleMap(){
        List<TopicInfo> list = topicInfoMapper.baseSelectAll(new TopicInfo());
        HashMap<Integer,String> result = new HashMap<>();
        for(TopicInfo topicInfo:list){
            result.put(topicInfo.getId(),topicInfo.getTitle());
        }
        return result;
    }

    private HashMap<Integer,String> getExerciseTitleMap(){
        List<ExerciseInfo> list = exerciseMapper.baseSelectAll(new ExerciseInfo());
        HashMap<Integer,String> result = new HashMap<>();
        for(ExerciseInfo exerciseInfo:list){
            result.put(exerciseInfo.getId(),exerciseInfo.getTitle());
        }
        return result;
    }
}
