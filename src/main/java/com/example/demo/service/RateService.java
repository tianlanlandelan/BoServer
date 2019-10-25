package com.example.demo.service;

import com.example.demo.common.response.ResultData;
import com.example.demo.common.util.Console;
import com.example.demo.entity.ExerciseInfo;
import com.example.demo.entity.Rate;
import com.example.demo.entity.UserExercise;
import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.*;
import com.example.demo.view.UserScores;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户学习进度操作
 * 通过保存的学习进度，控制菜单栏展示的各项课程的状态，以及用户每次获取的课程
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

    @Resource
    private ExerciseMapper exerciseMapper;

    /**
     * 排行榜上显示的行数
     */
    public static final int ROWS = 8;

    /**
     *
     */
    public static final int MIDDLE_ROWS = 2;


    /**
     * 保存用户成绩
     * 添加一条用户成绩详细记录（userExercise）,并更新用户总成绩（user_scores）
     * @return
     */
    public ResultData save(UserExercise userExercise){
        //TODO 校验user/exercise是否存在

        if(userInfoMapper.baseUpdateById(new UserInfo(userExercise.getUserId())) == null){
            return ResultData.error("User NotExist");
        }
        ExerciseInfo exerciseInfo = exerciseMapper.baseSelectById(new ExerciseInfo(userExercise.getExerciseId()));
        if(exerciseInfo == null){
            return ResultData.error("Exercise NotExist");
        }
        userExercise.setAnswer(exerciseInfo.getAnswer());
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
     * @return 排行榜数据，顺序排列，自己的（最低的）在最后面
     */
    public ResultData getUp(int userId){
        UserInfo userInfo = new UserInfo(userId);
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
        List<UserScores> list;
        //如果排名在第8名之前，显示前面所有人的得分
        if(sort <= ROWS){
            list = rateMapper.selectUp(rate);
            int index = 1;
            for(UserScores userScores:list){
                userScores.setSort(index++);
            }
        }else{
            //如果排名在第8名之后，只显示前三名和后四名的
            rate.setBaseKylePageSize(ROWS/2 -1);
            List<UserScores> headList = rateMapper.selectUp(rate);
            int index = 1;
            for(UserScores userScores:headList){
                userScores.setSort(index++);
            }

            //查询后四名的
            rate.setBaseKylePageSize(ROWS/2);
            rate.setBaseKyleStartRows(sort - ROWS/2 - 1);
            List<UserScores> footList = rateMapper.selectUp(rate);
            index = sort - ROWS/2;
            for(UserScores userScores:footList){
                userScores.setSort(index++);
            }

            //添加一个空记录，作为其他人成绩的标记
            headList.add(new UserScores());
            headList.addAll(footList);
            list = headList;
        }
        //构建自己的排行
        UserScores userScores = new UserScores(userInfo,rate);
        userScores.setSort(sort);
        list.add(userScores);

        //计算排行榜中每个人的得分相对于第一名的百分比
        int firstScore = list.get(0).getScore();

        return ResultData.success(setPercentage4List(list,firstScore));
    }

    private List<UserScores> setPercentage4List(List<UserScores> list,int scale){
        float score ;
        int percentage;
        for(UserScores scores:list){
            if(scores.getId() == 0){
                continue;
            }
            score = scores.getScore();
            percentage = (int)(score / scale * 100);
            scores.setPercentage(percentage);
            Console.print("getPercentage ","scale:",scale,"score:",score,"percentage:",percentage);
        }
        return  list;
    }

    /**
     * 获取比自己排行低的人的数据，只获取8个
     * @param userId
     * @return 排行榜数据，顺序排列，自己的（最高的）在前面
     */
    public ResultData getDown(int userId){
        UserInfo userInfo = new UserInfo(userId);
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
        rate.setBaseKylePageSize(ROWS);
        List<UserScores> downList = rateMapper.selectDown(rate);

        //构建自己的排行
        UserScores userScores = new UserScores(userInfo,rate);
        userScores.setSort(sort);

        //设置名次
        for(UserScores scores:downList){
            scores.setSort(++sort);
        }
        List<UserScores> list = new ArrayList<>();
        //查询到的其他人的成绩都是比自己低的，将自己的成绩放在第一个
        list.add(userScores);
        list.addAll(downList);

        //计算排行榜中每个人的得分相对于自己分数的百分比
        int firstScore = userScores.getScore();

        return ResultData.success(setPercentage4List(list,firstScore));
    }

    /**
     * 获取相邻的排行
     * 比自己排行高的三个
     * 比自己排行低的三个
     * @param userId
     * @return 排行榜数据，按顺序排列，自己的（中间的）在中间排列
     */
    public ResultData getMiddle(int userId){
        UserInfo userInfo = new UserInfo(userId);
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


        //构建自己的排行
        UserScores userScores = new UserScores(userInfo,rate);
        userScores.setSort(sort);

        //查询比自己成绩低的人，只取最接近自己成绩的2个
        rate.setBaseKylePageSize(MIDDLE_ROWS);
        List<UserScores> downList = rateMapper.selectDown(rate);
        //查询比自己成绩高的人，只取最接近自己成绩的2个
        int upSort = sort - MIDDLE_ROWS;
        if(upSort < 1){
            upSort = 1;
        }
        rate.setBaseKyleStartRows(upSort);
        List<UserScores> upList = rateMapper.selectUp(rate);
        int firstScore;
        if(upList != null && upList.size() > 0){
            firstScore = upList.get(0).getScore();
        }else{
            firstScore = userScores.getScore();
        }
        //设置名次
        for(UserScores scores:upList){
            scores.setSort(upSort++);
        }
        int downSort = sort;

        for(UserScores scores:downList){
            scores.setSort(++downSort);
        }

        //构建返回的list
        List<UserScores> list = new ArrayList<>();
        //如果排名大于4，查询结果的第一个不是第1名，需要在前边标注一下上面还有其他人
        if(sort > ROWS/2){
            list.add(new UserScores());
        }
        list.addAll(upList);
        list.add(userScores);
        list.addAll(downList);
        list.add(new UserScores());

        //计算排行榜中每个人的得分相对于自己分数的百分比


        return ResultData.success(setPercentage4List(list,firstScore));
    }

}
