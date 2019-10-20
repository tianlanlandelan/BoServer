package com.example.demo.service;

import com.example.demo.MyConfig;
import com.example.demo.ServiceConfig;
import com.example.demo.common.response.ResultData;
import com.example.demo.common.util.StringUtils;
import com.example.demo.entity.ExerciseInfo;
import com.example.demo.entity.UserExercise;
import com.example.demo.entity.TopicInfo;
import com.example.demo.entity.UserTopic;
import com.example.demo.mapper.ExerciseMapper;
import com.example.demo.mapper.UserExerciseMapper;
import com.example.demo.mapper.TopicInfoMapper;
import com.example.demo.mapper.UserTopicMapper;
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

    @Resource
    private UserExerciseMapper userExerciseMapper;

    @Resource
    private UserTopicMapper userTopicMapper;

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

    /**
     * 获取菜单
     * @return
     */
    public ResultData getMenu(int userId){
        //TODO 校验user是否存在
        List<TopicInfo> topicList = topicInfoMapper.selectAll();
        if(topicList == null || topicList.size() < 1){
            return ResultData.error("No Topic!");
        }
        List<ExerciseInfo> exerciseList = exerciseMapper.selectAll();

        UserExercise userExercise = new UserExercise();
        userExercise.setUserId(userId);

        List<UserExercise> scoreList = userExerciseMapper.baseSelectByCondition(userExercise);

        UserTopic userTopic = new UserTopic();
        userTopic.setUserId(userId);
        List<UserTopic> userTopicList = userTopicMapper.baseSelectByCondition(userTopic);


        //将所有有成绩的练习置为DONE
        for (UserExercise score : scoreList){
            for(ExerciseInfo exercise : exerciseList){
                if(score.getExerciseId() == exercise.getId()){
                    exercise.setStatus(ServiceConfig.DONE);
                }
            }
        }

        //将练习添加到所属的课程中
        for(TopicInfo topic : topicList) {
            for (ExerciseInfo exercise : exerciseList) {
                if (exercise.getTopicId() == topic.getId()) {
                    topic.getList().add(exercise);
                }
            }
        }

        //将学过的课程设置为DONE
        for(UserTopic u:userTopicList){
            for(TopicInfo topic: topicList){
                if(topic.getId() == u.getTopicId()){
                    topic.setStatus(ServiceConfig.DONE);
                }
            }
        }
        //如果用户还没有学习过课程，将第一课状态设置为DOING
        if(userTopicList == null || userTopicList.size() < 1){
            topicList.get(0).setStatus(ServiceConfig.DOING);
        }else{
            //如果用户已经开始学习
            for(int i = 0 ; i < topicList.size() ; i ++){
                if(topicList.get(i).getStatus() == ServiceConfig.DONE
                        && i < topicList.size() - 1){
                    topicList.get(i + 1).setStatus(ServiceConfig.DOING);
                }
            }
        }
        return ResultData.success(topicList);
    }
}
