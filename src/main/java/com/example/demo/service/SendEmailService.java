package com.example.demo.service;

import com.example.demo.common.response.ResultData;
import com.example.demo.common.util.SendEmailUtils;
import com.example.demo.entity.EmailLog;
import com.example.demo.mapper.EmailLogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-09-27 08:39:27
 */
@Service
public class SendEmailService {

    @Resource
    EmailLogMapper emailLogMapper;

    public ResultData sendCode(int userType,int type, String email){
        EmailLog log = SendEmailUtils.sendVCode(type,email);

        //邮件发送后，成功失败都记录日志
        if(log != null){
            return ResultData.error("类型错误，邮件未发送");
            //邮件未发送
        }else {
            emailLogMapper.baseInsertAndReturnKey(log);
            return ResultData.success();
        }
    }

}
