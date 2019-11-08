package com.justdoit.kyle.service;

import com.justdoit.kyle.config.Languages;
import com.justdoit.kyle.config.MyConfig;
import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.entity.*;
import com.justdoit.kyle.mapper.*;
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
    private TopicInfoMapper mapper;


    public ResultData save(TopicInfo topicInfo){
        mapper.baseInsertAndReturnKey(topicInfo);
        return ResultData.success(topicInfo.getId());
    }

    /**
     * Admin 用到的
     * @return
     */
    public ResultData getAll(){
        List<TopicInfo> list = mapper.baseSelectAll(new TopicInfo());
        if(list != null && list.size() > 0){
            for(TopicInfo topic : list){
                if(StringUtils.isNotEmpty(topic.getVideoUrl())) {
                    topic.setVideoUrl(myConfig.NGINX_PREFIX + topic.getVideoUrl());
                }
            }
            return ResultData.success(list);
        }
        return ResultData.error(Languages.NO_TOPIC);
    }

    public ResultData getById(int id){
        TopicInfo topicInfo = new TopicInfo();
        topicInfo.setId(id);
        topicInfo = mapper.baseSelectById(topicInfo);
        if(topicInfo == null){
            return ResultData.error(Languages.NO_TOPIC);
        }
        if(StringUtils.isNotEmpty(topicInfo.getVideoUrl())) {
            topicInfo.setVideoUrl(myConfig.NGINX_PREFIX + topicInfo.getVideoUrl());
        }
        return ResultData.success(topicInfo);
    }

    /**
     * 获取下一个
     * 根据用户学习的进度，获取下一步要展示的课程或练习
     * @param userId
     * @return
     */
    public ResultData getNext(int userId){

        return ResultData.success();
    }

    /**
     * 获取当前
     * 根据用户学习的进度，获取当前要展示的课程或练习
     * @param userId
     * @return
     */
    public ResultData getCurrent(int userId){
        return ResultData.success();
    }
}
