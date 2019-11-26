package com.alanding.mp.service.impl;

import com.alanding.mp.dao.BaseTest;
import com.alanding.mp.entity.User;
import com.alanding.mp.service.UserService;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest extends BaseTest {

    @Autowired
    private UserService userService;


    /** ===============================================================================
     * 不直接链式调用，需要传入 条件构造器 Wrapper
     * ============================================================================= */

    @Test
    public void getOne() {
        // 超过一个会报错，可在getOne()中传入第二个参数false，就只会警告
        User one = userService.getOne(getLambdaQuery().gt(User::getAge, 25), false);
        System.out.println(one);
    }


    /**
     * 批量插入
     */
    @Test
    public void saveBatch() {
        List<User> users = Arrays.asList(
            new User().setName("徐丽").setAge(28),
            new User().setName("徐丽2").setAge(29)
        );

        boolean b = userService.saveBatch(users, 2);
        System.out.println(b);
    }


    /**
     * 插入或更新
     * 注意：　如果设置了主键就先查询，没有记录就插入
     *        如果未设置主键就直接插入，可能产生垃圾数据
     */
    @Test
    public void saveOrUpdateBatch() {
        List<User> users = Arrays.asList(
            new User().setName("徐丽").setAge(28),
            new User().setName("向北").setAge(29)
                      .setEmail("bao@baomidou.com")
                      .setId(1094592041087729667L)
        );

        boolean b = userService.saveOrUpdateBatch(users, 2);
        System.out.println(b);
    }


    /** ===============================================================================
     * 直接链式调用，不需要传入 条件构造器 Wrapper
     * ============================================================================= */

    /**
     * 查询链式调用
     */
    @Test
    public void listChain() {
        List<User> users = userService.lambdaQuery()
                                      .gt(User::getAge, 25)
                                      .like(User::getName, "雨")
                                      .list();
        users.forEach(System.out::println);
    }

    /**
     * 更新链式调用，可以批量进行
     */
    @Test
    public void updateChain() {
        boolean b = userService.lambdaUpdate()
                               .eq(User::getAge, 28)
                               .set(User::getEmail, "bao@baomidou.com")
                               .update();
        System.out.println(b);
    }


    /**
     * 删除链式调用，可以批量进行
     */
    @Test
    public void deleteChain() {
        boolean b = userService.lambdaUpdate()
                               .eq(User::getAge, 25)
                               .remove();
        System.out.println(b);

        // 上面其实是 Wrapper封装到了通用 IService 中，自己实现这个接口也很方便
        // boolean remove = getLambdaUpdateChain().eq(User::getAge, 28).remove();
        // System.out.println(remove);
    }
}
