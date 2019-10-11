package com.example.demo.service;

import com.example.demo.MyConfig;
import com.example.demo.common.response.ResultData;
import com.example.demo.common.util.DateUtils;
import com.example.demo.common.util.FileUtils;
import com.example.demo.entity.ImageEntity;
import com.example.demo.entity.VideoInfo;
import com.example.demo.mapper.VideoMapper;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * @author yangkaile
 * @date 2019-10-02 07:37:13
 */
@Service
public class VideoService {
    @Resource
    VideoMapper videoMapper;

    @Autowired
    MyConfig myConfig;


    /**
     * 上传图片
     * 上传时对原图进行压缩，同时保存原图和缩略图，在本项目中多用到头像的保存，访问头像时，显示缩略图就行
     * @param file
     * @return
     */
    public ResultData uploadImage(MultipartFile file) throws IOException {

        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setFileType(file.getContentType());
        videoInfo.setOldName(file.getOriginalFilename());

        //解析文件后缀名
        String suffix = FileUtils.getSuffixWithSpilt(file.getOriginalFilename());
        //获取当前时间标记
        String timeMask = DateUtils.getTimeMask();
        //保存路径
        String path = myConfig.VIDEO_PATH + timeMask + suffix;
        //构建Nginx服务器保存视频的相对路径
        videoInfo.setUrl(myConfig.VIDEO_NGINX_PATH + timeMask + suffix);

        //保存视频
        File video = new File(path);
        file.transferTo(video);

        videoMapper.baseInsertAndReturnKey(videoInfo);
        videoInfo.setUrl(myConfig.NGINX_PREFIX + videoInfo.getUrl());
        return ResultData.success(videoInfo);
    }
}
