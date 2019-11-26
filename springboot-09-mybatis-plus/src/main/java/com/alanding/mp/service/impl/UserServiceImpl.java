package com.alanding.mp.service.impl;

import com.alanding.mp.dao.UserMapper;
import com.alanding.mp.entity.User;
import com.alanding.mp.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/26 下午6:27
 *  @File        :  UerServiceImpl.java
 *  @Description :
 */


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
}
