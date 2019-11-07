package com.justdoit.kyle.controller;

import com.justdoit.kyle.common.response.MyResponse;
import com.justdoit.kyle.common.util.StringUtils;
import com.justdoit.kyle.entity.CourseInfo;
import com.justdoit.kyle.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseService service;

    @PostMapping
    public ResponseEntity save(String title,String subTitle,String img,String overview,float price){
        if(StringUtils.isEmpty(title,subTitle,overview)){
            return MyResponse.badRequest();
        }
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setTitle(title);
        courseInfo.setSubTitle(subTitle);
        courseInfo.setImg(img);
        courseInfo.setOverview(overview);
        courseInfo.setPrice(price);

        return MyResponse.ok(service.save(courseInfo));
    }

    @GetMapping
    public ResponseEntity getAll(){
        return MyResponse.ok(service.getAll());
    }
}
