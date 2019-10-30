package com.example.demo.service;

import com.example.demo.common.response.ResultData;
import com.example.demo.entity.UserExercise;
import com.example.demo.entity.UserInfo;
import com.example.demo.entity.UserTopic;
import com.example.demo.mapper.UserExerciseMapper;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.mapper.UserTopicMapper;
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

    /**
     * 用户学习明细、答题明细等
     * @return
     */
    public ResultData selectInfoByType(int type){
        UserInfo userInfo = new UserInfo();
        userInfo.setType(type);
        List<UserInfo> userList = userInfoMapper.baseSelectByCondition(userInfo);
        HashMap<Integer,HashMap<String,String>> result = new HashMap<>();
        for(UserInfo user:userList){
            HashMap<String,String> map = new HashMap<>();
            map.put("userId",user.getId() + "");
            map.put("uid",user.getSid());
            map.put("uemail",user.getEmail());
            map.put("firstName",user.getFirstName());
            map.put("lastName",user.getLastName());
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
                map.put("topic" + topic.getTopicId(),topic.getTopicId() + "");
                map.put("topicTime" + topic.getTopicId(),topic.getTime() + "");
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


}
