package com.example.demo.controller;

import com.example.demo.common.response.MyResponse;
import com.example.demo.service.UploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2019-10-15 15:00:34
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Resource
    UploadService uploadService;

    @PostMapping("/img")
    public ResponseEntity uploadImg(MultipartFile file){
        return MyResponse.ok(uploadService.uploadImg(file));
    }


    @PostMapping("/video")
    public ResponseEntity uploadVideo(MultipartFile file){
        return MyResponse.ok(uploadService.uploadVideo(file));
    }

}
