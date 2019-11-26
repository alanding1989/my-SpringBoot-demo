package com.alanding.mp.dao;

import com.alanding.mp.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/25 上午12:13
 *  @File        :  PageTest.java
 *  @Description :
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PageTest extends BaseTest {

    /**
     * 返回实体类分页，会查询总记录数
     * １、名字中包含｀雨｀，并且年龄小于40
     *    name like '%雨%' and age < 40
     */
    @Test
    public void selectPageWithCount() {
        QueryWrapper<User> queryWrapper = getQueryWrapper()
            .like("name", "雨")
            .lt("age", 40);

        Page<User> userPage = new Page<>(1, 2);
        IPage<User> userIPage = userMapper.selectPage(userPage, queryWrapper);

        System.out.println("总页数" + userIPage.getPages() + "\n");
        System.out.println("总记录数" + userIPage.getTotal() + "\n");
        userIPage.getRecords().forEach(System.out::println);
    }


    /**
     * 返回 Map 对象分页，会查询总记录数
     * １、名字中包含｀雨｀，并且年龄小于40
     *    name like '%雨%' and age < 40
     */
    @Test
    public void selectMapsPageWithCount() {
        LambdaQueryWrapper<User> queryWrapper = getLambdaQuery()
            .select(User::getName, User::getAge)
            .like(User::getName, "雨")
            .lt(User::getAge, 40);

        Page<User> userPage = new Page<>(1, 2);
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(userPage, queryWrapper);

        System.out.println("总页数" + mapIPage.getPages() + "\n");
        System.out.println("总记录数" + mapIPage.getTotal() + "\n");
        mapIPage.getRecords().forEach(System.out::println);
    }


    /**
     * 不需要总记录数的情况下，返回 Map 对象或实体类对象分页
     * 应用场景： 例如知乎下拉刷新出新的问题或者新闻，不需要查询总记录数，优化查询性能
     * １、名字中包含｀雨｀，并且年龄小于40
     *    name like '%雨%' and age < 40
     */
    @Test
    public void selectMapsPageNoCount() {
        LambdaQueryWrapper<User> queryWrapper = getLambdaQuery()
            .select(User::getName, User::getAge)
            .like(User::getName, "雨")
            .lt(User::getAge, 40);

        // 在构造Page<T>时传入第三个参数为false
        Page<User> userPage = new Page<>(1, 2, false);
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(userPage, queryWrapper);

        // 这时候总记录数和页数都是0
        System.out.println("总页数" + mapIPage.getPages() + "\n");
        System.out.println("总记录数" + mapIPage.getTotal() + "\n");
        mapIPage.getRecords().forEach(System.out::println);
    }


    /**
     * 测试自定义分页查询语句
     * 不需要总记录数的情况下，返回 Map 对象或实体类对象分页
     *
     * １、名字中包含｀雨｀，并且年龄小于40
     *    name like '%雨%' and age < 40
     */
    @Test
    public void selectCustomMapsPageNoCount() {
        // select 语句在xml里写
        // 条件语句还是在条件构造器 wrapper里写
        LambdaQueryWrapper<User> queryWrapper = getLambdaQuery()
            .like(User::getName, "雨")
            .lt(User::getAge, 40);

        // 在构造Page<T>时传入第三个参数为false
        Page<User> userPage = new Page<>(0, 2, false);

        IPage<Map<String, Object>> userIPage = userMapper.selectUserMapPage(userPage, queryWrapper);

        userIPage.getRecords().forEach(System.out::println);
    }
}
