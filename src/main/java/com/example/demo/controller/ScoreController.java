package com.example.demo.controller;

import com.example.demo.common.response.MyResponse;
import com.example.demo.entity.UserExercise;
import com.example.demo.service.ScoreService;
import com.example.demo.service.TopicService;
import com.example.demo.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Resource
    private ScoreService scoreService;

    @Resource
    private UserInfoService userInfoService;
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
        userInfoService.setTimer(userId,null);
        //刷进度
        topicService.getNext(userId);

        UserExercise userExercise = new UserExercise();
        userExercise.setUserId(userId);
        userExercise.setExerciseId(exerciseId);
        userExercise.setScore(score);
        return MyResponse.ok(scoreService.save(userExercise));

    }
}
