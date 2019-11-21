package com.justdoit.kyle.controller;

import com.justdoit.kyle.common.response.MyResponse;
import com.justdoit.kyle.common.util.RequestUtil;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.entity.ChapterInfo;
import com.justdoit.kyle.service.ChapterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 获取章节列表
     * @param courseId
     * @return
     */
    @GetMapping
    public ResponseEntity get(Integer courseId){
        if(RequestUtil.notValidInteger(courseId)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(service.getByCourseId(courseId));
    }

    /**
     * 修改章节，可修改排序和名称
     * @param id
     * @param sort
     * @return
     */
    @PutMapping
    public ResponseEntity update(Integer id,String name,Integer sort){
        if(RequestUtil.notValidInteger(id,sort) || StringUtils.isEmpty(name)){
            return MyResponse.badRequest();
        }
        ChapterInfo info = new ChapterInfo(id);
        info.setName(name);
        info.setSort(sort);
        return MyResponse.ok(service.update(info));
    }

    /**
     * 删除章节
     * @param id 章节id
     * @return
     */
    @DeleteMapping
    public ResponseEntity delete(Integer id){
        if(RequestUtil.notValidInteger(id)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(service.deleteById(id));
    }
}
