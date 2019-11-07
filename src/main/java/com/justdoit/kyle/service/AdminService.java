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
    private UserTopicMapper userTopicMapper;

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

    /**
     * 用户学习明细、答题明细等
     * @return
     */
    public ResultData selectInfoByType(int type){
        UserInfo userInfo = new UserInfo();
        userInfo.setType(type);
        List<UserInfo> userList = userInfoMapper.baseSelectByCondition(userInfo);
        HashMap<Integer,HashMap<String,String>> result = new HashMap<>();
        HashMap<Integer,Rate> rateMap = getRateMap();

        HashMap<Integer,String> topicTitleMap = getTopicTitleMap();
        HashMap<Integer,String> exerciseTitleMap =getExerciseTitleMap();

        for(UserInfo user:userList){
            HashMap<String,String> map = new HashMap<>();

            //添加用户名片
            map.put("userId",user.getId() + "");
            map.put("uid",user.getSid());
            map.put("uemail",user.getEmail());
            map.put("firstName",user.getFirstName());
            map.put("lastName",user.getLastName());

            Rate rate = rateMap.get(user.getId());
            if(rate != null){
                //添加用户排名
                map.put("sort", rate.getSort() + "");
                map.put("score",rate.getScore() + "");
                map.put("topic",topicTitleMap.get(rate.getTopicId()));
                map.put("exercise",exerciseTitleMap.get(rate.getExerciseId()));
                map.put("feedback1",rate.getFeedback1());
                map.put("feedback2",rate.getFeedback2());
            }


            result.put(user.getId(),map);
        }
        //添加答题明细
        result = addUserExercise(userList,result,type);
        //添加学习明细
        result = addUserTopic(userList,result,type);



        return ResultData.success(result.values());
    }

    /**
     * 在map中添加用户答题明细
     * @param userList
     * @param result
     * @param type
     * @return
     */
    private HashMap<Integer,HashMap<String,String>> addUserExercise(List<UserInfo> userList,HashMap<Integer,HashMap<String,String>> result,int type){
        //添加答题明细
        HashMap<Integer,List<UserExercise>> userExerciseMap = getExerciseMapByUserType(type);
        for(UserInfo user :userList){
            List<UserExercise> list = userExerciseMap.get(user.getId());
            if(list == null){
                continue;
            }
            HashMap<String,String> map = result.get(user.getId());
            if(map == null){
                map = new HashMap<>();
            }
            for (UserExercise exercise:list){
                map.put("userId",exercise.getUserId() + "");
                map.put("exercise" + exercise.getExerciseId(),exercise.getExerciseId() + "");
                map.put("score" + exercise.getExerciseId(),exercise.getScore() + "");
                map.put("time" + exercise.getExerciseId(),exercise.getTime() + "");
            }
            result.put(user.getId(),map);
        }
        return result;
    }

    /**
     * 获取用户答题记录，并按UserId分类
     * @param type
     * @return
     */
    private HashMap<Integer,List<UserExercise>> getExerciseMapByUserType(int type){
        List<UserExercise> list = userExerciseMapper.selectByUserType(type);

        HashMap<Integer,List<UserExercise>> map = new HashMap<>();

        //将同一用户的答题记录区分开
        for(UserExercise userExercise :list){
            List<UserExercise> l1 = map.get(userExercise.getUserId());
            if(l1 == null){
                l1 = new ArrayList<>();
            }
            l1.add(userExercise);

            map.put(userExercise.getUserId(),l1);
        }
        return map;
    }


    /**
     * 添加用户学习情况
     * @param userList
     * @param result
     * @param type
     * @return
     */
    private HashMap<Integer,HashMap<String,String>> addUserTopic(List<UserInfo> userList,HashMap<Integer,HashMap<String,String>> result,int type){
        HashMap<Integer,List<UserTopic>> userTopicMap = getTopicMapByUserType(type);
        for(UserInfo user :userList){
            List<UserTopic> list = userTopicMap.get(user.getId());
            if(list == null){
                continue;
            }
            HashMap<String,String> map = result.get(user.getId());
            if(map == null){
                map = new HashMap<>();
            }
            for (UserTopic topic:list){
                map.put("userId",topic.getUserId() + "");
                map.put("topic" + (topic.getTopicId() - 1),topic.getTopicId() + "");
                map.put("topicTime" + (topic.getTopicId() - 1),topic.getTime() + "");
            }
            result.put(user.getId(),map);
        }
        return result;
    }

    /**
     * 查询用户学习情况，并按UserId归类
     * @param type
     * @return
     */
    private HashMap<Integer,List<UserTopic>> getTopicMapByUserType(int type){
        List<UserTopic> list = userTopicMapper.selectByUserType(type);

        HashMap<Integer,List<UserTopic>> map = new HashMap<>();

        //将同一用户的学习记录区分开
        for(UserTopic userTopic :list){
            List<UserTopic> l1 = map.get(userTopic.getUserId());
            if(l1 == null){
                l1 = new ArrayList<>();
            }
            l1.add(userTopic);

            map.put(userTopic.getUserId(),l1);
        }
        return map;
    }

    /**
     * 获取排名信息
     * @return
     */
    private HashMap<Integer,Rate> getRateMap(){
        Rate rate = new Rate();
        rate.setBaseKyleUseASC(false);
        List<Rate> list = rateMapper.baseSelectByCondition(rate);
        int index = 0;
        HashMap<Integer,Rate> result = new HashMap<>();
        //添加排名
        for(Rate r: list){
            r.setSort(++index);
            result.put(r.getId(),r);
        }
        return result;
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
