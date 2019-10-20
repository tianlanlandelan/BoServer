package com.example.demo.controller;

import com.example.demo.common.response.MyResponse;
import com.example.demo.entity.UserExercise;
import com.example.demo.service.ScoreService;
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

    @PostMapping
    public ResponseEntity save(Integer userId,Integer exerciseId,Integer score){
        if(RequestUtil.notValidInteger(userId,exerciseId,score)){
            return MyResponse.badRequest();
        }
        UserExercise userExercise = new UserExercise();
        userExercise.setUserId(userId);
        userExercise.setExerciseId(exerciseId);
        userExercise.setScore(score);
        return MyResponse.ok(scoreService.save(userExercise));

    }
}
