package com.justdoit.kyle.controller;

import com.justdoit.kyle.common.util.RequestUtil;
import com.justdoit.kyle.common.response.MyResponse;
import com.justdoit.kyle.common.util.Console;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.entity.UserInfo;
import com.justdoit.kyle.service.EmailService;
import com.justdoit.kyle.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author yangkaile
 * @date 2019-10-09 09:24:19
 */
@RestController
@RequestMapping("/base")
@CrossOrigin(origins = "*",allowedHeaders="*", maxAge = 3600)
public class BaseController {
    @Resource
    private UserInfoService userInfoService;

    /**
     * 注册功能
     */
    @PostMapping("/register")

    public ResponseEntity register(@RequestBody Map<String,String> params){
        String email = params.get("email");
        String password = params.get("password");
        String code = params.get("code");
        if(StringUtils.isEmpty(email,password,code)){
            return MyResponse.badRequest();
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
        userInfo.setPassword(password);
        userInfo.setType(0);

        return MyResponse.ok(userInfoService.register(userInfo,code));
    }


    /**
     * 暂时只支持邮箱登录
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map<String,String> params){
        Integer type = Integer.parseInt(params.get("type"));
        String name = params.get("name");
        String password = params.get("password");
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
