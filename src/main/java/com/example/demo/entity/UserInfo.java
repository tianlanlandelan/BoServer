package com.example.demo.entity;

import com.example.demo.common.mybatis.BaseEntity;
import com.example.demo.common.mybatis.annotation.AutoIncrKeyAttribute;
import com.example.demo.common.mybatis.annotation.FieldAttribute;
import com.example.demo.common.mybatis.annotation.IndexAttribute;
import com.example.demo.common.mybatis.annotation.TableAttribute;

import java.util.Date;

/**
 * @author yangkaile
 * @date 2019-10-01 13:34:14
 */
@TableAttribute(name = "user_info",comment = "用户信息表")
public class UserInfo extends BaseEntity {
    @AutoIncrKeyAttribute
    @FieldAttribute
    private int id;

    @FieldAttribute(value = "用户类型",notNull = true)
    @IndexAttribute
    private Integer type;

    @FieldAttribute(length = 200)
    private String firstName;

    @FieldAttribute(length = 200)
    private String lastName;

    @FieldAttribute(value = "密码",length = 200)
    private String password;

    @FieldAttribute(value = "学校邮箱",notNull = true,length = 200)
    @IndexAttribute
    private String email;

    @FieldAttribute(value = "学号",notNull = true,length = 200)
    @IndexAttribute
    private String sid;

    @FieldAttribute
    private Integer avatarId;

    @FieldAttribute
    private Date createTime = new Date();

    @FieldAttribute
    @IndexAttribute
    private Integer status ;

    @FieldAttribute("用户当前在学的课程")
    private Integer topicId;
    @FieldAttribute("课程视频是否看过")
    private int vodeoStatus;
    @FieldAttribute("用户当前在做的练习")
    private Integer exerciseId;
    @FieldAttribute("用户答题剩余时间")
    private Integer timer;



    @FieldAttribute
    private int topicOver;

    public static final int VODEO_OVER = 1;
    public static final int FORGET_PASSWORD = 1;
    public static final int OVER = 1;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Integer avatarId) {
        this.avatarId = avatarId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getTopicOver() {
        return topicOver;
    }

    public void setTopicOver(int topicOver) {
        this.topicOver = topicOver;
    }

    public Integer getTimer() {
        return timer;
    }

    public void setTimer(Integer timer) {
        this.timer = timer;
    }

    public int getVodeoStatus() {
        return vodeoStatus;
    }

    public void setVodeoStatus(int vodeoStatus) {
        this.vodeoStatus = vodeoStatus;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", type=" + type +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", sid='" + sid + '\'' +
                ", avatarId=" + avatarId +
                ", createTime=" + createTime +
                ", status=" + status +
                ", topicId=" + topicId +
                ", vodeoStatus=" + vodeoStatus +
                ", exerciseId=" + exerciseId +
                ", timer=" + timer +
                ", topicOver=" + topicOver +
                '}';
    }
}
