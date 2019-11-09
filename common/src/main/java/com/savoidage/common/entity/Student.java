package com.savoidage.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Author: created by savoidage
 * CreateTime: 2019-11-09 13:57
 * Description: Student
 */
@Data
public class Student implements Serializable {

    /**
     * 记录id
     */
    private Integer id;

    /**
     * 姓名
     */
    private String studentName;

    /**
     * 性别
     */
    private Boolean sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 创建时间
     */
    private Long createDate;

    /**
     * 更新时间
     */
    private Long updateDate;

}
