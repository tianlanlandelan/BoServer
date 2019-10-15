package com.example.demo.common.mybatis;

import java.util.HashMap;
import java.util.Map;

/**
 * Java类型和mysql类型间的转换，仅创建数据表时使用
 * @author yangkaile
 * @date 2019-09-12 09:08:43
 */
public class TypeCaster {
    private static final int MAX = 65535;
    private static Map<String,String> map = new HashMap<>(16);
    static {
        map.put("String","varchar(50)");
        map.put("int","int");
        map.put("Integer","int");
        map.put("long","bigint");
        map.put("Long","bigint");
        map.put("Date","datetime");
        map.put("byte[]","varbinary(50)");
    }
    private static final String STRING = "String";
    private static final String BYTE_ARRAY = "byte[]";
    public static String getType(String key,int length){
        if(length < 0){
         return null;
        }else if(length == 0){
            return map.get(key);
        }else if(length > 0 && length < MAX){
            if(STRING.equals(key)){
                return "varchar(" + length + ")";
            }else if(BYTE_ARRAY.equals(key)){
                return "varbinary(" + length + ")";
            }else {
                return null;
            }
        }else{
            if(STRING.equals(key)){
                return "text";
            }else if(STRING.equals(key)){
                return "blob";
            }else {
                return null;
            }
        }

    }
}
