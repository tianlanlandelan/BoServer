package com.example.demo.controller;

import com.example.demo.common.util.StringUtils;

import java.util.Arrays;

/**
 * 请求参数验证工具类
 * @author yangkaile
 * @date 2019-10-11 16:19:26
 */
public class RequestUtil {

    private static final String[] ANSWERS = new String[]{"A","B","C","D"};

    /**
     * 验证是不是子网站的用户类型 1 ~ 6
     * @param type
     * @return
     */
    public static boolean validType(Integer type){
        if(type == null || type > 6 || type < 1 ){
            return false;
        }
        return true;
    }

    /**
     * 验证是不是单选题答案 A B C D
     * @param answer
     * @return
     */
    public static boolean validAnswer(String answer){
        return StringUtils.isNotEmpty(answer) && inArray(ANSWERS,answer);
    }

    public static boolean inArray(String[] array,String str){
        int index = Arrays.binarySearch(array,str);
        return index != -1;
    }

}
