package com.wangtao.mybatisplusdemo.common;

/**
 * @author : wangtao
 * @date : 2018/12/7 11:27  星期五
 */


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 测试枚举
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AgeEnum implements IEnum<Integer> {
    /**
     * 年龄
     */
    TEN(10, "十岁"),
    TWENTY(20, "二十岁"),
    TWENTY_SIX(26, "二十六岁"),
    ;

    private Integer value;
    private String desc;

    AgeEnum(final Integer value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    @Override
    public String toString() {
        return this.getDesc();
    }}