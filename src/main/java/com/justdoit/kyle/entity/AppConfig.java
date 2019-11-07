package com.justdoit.kyle.entity;

import com.justdoit.kyle.common.mybatis.BaseEntity;
import com.justdoit.kyle.common.mybatis.annotation.FieldAttribute;
import com.justdoit.kyle.common.mybatis.annotation.KeyAttribute;
import com.justdoit.kyle.common.mybatis.annotation.TableAttribute;

/**
 * 应用设置，该设置是管理员可修改的
 * @author yangkaile
 * @date 2019-10-28 16:21:56
 */
@TableAttribute(name = "config",comment = "系统配置表")
public class AppConfig extends BaseEntity {
    @FieldAttribute
    @KeyAttribute
    private String k;

    @FieldAttribute
    private String v;

    public AppConfig() {
    }

    public AppConfig(String k) {
        this.k = k;
    }

    public AppConfig(String k, String v) {
        this.k = k;
        this.v = v;
    }

    /**
     * 邀请码
     */
    public static final String INVITE_CODE = "InviteCode";

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "k='" + k + '\'' +
                ", v='" + v + '\'' +
                '}';
    }
}
