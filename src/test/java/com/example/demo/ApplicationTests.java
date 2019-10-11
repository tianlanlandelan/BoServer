package com.example.demo;

import com.example.demo.common.util.Console;
import com.example.demo.entity.ImageEntity;
import com.example.demo.entity.UserInfo;
import com.example.demo.entity.VideoInfo;
import com.example.demo.mapper.ImageMapper;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.mapper.VideoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ApplicationTests {

    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    ImageMapper imageMapper;

    @Resource
    VideoMapper videoMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void userInfoTest(){
        createUserInfoTable();
        insertUserInfo();
        updateUserInfo();
    }

    @Test
    public void createUserInfoTable(){
        userInfoMapper.baseCreate(new UserInfo());
    }

    @Test
    public void insertUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setType(3);
        userInfo.setEmail("guyexing@foxmail.com");
        userInfo.setSid("A1001");
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
    public void createImageInfoTable(){
        imageMapper.baseCreate(new ImageEntity());
    }

    @Test
    public void createVideoInfoTable(){
        videoMapper.baseCreate(new VideoInfo());
    }
}
