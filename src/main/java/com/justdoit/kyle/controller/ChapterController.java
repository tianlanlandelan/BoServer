package com.justdoit.kyle.controller;

import com.justdoit.kyle.common.response.MyResponse;
import com.justdoit.kyle.common.util.RequestUtil;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.entity.Chapter;
import com.justdoit.kyle.service.ChapterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 笔记本目录
 * @author yangkaile
 * @date 2019-11-14 09:36:52
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Resource
    private ChapterService service;

    /**
     * 保存目录
     * 有则修改，无则添加
     * @param id id
     * @param notesId 笔记本ID
     * @param name 章节名称
     * @param sort 排序
     * @return
     */
    @PostMapping
    public ResponseEntity save(Integer id,Integer notesId,String name,Integer sort){
        if(RequestUtil.notValidInteger(id,notesId,sort) || StringUtils.isEmpty(name)){
            return MyResponse.badRequest();
        }
        Chapter info = new Chapter(id);
        info.setNotesId(notesId);
        info.setName(name);
        info.setSort(sort);
        return MyResponse.ok(service.save(info));
    }

    /**
     * 获取目录列表
     * @param notesId 笔记本ID
     * @return
     */
    @GetMapping
    public ResponseEntity get(Integer notesId){
        if(RequestUtil.notValidInteger(notesId)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(service.getByNotesId(notesId));
    }

    /**
     * 修改目录，可修改排序和名称
     * @param id
     * @param sort
     * @return
     */
    @PutMapping
    public ResponseEntity update(Integer id,String name,Integer sort){
        if(RequestUtil.notValidInteger(id,sort) || StringUtils.isEmpty(name)){
            return MyResponse.badRequest();
        }
        Chapter info = new Chapter(id);
        info.setName(name);
        info.setSort(sort);
        return MyResponse.ok(service.update(info));
    }

    /**
     * 删除目录
     * @param id id
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
