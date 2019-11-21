package com.justdoit.kyle;

import com.justdoit.kyle.common.util.Console;
import com.justdoit.kyle.common.util.StringUtils;
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
    private AppConfigMapper appConfigMapper;
    @Resource
    private ChapterMapper chapterMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private ExamExerciseMapper examExerciseMapper;
    @Resource
    private ExamMapper examMapper;
    @Resource
    private ExerciseMapper exerciseMapper;
    @Resource
    private RateMapper rateMapper;
    @Resource
    private SeriesMapper seriesMapper;
    @Resource
    private TopicMapper topicMapper;
    @Resource
    private TopicDiscussionMapper topicDiscussionMapper;
    @Resource
    private UserExamMapper userExamMapper;
    @Resource
    private UserExerciseMapper userExerciseMapper;
    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private SeriesCourseMapper seriesCourseMapper;


    @Test
    public void init(){
        AppConfig appConfig = new AppConfig(AppConfig.INVITE_CODE,"123456");
        appConfigMapper.baseInsert(appConfig);
    }


    @Test
    public void contextLoads() {
    }
    @Test
    public void createCourseTable(){
        courseMapper.baseCreate(new CourseInfo());
    }

    @Test
    public void createTable(){
        createAppConfigTable();
        createChapterTable();
        createCourseTable();
        createExamExerciseTable();
        createExamTable();
        createExerciseInfoTable();
        createRateTable();
        createSeriesTable();
        createSeriesCourseTable();
        createTopicInfoTable();
        createTopicDiscussionTable();
        createUserExamTable();
        createUserExerciseTable();
        createUserInfoTable();
    }

    @Test
    public void createTopicDiscussionTable(){
        topicDiscussionMapper.baseCreate(new TopicDiscussion());
    }
    @Test
    public void createSeriesCourseTable(){
        seriesCourseMapper.baseCreate(new SeriesCourse());
    }
    @Test
    public void createAppConfigTable(){
        appConfigMapper.baseCreate(new AppConfig());
    }
    @Test
    public void createChapterTable(){
        chapterMapper.baseCreate(new ChapterInfo());
    }
    @Test
    public void createUserInfoTable(){
        userInfoMapper.baseCreate(new UserInfo());
    }
    @Test
    public void createExamExerciseTable(){
        examExerciseMapper.baseCreate(new ExamExercise());
    }

    @Test
    public void createExamTable(){
        examMapper.baseCreate(new ExamInfo());
    }
    @Test
    public void createSeriesTable(){
        seriesMapper.baseCreate(new SeriesInfo());
    }

    @Test
    public void createTopicInfoTable(){
        topicMapper.baseCreate(new TopicInfo());
    }

    @Test
    public void createUserExamTable(){
        userExamMapper.baseCreate(new UserExam());
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
}
