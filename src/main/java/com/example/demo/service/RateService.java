package com.example.demo.service;

import com.example.demo.common.response.ResultData;
import com.example.demo.entity.Rate;
import com.example.demo.entity.UserExercise;
import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.*;
import com.example.demo.view.UserScores;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangkaile
 * @date 2019-10-22 15:40:44
 */
@Service
public class RateService {
    @Resource
    private UserInfoMapper userInfoMapper;

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

    /**
     * 用户开始答题时，每秒更新答题倒计时
     * @param userId
     * @param timer
     * @return
     */
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

    /**
     * 获取比当前用户得分高的
     * 当比用户排名高的人大于8个时，只显示前三个和后四个
     * @param userId
     * @return
     */
    public ResultData getUp(int userId){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo = userInfoMapper.baseSelectById(userInfo);
        if (userInfo == null) {
            return ResultData.error("User NotExist");
        }
        Rate rate = new Rate(userId);
        rate = rateMapper.baseSelectById(rate);
        if(rate == null){
            return ResultData.error("No Scores");
        }
        int sort = rateMapper.selectSort(rate);
        List<UserScores> list = null;
        //如果排名在第8名之前，显示前面所有人的得分
        if(sort <= 8){
            list = rateMapper.selectUp(rate);
            int index = 1;
            for(UserScores userScores:list){
                userScores.setSort(index++);
            }
        }else{
            //如果排名在第8名之后，只显示前三名和后四名的
            rate.setBaseKylePageSize(3);
            List<UserScores> headList = rateMapper.selectUp(rate);
            int index = 1;
            for(UserScores userScores:headList){
                userScores.setSort(index++);
            }

            //查询后四名的
            rate.setBaseKylePageSize(4);
            rate.setBaseKyleStartRows(sort - 4 - 1);
            List<UserScores> footList = rateMapper.selectUp(rate);
            index = sort - 4;
            for(UserScores userScores:footList){
                userScores.setSort(index++);
            }

            //添加一个空记录，作为其他人成绩的标记
            headList.add(new UserScores());
            headList.addAll(footList);
            list = headList;
        }
        //构建自己的得分
        UserScores userScores = new UserScores(userInfo,rate);
        userScores.setSort(sort);
        list.add(userScores);
        return ResultData.success(list);
    }
}
