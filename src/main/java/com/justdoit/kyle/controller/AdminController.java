package com.justdoit.kyle.controller;

import com.justdoit.kyle.common.response.MyResponse;
import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.service.AppConfigService;
import com.justdoit.kyle.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 后台管理功能
 * @author yangkaile
 * @date 2019-11-25 15:00:53
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AppConfigService appConfigService;
    @Resource
    private UserInfoService userInfoService;

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
     * 获取总用户数
     * @return
     */
    @GetMapping("/getUserCount")
    public ResponseEntity getUserCount(){
        return MyResponse.ok(userInfoService.getUserCount());
    }
}
