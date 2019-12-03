package com.justdoit.kyle.controller;

import com.justdoit.kyle.common.response.MyResponse;
import com.justdoit.kyle.common.util.RequestUtil;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.entity.Notes;
import com.justdoit.kyle.mapper.ChapterMapper;
import com.justdoit.kyle.service.NotesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 笔记本
 * @author yangkaile
 * @date 2019-11-12 14:29:39
 */
@RestController
@RequestMapping("/notes")
public class NotesController {
    @Resource
    private NotesService service;

    /**
     * 保存笔记
     * @param id id
     * @param userId 用户id
     * @param title 标题
     * @param subTitle 副标题
     * @return
     */
    @PostMapping
    public ResponseEntity save(Integer id,Integer userId,String title,String subTitle ){
        if(StringUtils.isEmpty(title) || RequestUtil.notValidInteger(userId)){
            return MyResponse.badRequest();
        }
        Notes notes = new Notes(id,userId);
        notes.setTitle(title);
        notes.setSubTitle(subTitle);
        return MyResponse.ok(service.save(notes));
    }
    @GetMapping("/getList")
    public ResponseEntity getList(Integer userId){
        if(RequestUtil.validInteger(userId)){
            return MyResponse.ok(service.getListByUserId(userId));
        }
        return MyResponse.badRequest();
    }
    @GetMapping
    public ResponseEntity get(Integer id){
        if(RequestUtil.validInteger(id)){
            return MyResponse.ok(service.getById(id));
        }
        return MyResponse.badRequest();
    }
}
