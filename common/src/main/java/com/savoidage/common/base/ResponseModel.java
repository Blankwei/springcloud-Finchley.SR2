
package com.savoidage.common.base;


import com.savoidage.common.utils.CodeUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: created by savoidage
 * CreateTime: 2019-04-23 14:58
 * Description: 项目输出模组
 */
@Setter
@Getter
public class ResponseModel {

    // 状态码
    private Integer code = CodeUtils.SUCCESS;

    // 模组消息
    private String message;

    // 数组组装块
    private Map<String, Object> data = new HashMap<String, Object>();

    // 构造模组(带参数)
    public ResponseModel(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    /**
     * 构造输出 成功 模组
     * @return 模组
     */
    public static ResponseModel getSuccess() {
        return new ResponseModel(CodeUtils.SUCCESS, CodeUtils.SUCCESS_MSG);
    }

    /**
     * 构造输出 失败 模组
     *
     * @return 模组
     */
    public static ResponseModel getFailed() {
        return new ResponseModel(CodeUtils.CODE_FAILED, CodeUtils.CODE_FAILED_MSG);
    }

    /**
     * 添加数据
     * @param key 数据键
     * @param value 数据
     */
    public void addData(String key,Object value){
        if(null == data){
            data = new HashMap<>();
        }
        data.put(key,value);
    }

    /**
     * 添加数组
     * @param map 数据
     */
    public void addData(Map<String,Object> map){
        if(null == data){
            data = new HashMap<>();
        }
        if(null != map){
            data.putAll(map);
        }
    }

}

