package com.wsheng.product.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @Auther: wsheng
 * @Date: 2018/10/24 00:08
 * @Description:
 */
public class JsonUtil {


    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 转换为json字符串
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json 转对象
     * @param string
     * @param classType
     * @return
     */
    public static Object formJson(String string ,Class classType ){
        try {
            return objectMapper.readValue(string,classType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
