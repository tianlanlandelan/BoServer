package com.justdoit.kyle;

import com.justdoit.kyle.common.util.Console;
import com.justdoit.kyle.common.util.SendEmailUtils;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.config.MyConfig;
import com.justdoit.kyle.config.PublicConfig;
import com.justdoit.kyle.entity.*;
import com.justdoit.kyle.mapper.*;
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
public class ApplicationTests {
    @Resource
    private MyConfig myConfig;

    @Resource
    private AppConfigMapper appConfigMapper;
    @Resource
    private ChapterMapper chapterMapper;
    @Resource
    private NotesMapper notesMapper;
    @Resource
    private EmailMapper emailMapper;

    @Resource
    private NoteMapper noteMapper;

    @Resource
    private UserInfoMapper userInfoMapper;


    @Test
    public void init(){
        AppConfig appConfig = new AppConfig(AppConfig.INVITE_CODE,"123456");
        appConfigMapper.baseInsert(appConfig);
    }


    @Test
    public void contextLoads() {
    }
    @Test
    public void createNotesTable(){
        notesMapper.baseCreate(new Notes());
    }

    @Test
    public void createTable(){
        createAppConfigTable();
        createChapterTable();
        createNotesTable();
        createEmailTable();
        createNoteTable();
        createUserInfoTable();
    }

    @Test
    public void createEmailTable(){
        emailMapper.baseCreate(new EmailLog());
    }
    @Test
    public void createAppConfigTable(){
        appConfigMapper.baseCreate(new AppConfig());
    }
    @Test
    public void createChapterTable(){
        chapterMapper.baseCreate(new Chapter());
    }
    @Test
    public void createUserInfoTable(){
        userInfoMapper.baseCreate(new UserInfo());
    }


    @Test
    public void createNoteTable(){
        noteMapper.baseCreate(new Note());
    }


    @Test
    public void insertUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setType(1);
        userInfo.setEmail("a@a.edu");
        userInfo.setPassword("123456");
        try{
            userInfoMapper.baseInsertAndReturnKey(userInfo);
        }catch (DuplicateKeyException e){
            Console.print("error",e.getMessage());
        }

        Console.print("userInfo",userInfo.getId(),userInfo);
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
            userInfo.setPassword("123456");
            userInfo.setAvatarId(new Random().nextInt(10) + 1);
            userInfo.setNickName("Test"+i);
            userInfoMapper.baseInsertAndReturnKey(userInfo);
        }
    }


    @Test
    public void sendEmail() throws Exception{
        Console.println("",myConfig.mailServerUser);
        SendEmailUtils.getSender(myConfig.mailServerHost,myConfig.mailServerUser,myConfig.mailServerPassword)
                .sendSimpleMail("atomiclong@aliyun.com",
                        "你好",
                        "邮件内容");

    }

    @Test
    public void sendRegisterCode() throws Exception{
        Console.println("",myConfig.mailServerUser);
        EmailLog emailLog = SendEmailUtils.getSender(myConfig.mailServerHost,myConfig.mailServerUser,myConfig.mailServerPassword)
                .sendVCode(PublicConfig.RegisterType,"atomiclong@aliyun.com");
        Console.println("emailLog",emailLog);
    }
}
