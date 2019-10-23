package com.example.demo.controller;

import com.example.demo.common.response.MyResponse;
import com.example.demo.entity.UserExercise;
import com.example.demo.service.RateService;
import com.example.demo.service.TopicService;
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
     * @param userId
     * @param exerciseId
     * @param score
     * @return
     */
    @PostMapping
    public ResponseEntity save(Integer userId,Integer exerciseId,Integer score){
        if(RequestUtil.notValidInteger(userId,exerciseId,score)){
            return MyResponse.badRequest();
        }
        //清空计时器
        rateService.setTimer(userId,null);
        //刷进度
        topicService.getNext(userId);

        UserExercise userExercise = new UserExercise();
        userExercise.setUserId(userId);
        userExercise.setExerciseId(exerciseId);
        userExercise.setScore(score);
        return MyResponse.ok(rateService.save(userExercise));

    }

    @PutMapping
    public ResponseEntity timer(Integer userId,Integer timer){
        if(RequestUtil.notValidInteger(userId)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(rateService.setTimer(userId,timer));
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
}
