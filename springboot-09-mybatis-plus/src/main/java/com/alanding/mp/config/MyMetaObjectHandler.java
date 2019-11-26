package com.alanding.mp.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import java.time.LocalDateTime;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/26 下午8:37
 *  @File        :  MyMetaObjectHandler.java
 *  @Description :  自动填充字段扩展实现，插入时间，更新时间
 */

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter("createTime")) {
            setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String updateTime = (String ) getFieldValByName("updateTime", metaObject);

        if (metaObject.hasSetter("updateTime") && !StringUtils.isNotEmpty(updateTime)) {
            // 测试是否更新会自动填充
            // System.out.println("我是自动填充");
            setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
