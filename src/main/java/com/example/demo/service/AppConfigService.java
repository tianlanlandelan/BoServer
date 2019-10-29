package com.example.demo.service;

import com.example.demo.entity.AppConfig;
import com.example.demo.mapper.AppConfigMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AppConfigService {
    @Resource
    private AppConfigMapper mapper;

    public String getInviteCode(){
        AppConfig appConfig = new AppConfig(AppConfig.INVITE_CODE);
        appConfig = mapper.baseSelectByKey(appConfig);
        if(appConfig == null){
            return null;
        }else{
            return appConfig.getV();
        }
    }
}
