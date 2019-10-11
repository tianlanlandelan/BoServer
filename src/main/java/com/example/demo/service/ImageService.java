package com.example.demo.service;

import com.example.demo.MyConfig;
import com.example.demo.common.response.ResultData;
import com.example.demo.common.util.DateUtils;
import com.example.demo.common.util.FileUtils;
import com.example.demo.entity.ImageEntity;
import com.example.demo.mapper.ImageMapper;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * @author yangkaile
 * @date 2019-10-02 07:12:06
 */
@Service
public class ImageService {
    @Resource
    private ImageMapper imageMapper;

    @Autowired
    MyConfig myConfig;


    /**
     * 上传图片
     * 上传时对原图进行压缩，同时保存原图和缩略图，在本项目中多用到头像的保存，访问头像时，显示缩略图就行
     * @param file
     * @return
     */
    public ResultData uploadImage(MultipartFile file) throws IOException {

        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setFileType(file.getContentType());
        imageEntity.setOldName(file.getOriginalFilename());

        //解析文件后缀名
        String suffix = FileUtils.getSuffixWithSpilt(file.getOriginalFilename());
        //获取当前时间标记
        String timeMask = DateUtils.getTimeMask();
        //构建原图保存路径
        String imgPath = myConfig.IMG_PATH + timeMask + suffix;
        //构建Nginx服务器保存原图的相对路径
        imageEntity.setUrl(myConfig.IMG_NGINX_PATH + timeMask + suffix);
        //构建缩略图保存路径
        String thumPath = myConfig.THUM_PATH + timeMask  + myConfig.THUM_SUFFIX + suffix;
        //构建Nginx服务器保存缩略图的相对路径
        imageEntity.setThumUrl(myConfig.THUM_NGINX_PATH + timeMask  + myConfig.THUM_SUFFIX + suffix);

        //保存原图
        File img = new File(imgPath);
        file.transferTo(img);


        //保存缩略图
        File thum = new File(thumPath);
        Thumbnails.of(img).size(myConfig.THUM_MAX_WIDTH,myConfig.THUM_MAX_HEIGHT).toFile(thum);

        imageMapper.baseInsertAndReturnKey(imageEntity);
        imageEntity.setUrl(myConfig.NGINX_PREFIX + imageEntity.getUrl());
        imageEntity.setThumUrl(myConfig.NGINX_PREFIX + imageEntity.getThumUrl());
        return ResultData.success(imageEntity);
    }
}
