package com.example.demo.service;

import com.example.demo.common.response.ResultData;
import com.example.demo.entity.Rate;
import com.example.demo.entity.UserExercise;
import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RateService {

    @Resource
    private UserExerciseMapper userExerciseMapper;

    @Resource
    private RateMapper rateMapper;

    /**
     * 保存用户成绩
     * 添加一条用户成绩详细记录（userExercise）,并更新用户总成绩（user_scores）
     * @return
     */
    public ResultData save(UserExercise userExercise){
        //TODO 校验user/exercise是否存在

        userExerciseMapper.baseInsertAndReturnKey(userExercise);
        Rate rate = new Rate(userExercise.getUserId());
        rate = rateMapper.baseSelectById(rate);
        if(rate == null){

        }else {
            rate.setScore(rate.getScore() + userExercise.getScore());
            rateMapper.baseUpdateById(rate);
        }
        return ResultData.success();
    }

    public ResultData setTimer(int userId,Integer timer){
        Rate rate = new Rate(userId);
        rate = rateMapper.baseSelectById(rate);
        if(rate == null){
            return ResultData.error("Rate NotExist");
        }
        rate.setTimer(timer);
        rateMapper.updateTimer(rate);
        return ResultData.success();
    }
}
