package com.justdoit.kyle.config;

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
    public String nginxPrefix;
    /**
     * 原图保存路径
     */
    @Value("${server.image.path}")
    public String imgPath;

    /**
     * Nginx服务器保存的原图的相对路径
     */
    @Value("${nginx.image.path}")
    public String imgNginxPath;


    /**
     * 视频保存路径
     */
    @Value("${server.video.path}")
    public String videoPath;

    /**
     * Nginx保存视频的相对路径
     */
    @Value("${nginx.video.path}")
    public String videoNginxPath;


    /**
     * 邮件服务器地址
     */
    @Value("${mail.server.host}")
    public  String mailServerHost;
    /**
     * 发件人名称
     */
    @Value("${mail.server.user}")
    public  String mailServerUser;
    /**
     * 发件人密码
     */
    @Value("${mail.server.password}")
    public  String mailServerPassword;


}
