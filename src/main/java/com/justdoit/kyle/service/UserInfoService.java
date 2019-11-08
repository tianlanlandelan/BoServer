package com.justdoit.kyle.service;

import com.justdoit.kyle.config.Languages;
import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.entity.Rate;
import com.justdoit.kyle.entity.UserExercise;
import com.justdoit.kyle.entity.UserInfo;
import com.justdoit.kyle.mapper.RateMapper;
import com.justdoit.kyle.mapper.UserExerciseMapper;
import com.justdoit.kyle.mapper.UserInfoMapper;
import com.justdoit.kyle.mapper.UserTopicMapper;
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

    /**
     * 添加用户，如果同一个网站下有已注册的邮箱、学号，注册失败
     * @param userInfo
     * @return
     */
    public ResultData insert(UserInfo userInfo){
        if(checkExist(userInfo)){
            return ResultData.error(Languages.RE_REGISTER);
        }
        userInfoMapper.baseInsertAndReturnKey(userInfo);
        return ResultData.success(userInfo.getId());
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
     * 忘记密码
     * 设置用户状态为忘记密码，等待管理员操作
     * @param userInfo
     * @return
     */
    public ResultData forgetPassword(UserInfo userInfo){
        UserInfo user = getUserByEmailAndType(userInfo);
        if(user == null){
            return ResultData.error(Languages.NO_USER);
        }
        user.setStatus(UserInfo.FORGET_PASSWORD);
        userInfoMapper.baseUpdateById(user);
        return ResultData.success(Languages.NOTIFIED_ADMIN);
    }

    /**
     * 修改用户信息，只修改名称、头像
     * @param id
     * @param firstName
     * @param lastName
     * @param avatarId
     * @return
     */
    public ResultData update(int id,String firstName,String lastName,int avatarId){
        UserInfo userInfo = new UserInfo(id);
        userInfo = userInfoMapper.baseSelectById(userInfo);
        if(userInfo == null){
            return ResultData.error(Languages.NO_USER);
        }
        userInfo.setFirstName(firstName);
        userInfo.setLastName(lastName);
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
     * 获取忘记密码的用户
     * @return
     */
    public ResultData getFotPasswordUser(){
        UserInfo userInfo = new UserInfo();
        userInfo.setStatus(UserInfo.FORGET_PASSWORD);
        List<UserInfo> list = userInfoMapper.baseSelectByCondition(userInfo);
        if(list == null || list.size() < 1){
            return ResultData.error("暂时没有忘记密码的用户");
        }
        return ResultData.success(list);
    }

    /**
     * 获取用户总数
     * @return
     */
    public ResultData getUserCount(){
        return ResultData.success(userInfoMapper.baseSelectCount(new UserInfo()));
    }

    /**
     * 获取用户信息，需同时指定三个条件：type/email/uid
     * @param info
     * @return
     */
    private boolean checkExist(UserInfo info){
        UserInfo userInfo = new UserInfo();
        userInfo.setSid(info.getSid());
        userInfo.setEmail(info.getEmail());
        userInfo.setBaseKyleUseAnd(false);
        List<UserInfo> list = userInfoMapper.baseSelectByCondition(userInfo);
        for(UserInfo user : list){
            if(user.getType().equals(info.getType())){
                return true;
            }
        }
        return false;
    }

    @Resource
    private UserExerciseMapper userExerciseMapper;

    @Resource
    private RateMapper rateMapper;

    @Resource
    private UserTopicMapper userTopicMapper;

    /**
     * 删除用户，同时删除用户学习进度和答题记录
     * @param userId
     * @return
     */
    public ResultData delete(int userId){

        Rate rate = new Rate(userId);
        rateMapper.baseDeleteById(rate);

        UserExercise userExercise = new UserExercise();
        userExercise.setUserId(userId);
        userExerciseMapper.baseDeleteByCondition(userExercise);

        UserTopic userTopic = new UserTopic();
        userTopic.setUserId(userId);
        userTopicMapper.baseDeleteByCondition(userTopic);


        UserInfo userInfo = new UserInfo(userId);
        userInfoMapper.baseDeleteById(userInfo);



        return  ResultData.success();
    }
}
