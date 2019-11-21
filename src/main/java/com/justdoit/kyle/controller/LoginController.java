package com.justdoit.kyle.controller;

import com.justdoit.kyle.common.util.RequestUtil;
import com.justdoit.kyle.config.Languages;
import com.justdoit.kyle.common.response.MyResponse;
import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.common.util.Console;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.entity.UserInfo;
import com.justdoit.kyle.service.AppConfigService;
import com.justdoit.kyle.service.UserInfoService;
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
    private UserInfoService userInfoService;

    @Resource
    private AppConfigService appConfigService;



    /**
     * 注册功能
     * @param type 用户类型
     * @param email 邮箱
     * @param password 密码
     * @param code 邀请码，由管理员统一设定
     * @return 注册成功返回UserId
     */
    @PostMapping("/register")
    public ResponseEntity register(Integer type,String email ,String password,String code){
        if(!RequestUtil.validType(type) || StringUtils.isEmpty(email,password,code)
                ){
            return MyResponse.badRequest();
        }
        if(!code.equals(appConfigService.getInviteCode())){
            return MyResponse.ok(ResultData.error(Languages.WRONG_CODE));
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setType(type);
        userInfo.setEmail(email);
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

}
