package com.example.demo.controller;

import com.example.demo.common.response.MyResponse;
import com.example.demo.common.util.Console;
import com.example.demo.common.util.StringUtils;
import com.example.demo.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-10-09 09:22:28
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Resource
    UserInfoService userInfoService;

    @PutMapping()
    public ResponseEntity update(Integer id,String firstName,String lastName,Integer avatarId){
        Console.print("update",id,firstName,lastName,avatarId);
        if(id == null || avatarId == null || StringUtils.isEmpty(firstName,lastName)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(userInfoService.update(id,firstName,lastName,avatarId));
    }
}
