package com.justdoit.kyle.service;

import com.justdoit.kyle.config.Languages;
import com.justdoit.kyle.config.MyConfig;
import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.entity.*;
import com.justdoit.kyle.mapper.*;
import com.justdoit.kyle.view.ChapterTopic;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    private ChapterService chapterService;


    /**
     * 保存课程，有则更新，无则添加
     * @param topicInfo
     * @return
     */
    public ResultData save(TopicInfo topicInfo){
        if(topicInfo.getId() == 0){
            mapper.baseInsertAndReturnKey(topicInfo);
        }else{
            TopicInfo result = mapper.baseSelectById(topicInfo);
            if(result == null){
                return ResultData.error("没有找到指定课程");
            }
            mapper.baseUpdateById(topicInfo);
        }
        return ResultData.success(topicInfo.getId());
    }

    /**
     *
     * @param courseId 课程id
     * @return List<ChapterTopic>
     */
    public ResultData getByCourseId(int courseId){
        TopicInfo info = new TopicInfo();
        info.setCourseId(courseId);
        //获取课程列表
        List<TopicInfo> list = mapper.baseSelectByCondition(info);
        //获取章节列表
        List<ChapterInfo> chapterList = chapterService.getByCourseId(courseId);
        if(list != null && list.size() > 0){
            for(TopicInfo topic : list){
                if(StringUtils.isNotEmpty(topic.getVideoUrl())) {
                    topic.setVideoUrl(myConfig.NGINX_PREFIX + topic.getVideoUrl());
                }
            }
        }
        List<ChapterTopic> result = new ArrayList<>();
        /**
         * 将课时放入章节中
         */
        for(ChapterInfo chapterInfo :chapterList){
            ChapterTopic chapterTopic = new ChapterTopic(chapterInfo);
            for(TopicInfo topicInfo:list){
                if(topicInfo.getChapterId() == chapterInfo.getId()){
                    chapterTopic.getList().add(topicInfo);
                }
            }
            result.add(chapterTopic);
        }
        if(result.size() > 0){
            return ResultData.success(result);
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
}
