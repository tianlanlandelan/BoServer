package com.example.demo.controller;

import com.example.demo.common.response.MyResponse;
import com.example.demo.common.util.Console;
import com.example.demo.common.util.StringUtils;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.SendEmailService;
import com.example.demo.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-10-09 09:24:19
 */
@RestController
public class LoginController {
    @Resource
    UserInfoService userInfoService;

    @Resource
    private SendEmailService sendEmailService;


    @PostMapping("/register")
    public ResponseEntity register(Integer type,String email,String sid,String password){
        Console.print("register","type",type,"email",email,"sid",sid,"password",password);
        if(!RequestUtil.validType(type) || StringUtils.isEmpty(email,sid,password)){
            return MyResponse.badRequest();
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setType(type);
        userInfo.setEmail(email);
        userInfo.setSid(sid);
        userInfo.setPassword(password);

        return MyResponse.ok(userInfoService.insert(userInfo));
    }


    /**
     * 暂时只支持邮箱登录
     * @param type
     * @param name
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity login(int type,String name,String password){
        Console.print("login","type:",type,"name:",name,"password:",password);
        if(!RequestUtil.validType(type) || StringUtils.isEmpty(name,password)){
            return MyResponse.badRequest();
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setType(type);
        userInfo.setEmail(name);
        userInfo.setPassword(password);
        return MyResponse.ok(userInfoService.login(userInfo));
    }

}
