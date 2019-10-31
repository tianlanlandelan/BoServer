package com.example.demo.controller;

import com.example.demo.Languages;
import com.example.demo.common.response.MyResponse;
import com.example.demo.common.response.ResultData;
import com.example.demo.common.util.Console;
import com.example.demo.common.util.StringUtils;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.AppConfigService;
import com.example.demo.service.TopicService;
import com.example.demo.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    private UserInfoService userInfoService;


    @Resource
    private TopicService topicService;

    @Resource
    private AppConfigService appConfigService;



    /**
     * 注册功能
     * @param type 用户类型
     * @param email 邮箱
     * @param sid 学号
     * @param password 密码
     * @param code 邀请码，由管理员统一设定
     * @return 注册成功返回UserId
     */
    @PostMapping("/register")
    public ResponseEntity register(Integer type,String email,String sid,String password,String code){
        Console.print("register","type",type,"email",email,"sid",sid,"password",password);
        if(!RequestUtil.validType(type) || StringUtils.isEmpty(email,sid,password,code)
                ){
            return MyResponse.badRequest();
        }
        if(!code.equals(appConfigService.getInviteCode())){
            return MyResponse.ok(ResultData.error(Languages.WRONG_CODE));
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

    @PostMapping("/forgetPassword")
    public ResponseEntity forgetPassword(int type,String name){
        Console.print("forgetPassword","type:",type,"name:",name);
        if(!RequestUtil.validType(type) || StringUtils.isEmpty(name)){
            return MyResponse.badRequest();
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setType(type);
        userInfo.setEmail(name);
        return MyResponse.ok(userInfoService.forgetPassword(userInfo));
    }

    @GetMapping("/getMenu")
    public ResponseEntity getMenu(Integer userId){
        if(RequestUtil.notValidInteger(userId)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(topicService.getMenu(userId));
    }


}
