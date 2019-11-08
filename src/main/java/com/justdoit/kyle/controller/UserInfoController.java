package com.justdoit.kyle.controller;

import com.justdoit.kyle.common.response.MyResponse;
import com.justdoit.kyle.common.util.Console;
import com.justdoit.kyle.common.util.RequestUtil;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.service.UserInfoService;
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

}
