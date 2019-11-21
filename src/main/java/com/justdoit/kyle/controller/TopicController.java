package com.justdoit.kyle.controller;

import com.justdoit.kyle.common.response.MyResponse;
import com.justdoit.kyle.common.util.RequestUtil;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.entity.TopicInfo;
import com.justdoit.kyle.service.ChapterService;
import com.justdoit.kyle.service.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-10-17 14:49:21
 */
@RestController
@RequestMapping("/topicInfo")
public class TopicController {
    @Resource
    private TopicService service;

    @PostMapping
    public ResponseEntity save(Integer id,Integer courseId,Integer chapterId,String title,
                                    String contentMD,String content ,String videoUrl, Integer sort){
        if(StringUtils.isEmpty(title) || RequestUtil.notValidInteger(courseId,chapterId)){
            return MyResponse.badRequest();
        }
        TopicInfo topicInfo = new TopicInfo();
        topicInfo.setId(id);
        topicInfo.setCourseId(courseId);
        topicInfo.setChapterId(chapterId);
        topicInfo.setSort(sort);
        topicInfo.setTitle(title);
        topicInfo.setContentMD(contentMD);
        topicInfo.setContent(content);
        topicInfo.setVideoUrl(videoUrl);

        return MyResponse.ok(service.save(topicInfo));
    }

    @GetMapping("/getList")
    public ResponseEntity getByCourseId(Integer courseId){
        if(RequestUtil.notValidInteger(courseId)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(service.getByCourseId(courseId));
    }
    /**
     * 修改课时所属章节或排序
     * @param id id
     * @param chapterId 章节id
     * @param sort 排序
     * @return
     */
    @PutMapping
    public ResponseEntity update(Integer id,Integer chapterId,Integer sort){
        if(RequestUtil.notValidInteger(id,chapterId,sort)){
            return MyResponse.badRequest();
        }
        TopicInfo topicInfo = new TopicInfo();
        topicInfo.setId(id);
        topicInfo.setChapterId(chapterId);
        topicInfo.setSort(sort);
        return MyResponse.ok(service.updateSort(topicInfo));

    }

    @GetMapping
    public ResponseEntity getById(Integer id){
        if(id == null || id < 0){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(service.getById(id));
    }
    @DeleteMapping
    public ResponseEntity delete(Integer id){
        if(id == null || id < 0){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(service.delete(id));
    }
}
