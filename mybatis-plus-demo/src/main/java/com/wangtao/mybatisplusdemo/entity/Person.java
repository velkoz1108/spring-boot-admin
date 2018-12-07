package com.wangtao.mybatisplusdemo.entity;

import com.wangtao.mybatisplusdemo.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangtao
 * @since 2018-12-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Person extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String address;

    private Integer age;

    private String name;


}
