package com.example.demo.service;

import com.example.demo.common.response.ResultData;
import com.example.demo.entity.ScoreInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.entity.UserScores;
import com.example.demo.mapper.ExerciseMapper;
import com.example.demo.mapper.ScoreInfoMapper;
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
    private ScoreInfoMapper scoreInfoMapper;

    /**
     * 保存用户成绩
     * 添加一条用户成绩详细记录（scoreInfo）,并更新用户总成绩（user_scores）
     * @return
     */
    public ResultData save(ScoreInfo scoreInfo){
        //TODO 校验user/exercise是否存在

        scoreInfoMapper.baseInsertAndReturnKey(scoreInfo);
        UserScores userScores = new UserScores();
        userScores.setId(scoreInfo.getUserId());
        userScores = userScoresMapper.baseSelectById(userScores);
        if(userScores == null){
            userScores.setId(scoreInfo.getUserId());
            userScores.setScore(scoreInfo.getScore());
            userScoresMapper.baseInsert(userScores);
        }else {
            userScores.setScore(userScores.getScore() + scoreInfo.getScore());
            userScoresMapper.baseUpdateById(userScores);
        }
        return ResultData.success();
    }
}
