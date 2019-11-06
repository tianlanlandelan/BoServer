package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 项目配置文件，从application.properties中加载
 * @author yangkaile
 * @date 2019-05-30 09:38:05
 */
@Configuration
@PropertySource("classpath:application.properties")
public class MyConfig {
    /**
     * Nginx 服务器地址
     */
    @Value("${nginx.prefix}")
    public String NGINX_PREFIX ;
    /**
     * 原图保存路径
     */
    @Value("${server.image.path}")
    public String IMG_PATH ;

    /**
     * Nginx服务器保存的原图的相对路径
     */
    @Value("${nginx.image.path}")
    public String IMG_NGINX_PATH ;


    /**
     * 视频保存路径
     */
    @Value("${server.video.path}")
    public String VIDEO_PATH ;

    /**
     * Nginx保存视频的相对路径
     */
    @Value("${nginx.video.path}")
    public String VIDEO_NGINX_PATH ;

}
