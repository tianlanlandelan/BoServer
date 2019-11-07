package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.AutoIncrKeyAttribute;
import com.justdoit.kyle.common.mybatis.annotation.FieldAttribute;
import com.justdoit.kyle.common.mybatis.annotation.IndexAttribute;
import com.justdoit.kyle.common.mybatis.annotation.TableAttribute;

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

    @FieldAttribute("头像id")
    private Integer avatarId;

    @FieldAttribute
    private Date createTime = new Date();

    @FieldAttribute
    @IndexAttribute
    private Integer status ;

    public static final int FORGET_PASSWORD = 1;

    public UserInfo() {
    }

    public UserInfo(int id) {
        this.id = id;
    }

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
                '}';
    }
}
