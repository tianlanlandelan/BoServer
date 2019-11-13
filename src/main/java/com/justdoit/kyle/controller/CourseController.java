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

/**
 * 课程
 * @author yangkaile
 * @date 2019-11-12 14:29:39
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseService service;

    @PostMapping
    public ResponseEntity save(String title,String subTitle,String img,String overview,Float price,Integer status){
        if(StringUtils.isEmpty(title,subTitle,overview)){
            return MyResponse.badRequest();
        }
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setTitle(title);
        courseInfo.setSubTitle(subTitle);
        courseInfo.setImg(img);
        courseInfo.setOverview(overview);
        if(price != null && price > 0){
            courseInfo.setPrice(price);
        }
        if(status == 0 || status == CourseInfo.SAVE){
            courseInfo.setStatus(status);
        }

        return MyResponse.ok(service.save(courseInfo));
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return MyResponse.ok(service.getAll());
    }
}
