package com.alanding.mp.dao;

import com.alanding.mp.entity.User;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ARTest extends BaseTest {

    // ActiveRecord 模式 CRUD
    // <p>
    // 必须存在对应的原始mapper并继承baseMapper并且可以使用的前提下
    // 才能使用此 AR 模式 !!!

    /**
     * 直接用实体类进行操作，
     */
    @Test
    public void insert() {
        boolean ifornot = new User()
            .setEmail("...@baomidou.com")
            .setId(12323454L)
            .setName("刘花")
            .setAge(29)
            .setManagerId(12323L)
            .setCreateTime(LocalDateTime.now())
            .setRemark("我是一个备注信息哦~~")
            .insert();

        System.out.println("影响记录数：　" + ifornot);
    }


    /**
     * 如果设置了主键 id，则根据主键先查询，有记录，就更新，没有记录就插入。
     * 如果没有设置主键，那自动插入
     */
    @Test
    public void insertOrUpdate() {
        boolean ifornot = new User()
            .setEmail("...@baomidou.com")
            // 注意：　如果设置了主键 id，则根据主键先查询，有记录，就更新，没有记录就插入。
            //        如果没有设置主键，那自动插入
            .setId(12321234453244L)
            .setName("小黎黎")
            .setAge(29)
            .setManagerId(12323L)
            .setCreateTime(LocalDateTime.now())
            .setRemark("我是一个备注信息哦~~")
            .insertOrUpdate();

        System.out.println("影响记录数：　" + ifornot);
    }


    @Test
    public void updateById() {
        // Id 将出现在 where 查询语句中
        User user = new User().setId(12323454L)
                              // 名字将出现在　set 语句中更新
                              .setName("王阳明");
        boolean userSelect = user.updateById();

        System.out.println(userSelect);
    }


    @Test
    public void selectById() {
        User user = new User();
        User userSelect = user.selectById(12323454L);
        // 又创建了一个新对象返回
        System.out.println(user == userSelect);
        System.out.println(user);
    }


    /**
     * Id 将出现在 where 查询语句中，最新版本删除不存在的返回 false。3.1.1 之前是返回 true
     * 查看源码
     * @deprecated 3.1.1 {@link com.baomidou.mybatisplus.extension.toolkit.SqlHelper#delBool(java.lang.Integer)}
     */
    @Test
    public void deleteById() {
        boolean user = new User().deleteById(123232334);

        System.out.println(user);
    }

    /** ===============================================================================
     * 全用 条件构造器 Wrapper 操作
     * ============================================================================= */


}
