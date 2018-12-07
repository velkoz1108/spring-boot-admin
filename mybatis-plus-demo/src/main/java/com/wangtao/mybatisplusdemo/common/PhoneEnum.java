package com.wangtao.mybatisplusdemo.common;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author : wangtao
 * @date : 2018/12/7 11:24  星期五
 */

/**
 * 运营商联系电话枚举
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PhoneEnum implements IEnum<String> {
    /**
     * 中国移动
     */
    CMCC("10086", "中国移动"),
    /**
     * 中国联通
     */
    CUCC("10010", "中国联通"),
    /**
     * 中国电信
     */
    CT("10000", "中国电信");

    private String value;
    private String desc;

    PhoneEnum(final String value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }
}
