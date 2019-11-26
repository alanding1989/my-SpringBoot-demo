package com.alanding.mp.dao;

import com.alanding.mp.entity.User;
import com.alanding.mp.util.GetWrapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/26 下午2:59
 *  @File        :  BaseTest.java
 *  @Description :
 */

public abstract class BaseTest implements GetWrapper<User> {

    @Autowired
    protected UserMapper userMapper;

    @Override
    public UserMapper getBaseMapper() {
        return userMapper;
    }
}
