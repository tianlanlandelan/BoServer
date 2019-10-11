package com.example.demo.controller;

import com.example.demo.common.response.MyResponse;
import com.example.demo.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/image")
/**
 * @author yangkaile
 * @date 2019-05-30 22:10:15
 */
public class ImageController {
    Logger logger = Logger.getLogger(ImageController.class.getName());
    @Resource
    private ImageService imageService;


    @PostMapping("/upload")
    public ResponseEntity upload(MultipartFile file){
        try{
            return MyResponse.ok(imageService.uploadImage(file));
        }catch (IOException e){
            e.printStackTrace();
            logger.warning(e.getMessage());
            return MyResponse.error("图片上传失败");
        }
    }
}
