package com.example.demo.controller;

import com.example.demo.common.response.MyResponse;
import com.example.demo.entity.ScoreInfo;
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
        ScoreInfo scoreInfo = new ScoreInfo();
        scoreInfo.setUserId(userId);
        scoreInfo.setExerciseId(exerciseId);
        scoreInfo.setScore(score);
        return MyResponse.ok(scoreService.save(scoreInfo));

    }
}
