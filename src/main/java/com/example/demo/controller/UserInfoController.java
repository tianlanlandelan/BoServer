package com.example.demo.controller;

import com.example.demo.common.response.MyResponse;
import com.example.demo.common.util.Console;
import com.example.demo.common.util.RequestUtil;
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

    /**
     * 修改用户信息，可修改昵称、头像
     * @param id
     * @param firstName
     * @param lastName
     * @param avatarId
     * @return
     */
    @PutMapping
    public ResponseEntity update(Integer id,String firstName,String lastName,Integer avatarId){
        Console.print("update",id,firstName,lastName,avatarId);
        if(id == null || avatarId == null || StringUtils.isEmpty(firstName,lastName)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(userInfoService.update(id,firstName,lastName,avatarId));
    }

    /**
     * 删除用户信息，同时删除该用户的学习进度和答题记录
     * @param userId
     * @return
     */
    @DeleteMapping
    public ResponseEntity delete(Integer userId){
        if(RequestUtil.notValidInteger(userId)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(userInfoService.delete(userId));
    }
}
