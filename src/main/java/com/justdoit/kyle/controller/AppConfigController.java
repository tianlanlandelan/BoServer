package com.justdoit.kyle.controller;

import com.justdoit.kyle.common.response.MyResponse;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.entity.AppConfig;
import com.justdoit.kyle.mapper.AppConfigMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/config")
public class AppConfigController {
    @Resource
    private AppConfigMapper mapper;

    /**
     * 修改邀请码
     * @param code
     * @return
     */
    @PutMapping
    public ResponseEntity setInviteCode(String code){
        if(StringUtils.isEmpty(code)){
            return MyResponse.badRequest();
        }
        AppConfig appConfig  = new AppConfig(AppConfig.INVITE_CODE,code);
        mapper.baseUpdateByKey(appConfig);
        return MyResponse.ok();
    }
}
