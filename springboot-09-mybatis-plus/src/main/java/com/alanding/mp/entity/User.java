package com.alanding.mp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/23 下午7:19
 *  @File        :  User.java
 *  @Description :
 */

// 表名如果有前缀，但不想改实体类名，可以用TableName注解指定表名，如 mp_user；
// 如果全库都是有前缀的，可以在配置文件设置全局属性 table-prefix

@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
@TableName("user")
public class User extends Model<User> {
    // 主键名有前缀，但不想改主键名，可以用这个注解指定主键名，如 user_id
    @TableId(value = "id" , type = IdType.AUTO)  // type 指定主键自增策略，还可在配置文件配置全局的策略
    private Long id;

    // 姓名
    @TableField("name")                 // 实体类属性和表字段名不同，比如表字段有前缀，但不想改实体类属性名，可以用这个注解指定对应的表字段名
    private String name;

    // 年龄
    private Integer age;

    // 邮箱
    private String email;

    // 直属上级
    private Long managerId;

    // 创建时间 - 由Mybatis自动填充
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    // 修改时间 - 由Mybatis自动填充
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    // 备注
    @TableField(exist = false)          // 该字段业务上需要用到，但数据库中不存在这个字段，也不需要存入数据库
    private String remark;

    // 逻辑删除标志 - 由Mybatis自动处理
    @TableLogic
    @TableField(select = false)        // 标识在查询中排除这个字段，但是自定义查询语句需要另外设置，它不会给自动排除。
    private Integer deleted;           // 1、可以上层 构造Wrapper时排除。 ２、可以在 sql 语句里不查询出来

    // 版本
    @Version
    private Integer version;
}
