package com.justdoit.kyle.service;

import com.justdoit.kyle.config.Languages;
import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.config.PublicConfig;
import com.justdoit.kyle.entity.EmailLog;
import com.justdoit.kyle.entity.Rate;
import com.justdoit.kyle.entity.UserExercise;
import com.justdoit.kyle.entity.UserInfo;
import com.justdoit.kyle.mapper.RateMapper;
import com.justdoit.kyle.mapper.UserExerciseMapper;
import com.justdoit.kyle.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangkaile
 * @date 2019-10-09 11:10:21
 */
@Service
public class UserInfoService {
    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    EmailService emailService;


    /**
     * 发送注册验证码
     * @param userType 用户类型
     * @param email 邮箱
     * @return
     */
    public ResultData sendRegisterCode(int userType,String email){
        UserInfo userInfo = new UserInfo();
        userInfo.setType(userType);
        userInfo.setEmail(email);
        if(checkExist(userInfo)){
            return ResultData.error("账号已注册，请直接登录");
        }
        return emailService.sendCode(PublicConfig.RegisterType,email);
    }

    public ResultData register(UserInfo userInfo,String code){

        EmailLog emailLog = new EmailLog();
        emailLog.setEmail(userInfo.getEmail());
        emailLog.setType(PublicConfig.RegisterType);
        emailLog.setCode(code);
        if(emailService.checkCode(emailLog)){
            userInfo = insert(userInfo);
            if(userInfo == null){
                return ResultData.error("注册失败");
            }
            return ResultData.success();
        }
        return ResultData.error("验证码错误");
    }

    /**
     * 添加用户
     * @param userInfo
     * @return
     */
    public UserInfo insert(UserInfo userInfo){
        userInfo.setType(1);
        if(checkExist(userInfo)){
            return null;
        }
        userInfoMapper.baseInsertAndReturnKey(userInfo);
        return userInfo;
    }


    /**
     * 登录
     * 判断指定邮箱和类型的用户是否存在，
     *  如果存在，校验密码；
     *      如果密码正确，返回用户数据，返回的数据屏蔽密码
     *      否则，返回密码不正确
     *  否则，返回用户不存在
     * @param userInfo
     * @return
     */
    public ResultData login(UserInfo userInfo){
        UserInfo user = getUserByEmailAndType(userInfo);
        if(user == null){
            return ResultData.error(Languages.NO_USER);
        }
        if(userInfo.getPassword().equals(user.getPassword())){
            user.setPassword("");
            return ResultData.success(user);
        }else {
            return ResultData.error(Languages.PASSWORD_WRONG);
        }
    }

    private UserInfo getUserByEmailAndType(UserInfo userInfo){
        UserInfo user = new UserInfo();
        user.setEmail(userInfo.getEmail());
        user.setType(userInfo.getType());
        user.setBaseKyleUseAnd(true);

        List<UserInfo> list = userInfoMapper.baseSelectByCondition(user);
        //仅查到1个用户才算正确
        if(list == null || list.size() != 1){
            return null;
        }
        return list.get(0);
    }



    /**
     * 修改用户信息，只修改名称、头像
     * @param id
     * @param nickName
     * @param avatarId
     * @return
     */
    public ResultData update(int id,String nickName,int avatarId){
        UserInfo userInfo = new UserInfo(id);
        userInfo = userInfoMapper.baseSelectById(userInfo);
        if(userInfo == null){
            return ResultData.error(Languages.NO_USER);
        }
        userInfo.setNickName(nickName);
        userInfo.setAvatarId(avatarId);
        userInfoMapper.baseUpdateById(userInfo);
        return ResultData.success();
    }

    /**
     * 重置密码
     * @param id
     * @param password
     * @return
     */
    public ResultData resetPassword(int id ,String password){
        UserInfo userInfo = new UserInfo(id);
        userInfo = userInfoMapper.baseSelectById(userInfo);
        if(userInfo == null){
            return ResultData.error(Languages.NO_USER);
        }
        userInfo.setPassword(password);
        userInfo.setStatus(0);
        userInfoMapper.baseUpdateById(userInfo);
        return ResultData.success();
    }



    /**
     * 获取用户总数
     * @return
     */
    public ResultData getUserCount(){
        return ResultData.success(userInfoMapper.baseSelectCount(new UserInfo()));
    }

    /**
     * 判断用户是否已存在
     * @param info
     * @return
     */
    private boolean checkExist(UserInfo info){
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(info.getEmail());
        userInfo.setBaseKyleUseAnd(false);
        List<UserInfo> list = userInfoMapper.baseSelectByCondition(userInfo);
        for(UserInfo user : list){
            if(user.getType().equals(info.getType())){

            } return true;
        }
        return false;
    }
}
