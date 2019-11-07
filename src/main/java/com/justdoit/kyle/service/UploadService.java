package com.justdoit.kyle.service;

import com.justdoit.kyle.config.MyConfig;
import com.justdoit.kyle.common.response.ResultData;
import com.justdoit.kyle.common.util.DateUtils;
import com.justdoit.kyle.common.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author yangkaile
 * @date 2019-10-02 07:12:06
 */
@Service
public class UploadService {

    @Autowired
    MyConfig myConfig;

    private final static int IMG = 1;
    private final static int VIDEO  = 2;


    public ResultData uploadImg(MultipartFile file){
        try {
            String url = upload(file,IMG);
            return ResultData.success(url);
        }catch(Exception e){
            e.printStackTrace();
            return ResultData.error("图片上传失败");
        }
    }
    public ResultData uploadVideo(MultipartFile file){
        try {
            String url = upload(file,VIDEO);
            return ResultData.success(url);
        }catch(Exception e){
            e.printStackTrace();
            return ResultData.error("视频上传失败");
        }
    }

    private String upload(MultipartFile multipartFile,int type) throws IOException {
        //解析文件后缀名
        String suffix = FileUtils.getSuffixWithSpilt(multipartFile.getOriginalFilename());
        //获取当前时间标记
        String timeMask = DateUtils.getTimeMask();
        //构建文件名
        String fileName = timeMask + suffix;
        //文件保存路径
        String path ;
        //Nginx服务器保存文件的相对路径
        String url;

        if(type == IMG){
            path = myConfig.IMG_PATH + fileName;
            url = myConfig.IMG_NGINX_PATH + fileName;
        }else {
            path = myConfig.VIDEO_PATH  + fileName;
            url = myConfig.VIDEO_NGINX_PATH + fileName;
        }

        //保存文件
        File file = new File(path);
        multipartFile.transferTo(file);

        //构建Nginx服务器保存文件的相对路径
        return url;
    }


}
