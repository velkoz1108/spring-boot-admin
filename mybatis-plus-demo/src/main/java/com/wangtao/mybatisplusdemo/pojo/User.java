package com.wangtao.mybatisplusdemo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.wangtao.mybatisplusdemo.common.AgeEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author : wangtao
 * @date : 2018/12/6 10:11  星期四
 */


@Data
public class User {
    /**
     * 数据库设置了主键自动增长可以用IdType.AUTO
     * 无@TableId注解，也没有设置自动增长时，数据库保存的id是：1070863178585776130
     */
//    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    private String name;
    //    private Integer age;

    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private AgeEnum age;
    private String email;

    @TableLogic
    private Integer status;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @Version
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;

//    @Version
    private Long version;
}
