package com.alanding.mp.plugin;

import com.alanding.mp.dao.BaseTest;
import com.alanding.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/26 下午9:31
 *  @File        :  OptTest.java
 *  @Description :  乐观锁插件测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OptTest extends BaseTest {

    /**
     * 乐观锁只支持 updateById(id)与 update(entity, wrapper)方法
     * 一个函数中 wrapper不能复用。
     */
    @Test
    public void updateById() {
        // 模拟当前线程查出该用户版本号为1，如果更新时版本号没变，那更新成功，如果在这期间有别的线程
        // 更新该用户，就会失败。
        int version = 1;

        User user = new User()
        .setId(1088250446457389058L)
        .setVersion(version)
        .setEmail("llll@baomidou.com");

        int rows = userMapper.updateById(user);
        System.out.println("影响记录数：　" + rows);
    }
}
