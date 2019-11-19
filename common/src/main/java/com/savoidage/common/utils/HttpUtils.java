package com.savoidage.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author: created by savoidage
 * CreateTime: 2019-11-16 17:48
 * Description: HttpUtils
 */
public class HttpUtils {

    /**
     * 获取请求头集合信息
     * @param request 请求对象
     * @return map集合
     */
    public static Map<String,String> getHeaders(HttpServletRequest request){
        Map<String,String> map =  new LinkedHashMap<String,String>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            System.out.println("HEADER - "+key+":"+value);
            map.put(key,value);
        }
        return map;
    }

    /**
     * 获取指定的请求头信息
     * @param request 请求对象
     * @param key 指定头键值
     * @return
     */
    public static String getHeader(HttpServletRequest request,String key){
        Map<String,String> headers = getHeaders(request);
        for(Map.Entry<String,String> map:headers.entrySet()){
            if(map.getKey().equals(key)){
                return map.getValue();
            }
        }
        return null;
    }
}
