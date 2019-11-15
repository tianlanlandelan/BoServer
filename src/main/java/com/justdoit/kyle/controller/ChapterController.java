package com.justdoit.kyle.controller;

import com.justdoit.kyle.common.response.MyResponse;
import com.justdoit.kyle.common.util.RequestUtil;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.entity.ChapterInfo;
import com.justdoit.kyle.service.ChapterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 章节管理
 * @author yangkaile
 * @date 2019-11-14 09:36:52
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Resource
    private ChapterService service;

    @PostMapping
    public ResponseEntity save(Integer id,Integer courseId,String name,Integer sort){
        if(RequestUtil.notValidInteger(id,courseId,sort) || StringUtils.isEmpty(name)){
            return MyResponse.badRequest();
        }
        ChapterInfo info = new ChapterInfo(id);
        info.setCourseId(courseId);
        info.setName(name);
        info.setSort(sort);
        return MyResponse.ok(service.save(info));
    }
}
