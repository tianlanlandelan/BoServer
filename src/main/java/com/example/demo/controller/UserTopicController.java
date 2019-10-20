package com.example.demo.controller;

import com.example.demo.common.response.MyResponse;
import com.example.demo.entity.UserTopic;
import com.example.demo.service.UserTopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userTopic")
public class UserTopicController {
    @Resource
    private UserTopicService service;

    @PostMapping
    public ResponseEntity save(Integer userId,Integer topicId){
        if(RequestUtil.notValidInteger(userId,topicId)){
            return MyResponse.badRequest();
        }
        UserTopic userTopic = new UserTopic();
        userTopic.setUserId(userId);
        userTopic.setTopicId(topicId);
        return MyResponse.ok(service.save(userTopic));
    }
}
