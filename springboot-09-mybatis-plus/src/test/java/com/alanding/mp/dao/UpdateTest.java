package com.alanding.mp.dao;

import com.alanding.mp.entity.User;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateTest extends BaseTest {

    /**
     * 根据主键 id 更新
     */
    @Test
    public void updateById() {
        User user = new User();
        user.setId(1088248166370832385L);
        user.setAge(27);
        user.setEmail("wtf2@baomidou.com");

        int rows = userMapper.updateById(user);
        System.out.println("影响记录数：　" + rows);
    }


    /** ===============================================================================
     *  以下全部是用 lambdaWrapper，也可以换成普通 Wrapper。
     * ============================================================================= */

    /**
     * 根据 where 条件更新
     */
    @Test
    public void updateByWrapper_1() {
        // 这两个条件出现在 where 语句里
        LambdaUpdateWrapper<User> qw = getLambdaUpdate()
            .eq(User::getName, "李艺伟")
            .eq(User::getAge, 28);

        // 这两个条件出现在 set 语句里
        User user = new User()
            .setAge(29)
            .setEmail("lyw2019@baomidou.com");

        int rows = userMapper.update(user, qw);

        System.out.println("影响记录数：　" + rows);
    }


    /**
     * 根据 where 条件更新，条件构造器传入实体类作为 where 条件。
     * 当Controller入参为实体类时使用。
     */
    @Test
    public void updateByWrapper_2() {
        // 这个条件将出现在 where 语句里
        User whereUser = new User().setName("李艺伟");

        LambdaUpdateWrapper<User> qw = getLambdaUpdate(whereUser);

        // 这两个条件出现在 set 语句里
        User user = new User()
            .setAge(29)
            .setEmail("lyw2019@baomidou.com");

        int rows = userMapper.update(user, qw);

        System.out.println("影响记录数：　" + rows);
    }


    /**
     * 根据 where 条件构造器更新，直接链式设置新值
     */
    @Test
    public void updateByWrapper_3() {
        LambdaUpdateWrapper<User> qw = getLambdaUpdate()
        // 这两个条件出现在 where 语句里
            .eq(User::getName, "李艺伟")
            .eq(User::getAge, 29)

            // 这两个条件出现在 set 语句里
            .set(User::getAge, 28)
            .set(User::getEmail, "2lyw2019@baomidou.com");

        int rows = userMapper.update(null, qw);
        System.out.println("影响记录数：　" + rows);
    }


    /**
     * 根据 where 条件构造器更新，全链式调用
     */
    @Test
    public void updateByWrapperLambdaChain_4() {
        // 注意，这个只会返回是否成功，不会返回影响记录数
        System.out.println(
                getLambdaUpdateChain()
                // 这两个条件出现在 where 语句里
                .eq(User::getName, "李艺伟")
                .eq(User::getAge, 29)

                // 这两个条件出现在 set 语句里
                .set(User::getAge, 28)
                .set(User::getEmail, "2lyw2019@baomidou.com")

                // 执行 update()，只不过是前面那种方式的封装。
                .update());
    }
}

