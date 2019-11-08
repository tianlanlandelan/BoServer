package com.justdoit.kyle.controller;

import com.justdoit.kyle.common.response.MyResponse;
import com.justdoit.kyle.common.util.RequestUtil;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.entity.ExerciseInfo;
import com.justdoit.kyle.service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-10-17 16:48:52
 */
@RestController
@RequestMapping("/exerciseInfo")
public class ExerciseController {
    @Resource
    private ExerciseService service;


    @GetMapping("getAll")
    public ResponseEntity getAll(){
        return MyResponse.ok(service.getAll());
    }

    @GetMapping
    public ResponseEntity getById(Integer id){
        if(id == null || id < 0 ){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(service.getById(id));
    }
}
