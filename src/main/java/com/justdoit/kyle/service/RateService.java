package com.justdoit.kyle.service;

import com.justdoit.kyle.config.Languages;

import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.common.util.Console;
import com.justdoit.kyle.view.UserScores;
import com.justdoit.kyle.entity.*;
import com.justdoit.kyle.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 用户学习进度操作
 * 通过保存的学习进度，控制菜单栏展示的各项课程的状态，以及用户每次获取的课程
 * @author yangkaile
 * @date 2019-10-22 15:40:44
 */
@Service
public class RateService {

    private static HashMap<Integer,String> exerciseMap  = null;
    private static HashMap<Integer,String> topicMap  = null;

    @Resource
    private UserInfoMapper userInfoMapper;


    @Resource
    private RateMapper rateMapper;



    /**
     * 排行榜上显示的行数
     */
    public static final int ROWS = 8;

    /**
     *
     */
    public static final int MIDDLE_ROWS = 2;



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
            return ResultData.error(Languages.NO_USER);
        }
        Rate rate = new Rate();
        rate = rateMapper.baseSelectById(rate);
        if(rate == null){
            return ResultData.error(Languages.NO_RATE);
        }
        int sort = rateMapper.selectSort(rate) + 1;
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
            return ResultData.error(Languages.NO_USER);
        }
        Rate rate = new Rate();
        rate = rateMapper.baseSelectById(rate);
        if(rate == null){
            return ResultData.error(Languages.NO_RATE);
        }
        int sort = rateMapper.selectSort(rate) + 1;
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
            return ResultData.error(Languages.NO_USER);
        }
        Rate rate = new Rate();
        rate = rateMapper.baseSelectById(rate);
        if(rate == null){
            return ResultData.error(Languages.NO_RATE);
        }

        int sort = rateMapper.selectSort(rate) + 1;


        //构建自己的排行
        UserScores userScores = new UserScores(userInfo,rate);
        userScores.setSort(sort);

        //查询比自己成绩低的人，只取最接近自己成绩的2个
        rate.setBaseKylePageSize(MIDDLE_ROWS);
        List<UserScores> downList = rateMapper.selectDown(rate);
        //查询比自己成绩高的人，只取最接近自己成绩的2个
        int upSort = sort - MIDDLE_ROWS - 1;
        if(upSort < 0){
            upSort = 0;
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
            scores.setSort(++upSort);
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





    public ResultData getLeaderBoardTop20(){
        List<UserScores> list = rateMapper.getLeaderBoardTop20();
        int sort = 0;
        for(UserScores userScores:list){
            userScores.setSort(++sort);
        }
        return ResultData.success(list);
    }
}
