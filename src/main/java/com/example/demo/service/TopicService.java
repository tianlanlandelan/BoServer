package com.example.demo.service;

import com.example.demo.MyConfig;
import com.example.demo.ServiceConfig;
import com.example.demo.common.response.ResultData;
import com.example.demo.common.util.StringUtils;
import com.example.demo.entity.ExerciseInfo;
import com.example.demo.entity.TopicInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.ExerciseMapper;
import com.example.demo.mapper.UserExerciseMapper;
import com.example.demo.mapper.TopicInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
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
    private UserExerciseMapper userExerciseMapper;


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
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo = userInfoMapper.baseSelectById(userInfo);
        if(userInfo == null){
            return ResultData.error("User NotExist");
        }

        List<TopicInfo> topicList = topicInfoMapper.selectAll();
        if(topicList == null || topicList.size() < 1){
            return ResultData.error("No Topic!");
        }

        List<ExerciseInfo> exerciseList = exerciseMapper.selectAll();

        /*
        设置练习状态
         */
        //如果用户当前没有要做的练习，将所有练习状态置为TODO
        if(userInfo.getExerciseId() == null){
            for(ExerciseInfo exercise : exerciseList){
                exercise.setStatus(ServiceConfig.TODO);
            }
        }else if(userInfo.getTopicOver() == UserInfo.OVER){
            //如果用户已经完成课程，所有练习设置为DONE
            for(ExerciseInfo exercise : exerciseList){
                exercise.setStatus(ServiceConfig.DONE);
            }
        }else{
            //根据当前用户做过的练习，设置练习状态
            for(ExerciseInfo exercise : exerciseList){
                //没做过的练习设置为TODO
                if(exercise.getId() > userInfo.getExerciseId()){
                    exercise.setStatus(ServiceConfig.TODO);
                }else if(exercise.getId() < userInfo.getExerciseId()){
                    //做过的练习设置为DONE
                    exercise.setStatus(ServiceConfig.DONE);
                }else {
                    //当前练习记录着倒计时的，说明是正在做的练习，设置为DOING
                    if(userInfo.getTimer() != null) {
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
        if(userInfo.getTopicId() == null){
            userInfo.setTopicId(topicList.get(0).getId());
            userInfoMapper.baseUpdateById(userInfo);
        }
        if(userInfo.getTopicOver() == UserInfo.OVER){
            //如果用户已经完成课程，所有课程设置为DONE
            for(TopicInfo topicInfo : topicList){
                topicInfo.setStatus(ServiceConfig.DONE);
            }
        }else {
            for (TopicInfo topicInfo : topicList) {
                if (topicInfo.getId() > userInfo.getTopicId()) {
                    topicInfo.setStatus(ServiceConfig.TODO);
                } else if (topicInfo.getId() < userInfo.getTopicId()) {
                    topicInfo.setStatus(ServiceConfig.DONE);
                } else {
                    topicInfo.setStatus(ServiceConfig.DOING);
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
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo = userInfoMapper.baseSelectById(userInfo);
        if(userInfo == null){
            return ResultData.error("User NotExist");
        }
        if(userInfo.getTopicId() == null){
            return ResultData.error("Not GetMenu");
        }
        if(userInfo.getTopicOver() == UserInfo.OVER){
            return ResultData.error("Topic Over");
        }
        List<TopicInfo> topicList = topicInfoMapper.baseSelectAll(new TopicInfo());
        if(topicList == null || topicList.size() < 1){
            return ResultData.error("No Topic!");
        }
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
            if(userInfo.getTopicId() == topicInfo.getId()){
                //当前课程有练习，判断练习
                if(topicInfo.getList() != null && topicInfo.getList().size() > 0){
                    for(int j = 0 ; j < topicInfo.getList().size(); j++){
                        ExerciseInfo exerciseInfo = topicInfo.getList().get(j);
                        //如果有下一个练习，返回下一个练习
                        if(userInfo.getExerciseId() == null ||
                                userInfo.getExerciseId() < exerciseInfo.getId()){
                            userInfo.setExerciseId(exerciseInfo.getId());
                            userInfo.setTimer(null);
                            userInfoMapper.baseUpdateById(userInfo);

                            if(StringUtils.isNotEmpty(exerciseInfo.getImg())){
                                exerciseInfo.setImg(myConfig.NGINX_PREFIX + exerciseInfo.getImg());
                            }
                            return ResultData.success(exerciseInfo);
                        }
                    }
                }
                //如果还有课程，返回下一课程
                if(i < topicList.size() - 1){
                    userInfo.setTopicId(topicList.get(i + 1).getId());
                    userInfoMapper.baseUpdateById(userInfo);
                    return ResultData.success(topicList.get(i + 1));
                }else{
                    //如果没有课程，设置当前课程已学完
                    userInfo.setTopicOver(UserInfo.OVER);
                    userInfoMapper.baseUpdateById(userInfo);
                    return ResultData.error("Topic Over");
                }
            }
        }
        return ResultData.error("Not find topic");
    }

    /**
     * 获取当前
     * 根据用户学习的进度，获取当前要展示的课程或练习
     * @param userId
     * @return
     */
    public ResultData getCurrent(int userId){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo = userInfoMapper.baseSelectById(userInfo);
        if(userInfo == null){
            return ResultData.error("User NotExist");
        }
        if(userInfo.getTopicId() == null){
            return ResultData.error("Not GetMenu");
        }
        if(userInfo.getTopicOver() == UserInfo.OVER){
            return ResultData.error("Topic Over");
        }
        List<TopicInfo> topicList = topicInfoMapper.baseSelectAll(new TopicInfo());
        if(topicList == null || topicList.size() < 1){
            return ResultData.error("No Topic!");
        }
        List<ExerciseInfo> exerciseList = exerciseMapper.baseSelectAll(new ExerciseInfo());
        if(exerciseList == null || exerciseList.size() < 1){
            return ResultData.error("No Exercise!");
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
           if(userInfo.getTopicId() == topicInfo.getId()){
               topic = topicInfo;
           }
        }
        if(topic == null){
            return ResultData.error("Not Find Topic!");
        }
        if(StringUtils.isNotEmpty(topic.getVideoUrl())){
            topic.setVideoUrl(myConfig.NGINX_PREFIX + topic.getVideoUrl());
        }
        if(userInfo.getExerciseId() == null){
            return ResultData.success(topic);
        }
        ExerciseInfo exercise = null;
        //从当前课程中查找练习，如果查找不到，说明正在学习课程
        for(ExerciseInfo exerciseInfo:topic.getList()){
            if(exerciseInfo.getId() == userInfo.getExerciseId()){
                exercise = exerciseInfo;
            }
        }
        //查找不到练习，返回当前课程
        if(exercise == null){
           return ResultData.success(topic);
        }else{
            if(StringUtils.isNotEmpty(exercise.getImg())){
                exercise.setImg(myConfig.NGINX_PREFIX + exercise.getImg());
            }
            exercise.setTimer(userInfo.getTimer());
            return ResultData.success(exercise);
        }
    }
}
