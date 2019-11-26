package com.alanding.mp.dao;

import com.alanding.mp.entity.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 *  @Author      :   AlanDing
 *  @Time        :   2019/11/23 下午7:23
 *  @File        :   UserMapper.java
 *  @Description :
 */

public interface UserMapper extends MyBaseMapper<User> {

    // 自定义sql语句
    List<User> mySelectAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    // 多表联查自定义查询方法
    IPage<Map<String, Object>> selectUserMapPage(IPage<User> page,
                                                 @Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
