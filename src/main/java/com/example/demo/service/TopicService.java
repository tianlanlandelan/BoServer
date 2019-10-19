package com.example.demo.service;

import com.example.demo.MyConfig;
import com.example.demo.ServiceConfig;
import com.example.demo.common.response.ResultData;
import com.example.demo.common.util.StringUtils;
import com.example.demo.entity.ExerciseInfo;
import com.example.demo.entity.TopicInfo;
import com.example.demo.mapper.ExerciseMapper;
import com.example.demo.mapper.TopicInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangkaile
 * @date 2019-10-17 14:48:08
 */
@Service
public class TopicService {
    @Resource
    private MyConfig myConfig;

    @Resource
    private TopicInfoMapper topicInfoMapper;

    @Resource
    private ExerciseMapper exerciseMapper;

    public ResultData save(TopicInfo topicInfo){
        topicInfoMapper.baseInsertAndReturnKey(topicInfo);
        return ResultData.success(topicInfo.getId());
    }

    /**
     * Admin 用到的
     * @return
     */
    public ResultData getAll(){
        List<TopicInfo> list = topicInfoMapper.baseSelectAll(new TopicInfo());
        if(list != null && list.size() > 0){
            for(TopicInfo topic : list){
                if(StringUtils.isNotEmpty(topic.getVideoUrl())) {
                    topic.setVideoUrl(myConfig.NGINX_PREFIX + topic.getVideoUrl());
                }
            }
            return ResultData.success(list);
        }
        return ResultData.error("No Topic!");
    }

    public ResultData getById(int id){
        TopicInfo topicInfo = new TopicInfo();
        topicInfo.setId(id);
        topicInfo = topicInfoMapper.baseSelectById(topicInfo);
        if(topicInfo == null){
            return ResultData.error("Not Exist");
        }
        if(StringUtils.isNotEmpty(topicInfo.getVideoUrl())) {
            topicInfo.setVideoUrl(myConfig.NGINX_PREFIX + topicInfo.getVideoUrl());
        }
        return ResultData.success(topicInfo);
    }

    public ResultData getMenu(){
        List<TopicInfo> topicList = topicInfoMapper.selectAll();
        List<ExerciseInfo> exerciseList = exerciseMapper.selectAll();

        for(TopicInfo topic : topicList){
            for(ExerciseInfo exercise : exerciseList){
                if(exercise.getTopicId() == topic.getId()){
                    topic.getList().add(exercise);
                }
            }
        }
        if(topicList == null || topicList.size() < 1){
            return ResultData.error("No Topic!");
        }
        return ResultData.success(topicList);
    }
}
