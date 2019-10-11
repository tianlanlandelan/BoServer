package com.example.demo.controller;

import com.example.demo.common.response.MyResponse;
import com.example.demo.service.VideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author yangkaile
 * @date 2019-10-02 07:50:51
 */
@RestController
@RequestMapping("/video")
public class VideoController {
    @Resource
    VideoService videoService;

    @PostMapping("/upload")
    public ResponseEntity upload(MultipartFile file){
        try{
            return MyResponse.ok(videoService.uploadImage(file));
        }catch (IOException e){
            e.printStackTrace();
            return MyResponse.error("视频上传失败");
        }
    }
}
