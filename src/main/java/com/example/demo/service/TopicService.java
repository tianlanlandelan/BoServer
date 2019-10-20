package com.example.demo.service;

import com.example.demo.MyConfig;
import com.example.demo.ServiceConfig;
import com.example.demo.common.response.ResultData;
import com.example.demo.common.util.StringUtils;
import com.example.demo.entity.ExerciseInfo;
import com.example.demo.entity.ScoreInfo;
import com.example.demo.entity.TopicInfo;
import com.example.demo.mapper.ExerciseMapper;
import com.example.demo.mapper.ScoreInfoMapper;
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

    @Resource
    private ScoreInfoMapper scoreInfoMapper;

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
        List<ExerciseInfo> exerciseList = exerciseMapper.selectAll();

        ScoreInfo scoreInfo = new ScoreInfo();
        scoreInfo.setUserId(userId);

        List<ScoreInfo> scoreList = scoreInfoMapper.baseSelectByCondition(scoreInfo);
        //将所有有成绩的练习置为DONE
        for (ScoreInfo score : scoreList){
            for(ExerciseInfo exercise : exerciseList){
                if(score.getExerciseId() == exercise.getId()){
                    exercise.setStatus(ServiceConfig.DONE);
                }
            }
        }
        //将第1个状态为TODO的练习置为DOING,表示该练习为当前可以做的练习
        for(int i = 0 ; i < exerciseList.size() ; i++){
            if(exerciseList.get(i).getStatus() == ServiceConfig.TODO){
                exerciseList.get(i).setStatus(ServiceConfig.DOING);
                break;
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
        /*
        如果一个课程下面所有练习都是TODO状态，该课程设置为TODO状态
        如果一个课程下面所有练习都是DONE状态，该课程设置为DONE状态
         */

        for(TopicInfo topic: topicList){
            int todo = 0;
            int count = topic.getList().size();
            int done = 0;
            if(count == 0){
                continue;
            }
            for(ExerciseInfo exercise:topic.getList()){
                switch (exercise.getStatus()){
                    case ServiceConfig.TODO: todo ++;break;
                    case ServiceConfig.DONE: done ++;break;
                    default: break;
                }
            }
            if(todo == count){
                topic.setStatus(ServiceConfig.TODO);
            }else if(done == count){
                topic.setStatus(ServiceConfig.DONE);
            }else {
                topic.setStatus(ServiceConfig.DOING);
            }
        }
        /*
         *  //TODO 可以添加课程介绍表
         *  在这里是为了将课程介绍设置为Done
         */
        for(int i = 0 ; i <topicList.size();i++){
            if(topicList.get(i).getStatus()==ServiceConfig.DOING && i > 0){
                topicList.get(i - 1).setStatus(ServiceConfig.DONE);
                break;
            }
        }

        if(topicList == null || topicList.size() < 1){
            return ResultData.error("No Topic!");
        }
        return ResultData.success(topicList);
    }
}
