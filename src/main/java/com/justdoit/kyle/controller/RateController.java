package com.justdoit.kyle.controller;

import com.justdoit.kyle.common.response.MyResponse;
import com.justdoit.kyle.common.util.RequestUtil;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.entity.Rate;
import com.justdoit.kyle.entity.UserExercise;
import com.justdoit.kyle.service.RateService;
import com.justdoit.kyle.service.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-10-22 14:38:58
 * 用户学习进度控制
 */
@RestController
@RequestMapping("/rate")
public class RateController {

    @Resource
    private RateService rateService;

    @Resource
    private TopicService topicService;

    /**
     * 保存练习的得分，此时，将练习的倒计时清空，将当前进度向后推进
     * @param userId 用户id
     * @param exerciseId 练习id
     * @param score 得分
     * @param answer 用户答案
     * @return
     */
    @PostMapping
    public ResponseEntity save(Integer userId,Integer exerciseId,Integer score,String answer){
        if(RequestUtil.notValidInteger(userId,exerciseId,score) || StringUtils.isEmpty(answer)){
            return MyResponse.badRequest();
        }
        //清空计时器
        int time = rateService.setTimerAndReturnValue(userId,null);
        //刷进度
        topicService.getNext(userId);
        UserExercise userExercise = new UserExercise();
        userExercise.setUserId(userId);
        userExercise.setExerciseId(exerciseId);
        userExercise.setScore(score);
        userExercise.setUserAnswer(answer);
        return MyResponse.ok(rateService.save(userExercise,time));

    }

    @PutMapping
    public ResponseEntity timer(Integer userId,Integer timer){
        if(RequestUtil.notValidInteger(userId)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(rateService.setTimer(userId,timer));
    }

    @PutMapping("/feedback")
    public ResponseEntity feedback(Integer userId,String feedback1,String feedback2){
        if(RequestUtil.notValidInteger(userId) || StringUtils.isEmpty(feedback1,feedback2)){
            return MyResponse.badRequest();
        }
        Rate rate = new Rate(userId);
        rate.setFeedback1(feedback1);
        rate.setFeedback2(feedback2);
        return MyResponse.ok(rateService.saveFeedBack(rate));
    }

    /**
     * 获取比自己排名高的用户的信息
     * @param userId
     * @return
     */
    @GetMapping("/getUp")
    public ResponseEntity getUp(Integer userId){
        if(RequestUtil.notValidInteger(userId)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(rateService.getUp(userId));
    }

    /**
     * 获取比自己排名低的
     * @param userId
     * @return
     */
    @GetMapping("/getDown")
    public ResponseEntity getDown(Integer userId){
        if(RequestUtil.notValidInteger(userId)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(rateService.getDown(userId));
    }
    @GetMapping("/getMiddle")
    public ResponseEntity getMiddle(Integer userId){
        if(RequestUtil.notValidInteger(userId)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(rateService.getMiddle(userId));
    }
}