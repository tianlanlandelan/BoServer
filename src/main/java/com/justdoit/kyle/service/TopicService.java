package com.justdoit.kyle.service;

import com.justdoit.kyle.config.Languages;
import com.justdoit.kyle.config.MyConfig;
import com.justdoit.kyle.config.ServiceConfig;
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
    private TopicInfoMapper topicInfoMapper;

    @Resource
    private ExerciseMapper exerciseMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private RateMapper rateMapper;

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
        return ResultData.error(Languages.NO_TOPIC);
    }

    public ResultData getById(int id){
        TopicInfo topicInfo = new TopicInfo();
        topicInfo.setId(id);
        topicInfo = topicInfoMapper.baseSelectById(topicInfo);
        if(topicInfo == null){
            return ResultData.error(Languages.NO_TOPIC);
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
        UserInfo userInfo = new UserInfo(userId);
        userInfo = userInfoMapper.baseSelectById(userInfo);
        if(userInfo == null){
            return ResultData.error(Languages.NO_USER);
        }

        List<TopicInfo> topicList = topicInfoMapper.selectAll();
        if(topicList == null || topicList.size() < 1){
            return ResultData.error(Languages.NO_TOPIC);
        }

        List<ExerciseInfo> exerciseList = exerciseMapper.selectAll();

        Rate rate = new Rate(userId);
        rate = rateMapper.baseSelectById(rate);
        if(rate == null){
            rate = new Rate();
            rate.setId(userId);
            rateMapper.baseInsert(rate);
        }

        /*
        设置练习状态
         */
        //如果用户当前没有要做的练习，将所有练习状态置为TODO
        if(rate.getExerciseId() == null){
            for(ExerciseInfo exercise : exerciseList){
                exercise.setStatus(ServiceConfig.TODO);
            }
        }else if(rate.getTopicOver() == Rate.OVER){
            //如果用户已经完成课程，所有练习设置为DONE
            for(ExerciseInfo exercise : exerciseList){
                exercise.setStatus(ServiceConfig.DONE);
            }
        }else{
            //根据当前用户做过的练习，设置练习状态
            for(ExerciseInfo exercise : exerciseList){
                //没做过的练习设置为TODO
                if(exercise.getId() > rate.getExerciseId()){
                    exercise.setStatus(ServiceConfig.TODO);
                }else if(exercise.getId() < rate.getExerciseId()){
                    //做过的练习设置为DONE
                    exercise.setStatus(ServiceConfig.DONE);
                }else {
                    //当前练习记录着倒计时的，说明是正在做的练习，设置为DOING
                    if(rate.getTimer() != null) {
                        exercise.setStatus(ServiceConfig.DOING);
                    }else{
                        //做过的练习
                        exercise.setStatus(ServiceConfig.DONE);
                    }

                }

            }
        }

        //练习添加到课程中
        for(TopicInfo topicInfo:topicList){
            for(ExerciseInfo exerciseInfo:exerciseList){
                if(exerciseInfo.getTopicId() == topicInfo.getId()){
                    topicInfo.getList().add(exerciseInfo);
                }
            }
        }

        //如果用户还没学习过课程，将第一课设置DOING
        if(rate.getTopicId() == null){
            rate.setTopicId(topicList.get(0).getId());
            rateMapper.baseUpdateById(rate);
        }
        if(rate.getTopicOver() == Rate.OVER){
            //如果用户已经完成课程，所有课程设置为DONE
            for(TopicInfo topicInfo : topicList){
                topicInfo.setStatus(ServiceConfig.DONE);
                topicInfo.setVideoStatus(ServiceConfig.DONE);
            }
        }else {
            for (TopicInfo topicInfo : topicList) {
                if (topicInfo.getId() > rate.getTopicId()) {
                    topicInfo.setStatus(ServiceConfig.TODO);
                    topicInfo.setVideoStatus(ServiceConfig.TODO);
                } else if (topicInfo.getId() < rate.getTopicId()) {
                    topicInfo.setStatus(ServiceConfig.DONE);
                    topicInfo.setVideoStatus(ServiceConfig.DONE);
                } else {
                    topicInfo.setStatus(ServiceConfig.DOING);
                    topicInfo.setVideoStatus(ServiceConfig.DONE);
                }
            }
        }

        return ResultData.success(topicList);
    }

    /**
     * 获取下一个
     * 根据用户学习的进度，获取下一步要展示的课程或练习
     * @param userId
     * @return
     */
    public ResultData getNext(int userId){
        UserInfo userInfo = new UserInfo(userId);
        userInfo = userInfoMapper.baseSelectById(userInfo);
        if(userInfo == null){
            return ResultData.error(Languages.NO_USER);
        }
        //获取用户学习进度
        Rate rate = new Rate(userId);
        rate = rateMapper.baseSelectById(rate);


        Integer time ;
        if(rate == null){
            return ResultData.error(Languages.NO_RATE);
        }else {
            //保存用户计时
            time = rate.getTimer();
            //清空用户计时器，只要是获取下一题，就说明当前学习已完成，重置计时器状态，避免影响下一学习内容的计时
            rate.setTimer(null);
            rateMapper.updateTimer(rate);
        }

        if(rate.getTopicOver() == Rate.OVER){
            return ResultData.error("Topic Over");
        }

        //获取课程列表
        List<TopicInfo> topicList = topicInfoMapper.baseSelectAll(new TopicInfo());
        if(topicList == null || topicList.size() < 1){
            return ResultData.error(Languages.NO_TOPIC);
        }

        //获取练习列表
        List<ExerciseInfo> exerciseList = exerciseMapper.baseSelectAll(new ExerciseInfo());
        if(exerciseList == null || exerciseList.size() < 1){
            return ResultData.error("No Exercise!");
        }
        //练习添加到课程中
        for(TopicInfo topicInfo:topicList){
            if(StringUtils.isNotEmpty(topicInfo.getVideoUrl())){
                topicInfo.setVideoUrl(myConfig.NGINX_PREFIX + topicInfo.getVideoUrl());
            }
            for(ExerciseInfo exerciseInfo:exerciseList){
                if(exerciseInfo.getTopicId() == topicInfo.getId()){
                    topicInfo.getList().add(exerciseInfo);
                }
            }
        }
        //判断当前课程是否有练习，如果有练习，看当前练习是否是最后一个
        for(int i = 0 ; i < topicList.size();i++){
            TopicInfo topicInfo = topicList.get(i);
            if(rate.getTopicId() == topicInfo.getId()){
                //当前课程有练习，判断练习
                if(topicInfo.getList() != null && topicInfo.getList().size() > 0){
                    //有练习的课程需要记录学习时间
                    UserTopic userTopic = new UserTopic(userId,topicInfo.getId());
                    if(time == null){
                        time = 0 ;
                    }
                    userTopic.setTime(ServiceConfig.TOPIC_TIME - time);
                    saveUserTopic(userTopic);
                    for(int j = 0 ; j < topicInfo.getList().size(); j++){
                        ExerciseInfo exerciseInfo = topicInfo.getList().get(j);
                        //如果有下一个练习，返回下一个练习
                        if(rate.getExerciseId() == null ||
                                rate.getExerciseId() < exerciseInfo.getId()){
                            rate.setExerciseId(exerciseInfo.getId());
                            rateMapper.baseUpdateById(rate);

                            if(StringUtils.isNotEmpty(exerciseInfo.getImg())){
                                exerciseInfo.setImg(myConfig.NGINX_PREFIX + exerciseInfo.getImg());
                            }
                            return ResultData.success(exerciseInfo);
                        }
                    }
                }
                //如果还有课程，返回下一课程
                if(i < topicList.size() - 1){
                    rate.setTopicId(topicList.get(i + 1).getId());
                    rateMapper.baseUpdateById(rate);
                    return ResultData.success(topicList.get(i + 1));
                }else{
                    //如果没有课程，设置当前课程已学完
                    rate.setTopicOver(Rate.OVER);
                    rateMapper.baseUpdateById(rate);
                    return ResultData.error("Topic Over");
                }
            }
        }
        return ResultData.error(Languages.NO_TOPIC);
    }

    private void saveUserTopic(UserTopic userTopic){
        List<UserTopic> list = userTopicMapper.baseSelectByCondition(userTopic);
        if(list == null || list.size() < 1){
            userTopicMapper.baseInsertAndReturnKey(userTopic);
        }else{
            return;
        }
    }

    /**
     * 获取当前
     * 根据用户学习的进度，获取当前要展示的课程或练习
     * @param userId
     * @return
     */
    public ResultData getCurrent(int userId){
        UserInfo userInfo = new UserInfo(userId);
        userInfo = userInfoMapper.baseSelectById(userInfo);
        if(userInfo == null){
            return ResultData.error(Languages.NO_USER);
        }
        Rate rate = new Rate(userId);
        rate = rateMapper.baseSelectById(rate);
        if(rate == null || rate.getTopicId() == null){
            return ResultData.error(Languages.NO_RATE);
        }
        if(rate.getTopicOver() == Rate.OVER){
            return ResultData.error("Topic Over");
        }
        List<TopicInfo> topicList = topicInfoMapper.baseSelectAll(new TopicInfo());
        if(topicList == null || topicList.size() < 1){
            return ResultData.error(Languages.NO_TOPIC);
        }
        List<ExerciseInfo> exerciseList = exerciseMapper.baseSelectAll(new ExerciseInfo());
        if(exerciseList == null || exerciseList.size() < 1){
            return ResultData.error(Languages.NO_EXERCISE);
        }
        TopicInfo topic = null;
        //练习添加到课程中
        for(TopicInfo topicInfo:topicList){
            for(ExerciseInfo exerciseInfo:exerciseList){
                if(exerciseInfo.getTopicId() == topicInfo.getId()){
                    topicInfo.getList().add(exerciseInfo);
                }
            }
        }
        for(TopicInfo topicInfo:topicList){
           if(rate.getTopicId() == topicInfo.getId()){
               topic = topicInfo;
           }
        }
        if(topic == null){
            return ResultData.error(Languages.NO_TOPIC);
        }

        if(StringUtils.isNotEmpty(topic.getVideoUrl())){
            topic.setVideoUrl(myConfig.NGINX_PREFIX + topic.getVideoUrl());
        }
        if(rate.getExerciseId() == null){
            topic.setList(null);
            topic.setTimer(rate.getTimer());
            return ResultData.success(topic);
        }
        ExerciseInfo exercise = null;
        //从当前课程中查找练习，如果查找不到，说明正在学习课程
        for(ExerciseInfo exerciseInfo:topic.getList()){
            if(exerciseInfo.getId() == rate.getExerciseId()){
                exercise = exerciseInfo;
            }
        }
        //查找不到练习，返回当前课程
        if(exercise == null){
            topic.setList(null);
            topic.setTimer(rate.getTimer());
            return ResultData.success(topic);
        }else{
            if(StringUtils.isNotEmpty(exercise.getImg())){
                exercise.setImg(myConfig.NGINX_PREFIX + exercise.getImg());
            }
            exercise.setTimer(rate.getTimer());
            return ResultData.success(exercise);
        }
    }
}
