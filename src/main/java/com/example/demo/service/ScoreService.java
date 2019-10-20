package com.example.demo.service;

import com.example.demo.common.response.ResultData;
import com.example.demo.entity.UserExercise;
import com.example.demo.entity.UserScores;
import com.example.demo.mapper.ExerciseMapper;
import com.example.demo.mapper.UserExerciseMapper;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.mapper.UserScoresMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ScoreService {
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private ExerciseMapper exerciseMapper;
    @Resource
    private UserScoresMapper userScoresMapper;
    @Resource
    private UserExerciseMapper userExerciseMapper;

    /**
     * 保存用户成绩
     * 添加一条用户成绩详细记录（userExercise）,并更新用户总成绩（user_scores）
     * @return
     */
    public ResultData save(UserExercise userExercise){
        //TODO 校验user/exercise是否存在

        userExerciseMapper.baseInsertAndReturnKey(userExercise);
        UserScores userScores = new UserScores();
        userScores.setId(userExercise.getUserId());
        userScores = userScoresMapper.baseSelectById(userScores);
        if(userScores == null){
            userScores = new UserScores();
            userScores.setId(userExercise.getUserId());
            userScores.setScore(userExercise.getScore());
            userScoresMapper.baseInsert(userScores);
        }else {
            userScores.setScore(userScores.getScore() + userExercise.getScore());
            userScoresMapper.baseUpdateById(userScores);
        }
        return ResultData.success();
    }
}
