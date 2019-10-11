package com.example.demo.controller;

/**
 * 请求参数验证工具类
 * @author yangkaile
 * @date 2019-10-11 16:19:26
 */
public class RequestUtil {
    public static boolean validType(Integer type){
        if(type == null || type > 6 || type < 1 ){
            return false;
        }
        return true;
    }
}
