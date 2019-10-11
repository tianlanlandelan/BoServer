package com.example.demo.entity;

import com.example.demo.common.mybatis.annotation.AutoIncrKeyAttribute;
import com.example.demo.common.mybatis.annotation.FieldAttribute;
import com.example.demo.common.mybatis.annotation.TableAttribute;

import java.util.Date;

/**
 * @author yangkaile
 * @date 2019-05-30 08:42:44
 * 本项目中图片只支持按照id获取，因此不继承BaseEntity
 */
@TableAttribute(name = "image_info",comment = "图片信息表，主要存储用户头像等")
public class ImageEntity{
    /**
     * id
     */
    @AutoIncrKeyAttribute
    @FieldAttribute
    private int id;
    /**
     * 原文件名
     */
    @FieldAttribute("原文件名")
    private String oldName;
    /**
     * 原图保存路径
     */
    @FieldAttribute(value = "原图保存路径",notNull = true,length = 200)
    private String url;
    /**
     * 缩略图保存路径
     */
    @FieldAttribute(value = "缩略图保存路径",notNull = true,length = 200)
    private String thumUrl;
    /**
     * 宽度
     */
    @FieldAttribute(value = "原图宽度",notNull = true)
    private int width;
    /**
     * 高度
     */
    @FieldAttribute(value = "原图高度",notNull = true)
    private int height;
    /**
     * 文件类型
     */
    @FieldAttribute(value = "文件类型",notNull = true)
    private String fileType;

    /**
     * 上传日期
     */
    @FieldAttribute("上传时间")
    private Date createTime = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumUrl() {
        return thumUrl;
    }

    public void setThumUrl(String thumUrl) {
        this.thumUrl = thumUrl;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ImageEntity{" +
                "id=" + id +
                ", oldName='" + oldName + '\'' +
                ", url='" + url + '\'' +
                ", thumUrl='" + thumUrl + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", fileType='" + fileType + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
