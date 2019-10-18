package com.example.demo.controller;

import com.example.demo.common.response.MyResponse;
import com.example.demo.common.util.StringUtils;
import com.example.demo.entity.ExerciseInfo;
import com.example.demo.service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;

/**
 * @author yangkaile
 * @date 2019-10-17 16:48:52
 */
@RestController
@RequestMapping("/exerciseInfo")
public class ExerciseController {
    @Resource
    private ExerciseService service;

    /**
     * 添加练习，如果练习绑定的课程不存在，添加失败
     * @param topicId
     * @param sort
     * @param title
     * @param content
     * @param img
     * @param question
     * @param optionA
     * @param optionB
     * @param optionC
     * @param optionD
     * @param answer
     * @return
     */
    @PostMapping
    public ResponseEntity save(Integer topicId,Integer sort,String title,String content,
                               String img,String question,String optionA,
                               String optionB,String optionC,String optionD,
                               String answer){
        if(topicId == null || topicId < 0 || !RequestUtil.validAnswer(answer) ||
                StringUtils.isEmpty(title,content,question,optionA,optionB,optionC,optionD)) {
            return MyResponse.badRequest();
        }
        ExerciseInfo info = new ExerciseInfo();
        info.setTopicId(topicId);
        info.setSort(sort);
        info.setTitle(title);
        info.setContent(content);
        info.setImg(img);
        info.setQuestion(question);
        info.setOptionA(optionA);
        info.setOptionB(optionB);
        info.setOptionC(optionC);
        info.setOptionD(optionD);
        info.setAnswer(answer);

        return MyResponse.ok(service.save(info));
    }
}