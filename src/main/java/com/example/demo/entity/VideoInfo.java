package com.example.demo.entity;

import com.example.demo.common.mybatis.annotation.AutoIncrKeyAttribute;
import com.example.demo.common.mybatis.annotation.FieldAttribute;
import com.example.demo.common.mybatis.annotation.TableAttribute;

import java.util.Date;

/**
 * @author yangkaile
 * @date 2019-10-02 07:32:41
 * 本项目中视频只支持按照id获取，因此不继承BaseEntity
 */
@TableAttribute(name = "video_info")
public class VideoInfo {
    @AutoIncrKeyAttribute
    @FieldAttribute
    private int id;

    @FieldAttribute(value = "原视频名称",notNull = true,length = 500)
    private String oldName;

    @FieldAttribute(value = "文件类型",notNull = true)
    private String fileType;

    @FieldAttribute(value = "存储地址",notNull = true,length = 500)
    private String url;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
                "id=" + id +
                ", oldName='" + oldName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", url='" + url + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
