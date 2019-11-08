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
