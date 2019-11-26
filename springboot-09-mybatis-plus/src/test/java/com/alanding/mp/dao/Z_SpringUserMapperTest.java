package com.alanding.mp.dao;

import com.alanding.mp.entity.User;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.io.FileNotFoundException;
import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/26 上午11:37
 *  @File        :  UserMapperTest.java
 *  @Description :
 */

// Spring中测试的原始方法
public class Z_SpringUserMapperTest extends BaseTest {
    private static UserMapper mapper;

    @Override
    public UserMapper getBaseMapper() {
        return mapper;
    }

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new MybatisSqlSessionFactoryBuilder()
            .build(Z_SpringUserMapperTest.class.getClassLoader().getResourceAsStream(
                "mybatisTestConfiguration/UserMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(UserMapper.class, builder.openSession(false));
    }

    @Test
    public void testSelectAll() throws FileNotFoundException {
        LambdaQueryWrapper<User> querywrapper = getLambdaQuery()
            .like(User::getName, "雨")
            .lt(User::getAge, 40);

        List<User> user = mapper.mySelectAll(querywrapper);
        System.out.println(user);
    }
}
