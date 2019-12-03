package com.justdoit.kyle.service;

import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.common.util.SendEmailUtils;
import com.justdoit.kyle.config.MyConfig;
import com.justdoit.kyle.entity.EmailLog;
import com.justdoit.kyle.mapper.EmailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangkaile
 * @date 2019-11-22 15:54:45
 */
@Service
public class EmailService {
    @Resource
    private EmailMapper mapper;
    @Resource
    private MyConfig config;

    /**
     * 发送邮件验证码
     * @param type
     * @param email
     * @return
     */
    public ResultData sendCode(int type,String email){
        EmailLog emailLog = SendEmailUtils.getSender(
                config.mailServerHost,
                config.mailServerUser,
                config.mailServerPassword)
                .sendVCode(type,email);
        if(emailLog == null){
            return ResultData.error("邮件发送失败");
        }
        mapper.baseInsertAndReturnKey(emailLog);
        return ResultData.success();
    }

    public boolean checkCode(EmailLog emailLog){
        emailLog.setEmail(emailLog.getEmail());
        emailLog.setBaseKyleUseASC(false);
        List<EmailLog> list = mapper.baseSelectByCondition(emailLog);
        if(list == null || list.size() <= 0){
            return false;
        }
        EmailLog result = list.get(0);
        if(result.isEfficientVerificationCode() &&
                result.equalsCode(emailLog)){
            setCodeUsed(result);
            return true;
        }
        return false;
    }

    private void setCodeUsed(EmailLog emailLog){
        emailLog.setIsUsed(1);
        mapper.baseUpdateById(emailLog);
    }
}
