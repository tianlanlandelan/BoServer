package com.example.demo.service;

import com.example.demo.entity.AppConfig;
import com.example.demo.mapper.AppConfigMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AppConfigService {
    @Resource
    private AppConfigMapper mapper;

    /**
     * 邀请码
     */
    private static String inviteCode = null;

    /**
     * 获取邀请码
     * @return
     */
    public String getInviteCode(){
        if(inviteCode == null){
            AppConfig appConfig = new AppConfig(AppConfig.INVITE_CODE);
            appConfig = mapper.baseSelectByKey(appConfig);
            if(appConfig == null){
                inviteCode = null;
            }else{
                inviteCode = appConfig.getV();
            }
        }
        return inviteCode;
    }

    /**
     * 设置邀请码
     * @param value
     */
    public void setInviteCode(String value){
        AppConfig appConfig = new AppConfig(AppConfig.INVITE_CODE);
        appConfig = mapper.baseSelectByKey(appConfig);
        if(appConfig == null){
            appConfig = new AppConfig(AppConfig.INVITE_CODE,value);
            mapper.baseInsert(appConfig);
        }else{
            appConfig.setV(value);
            mapper.baseUpdateByKey(appConfig);
            inviteCode = value;
        }
    }
}
