package com.justdoit.kyle.controller;

import com.justdoit.kyle.common.response.MyResponse;
import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.common.util.RequestUtil;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.service.AdminService;
import com.justdoit.kyle.service.AppConfigService;
import com.justdoit.kyle.service.RateService;
import com.justdoit.kyle.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AppConfigService appConfigService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private RateService rateService;

    @Resource
    private AdminService adminService;

    /**
     * 设置邀请码
     * @param value
     * @return
     */
    @PutMapping("/setInviteCode")
    public ResponseEntity setInviteCode(String value){
        if(StringUtils.isEmpty(value)){
            return MyResponse.badRequest();
        }
        appConfigService.setInviteCode(value);
        return MyResponse.ok();
    }

    @GetMapping("/getInviteCode")
    public ResponseEntity getInviteCode(){
        return MyResponse.ok(ResultData.success(appConfigService.getInviteCode()));
    }

    /**
     * 重置用户密码
     * @param userId
     * @param password
     * @return
     */
    @PutMapping("/resetPassword")
    public ResponseEntity resetPassword(Integer userId,String password){
        if(RequestUtil.notValidInteger(userId) || StringUtils.isEmpty(password)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(userInfoService.resetPassword(userId,password));
    }

    /**
     * 获取忘记密码的用户
     * @return
     */
    @GetMapping("/getFotPasswordUser")
    public ResponseEntity getFotPasswordUser(){
        return MyResponse.ok(userInfoService.getFotPasswordUser());
    }

    /**
     * 获取总用户数
     * @return
     */
    @GetMapping("/getUserCount")
    public ResponseEntity getUserCount(){
        return MyResponse.ok(userInfoService.getUserCount());
    }

    /**
     * 获取排行榜前20名
     * @return
     */
    @GetMapping("/getTopLeaderBoard")
    public ResponseEntity getTopLeaderBoard(){
        return MyResponse.ok();
    }




    @GetMapping("/getByEmail")
    public ResponseEntity getByEmail(String email){
        if(StringUtils.isEmpty(email)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(adminService.getByEmail(email));
    }


    @GetMapping("/getLeaderBoardTop20")
    public ResponseEntity getLeaderBoardTop20(){
        return MyResponse.ok(rateService.getLeaderBoardTop20());
    }


}
