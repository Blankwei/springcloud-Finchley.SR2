package com.savoidage.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Author: created by savoidage
 * CreateTime: 2020-04-09 17:29
 * Description: json对象转化工具类
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转换为json字符串
     * @param object object
     * @return json字符串
     */
    public static String Object2Json(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json字符串转化为对象
     * @param jsonText json字符串
     * @param objClass 转化对象
     * @param <T> 泛型对象
     * @return 目标对象
     * @throws IOException 异常
     */
    public static <T> T Json2Object(String jsonText, Class<T> objClass) throws IOException {
        return objectMapper.readValue(jsonText, objClass);
    }
}
