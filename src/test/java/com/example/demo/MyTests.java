package com.example.demo;

import com.example.demo.common.util.Console;
import com.example.demo.common.util.StringUtils;
import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest

public class MyTests {

    @Resource
    UserInfoMapper userInfoMapper;
    @Resource
    TopicInfoMapper topicInfoMapper;
    @Resource
    ExerciseMapper exerciseMapper;
    @Resource
    RateMapper rateMapper;
    @Resource
    UserExerciseMapper userExerciseMapper;
    @Resource
    AppConfigMapper appConfigMapper;

    @Resource
    UserTopicMapper userTopicMapper;

    @Test
    public void init(){
        AppConfig appConfig = new AppConfig(AppConfig.INVITE_CODE,"123456");
        appConfigMapper.baseInsert(appConfig);
    }


    @Test
    public void contextLoads() {
    }

    @Test
    public void createTable(){
        createTopicInfoTable();
        createExerciseInfoTable();
        createUserInfoTable();
        createUserExerciseTable();
        createRateTable();
        createAppConfigTable();
        createUserTopicTable();
    }

    @Test
    public void createUserTopicTable(){
        userTopicMapper.baseCreate(new UserTopic());
    }
    @Test
    public void createAppConfigTable(){
        appConfigMapper.baseCreate(new AppConfig());
    }
    @Test
    public void createUserInfoTable(){
        userInfoMapper.baseCreate(new UserInfo());
    }
    @Test
    public void createTopicInfoTable(){
        topicInfoMapper.baseCreate(new TopicInfo());
    }
    @Test
    public void createExerciseInfoTable(){
        exerciseMapper.baseCreate(new ExerciseInfo());
    }

    @Test
    public void createRateTable(){
        rateMapper.baseCreate(new Rate());
    }

    @Test
    public void createUserExerciseTable(){
        userExerciseMapper.baseCreate(new UserExercise());
    }


    @Test
    public void insertUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setType(1);
        userInfo.setEmail("a@a.edu");
        userInfo.setSid("u100");
        userInfo.setPassword("123456");
        try{
            userInfoMapper.baseInsertAndReturnKey(userInfo);
        }catch (DuplicateKeyException e){
            Console.print("error",e.getMessage());
        }

        Console.print("userInfo",userInfo.getId(),userInfo);
    }
    @Test
    public void updateUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo = userInfoMapper.baseSelectById(userInfo);
        Console.print("更新前",userInfo);
        userInfo.setFirstName("FirstName");
        userInfo.setLastName("LastName");
        userInfo.setAvatarId(1);
        userInfoMapper.baseUpdateById(userInfo);
        userInfo = userInfoMapper.baseSelectById(userInfo);
        Console.print("更新后",userInfo);
    }
    @Test
    public void getUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setType(1);
        userInfo.setEmail("guyexing@foxmail.com");
        userInfo.setBaseKyleUseAnd(true);
        List<UserInfo> list = userInfoMapper.baseSelectByCondition(userInfo);
        Console.print("getUserInfo",list);
    }

    @Test
    public void initTestData(){

        for(int i = 0 ; i < 20 ; i++){
            UserInfo userInfo = new UserInfo();
            userInfo.setType(1);
            userInfo.setEmail(StringUtils.getAllCharString(10));
            userInfo.setSid(StringUtils.getAllCharString(10));
            userInfo.setPassword("123456");
            userInfo.setAvatarId(new Random().nextInt(10) + 1);
            userInfo.setFirstName("Test"+i);
            userInfo.setLastName(StringUtils.getAllCharString(4));

            try{
                userInfoMapper.baseInsertAndReturnKey(userInfo);
                Rate rate = new Rate(userInfo.getId());
                rate.setScore(new Random().nextInt(2000) + 100);
                rateMapper.baseInsert(rate);
            }catch (DuplicateKeyException e){
                Console.print("error",e.getMessage());
            }
        }
    }
}
