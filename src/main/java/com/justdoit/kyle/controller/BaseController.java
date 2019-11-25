package com.justdoit.kyle.controller;

import com.justdoit.kyle.common.util.RequestUtil;
import com.justdoit.kyle.common.response.MyResponse;
import com.justdoit.kyle.common.util.Console;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.entity.UserInfo;
import com.justdoit.kyle.service.EmailService;
import com.justdoit.kyle.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-10-09 09:24:19
 */
@RestController
@RequestMapping("/base")
public class BaseController {
    @Resource
    private UserInfoService userInfoService;

    @Resource
    private EmailService emailService;

    /**
     * 注册功能
     * @param email 邮箱
     * @param password 密码
     * @return 注册成功返回UserId
     */
    @PostMapping("/register")
    public ResponseEntity register(String email ,String password,String code){
        if(StringUtils.isEmpty(email,password,code)){
            return MyResponse.badRequest();
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
        userInfo.setPassword(password);

        return MyResponse.ok(userInfoService.register(userInfo,code));
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

    /**
     * 获取验证码
     * @param type
     * @param email
     * @return
     */
    @GetMapping("/getCode")
    public ResponseEntity getCode(int type,String email){
        if(type < 0 || StringUtils.isEmpty(email)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(userInfoService.sendRegisterCode(type,email));
    }
}
