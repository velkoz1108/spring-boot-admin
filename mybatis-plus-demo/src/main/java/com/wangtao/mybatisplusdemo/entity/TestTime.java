package com.wangtao.mybatisplusdemo.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
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
 * @since 2018-12-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TestTime extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private LocalTime createTime;

    private LocalDateTime createTime2;

    private LocalDate createTime3;

    private LocalDateTime createTime4;


}
