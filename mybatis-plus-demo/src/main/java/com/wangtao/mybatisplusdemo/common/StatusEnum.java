package com.wangtao.mybatisplusdemo.common;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author : wangtao
 * @date : 2018/12/7 11:58  星期五
 */

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusEnum implements IEnum<Integer> {
    /**
     * 状态
     */
    NORMAL(0, "正常"),

    DELETE(1, "删除"),

    ;

    private int value;

    private String desc;


    StatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return null;
    }

    public String getDesc() {
        return desc;
    }


    @Override
    public String toString() {
        return this.getDesc();
    }
}
