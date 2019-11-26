package com.alanding.mp.dao;

import com.alanding.mp.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteTest extends BaseTest {

    /**
     * 根据主键 id 删除
     */
    @Test
    public void deleteById() {
        int rows = userMapper.deleteById(1198240146189877250L);

        System.out.println("影响记录数：　" + rows);
    }


    /**
     * 根据主键 id 批量删除
     */
    @Test
    public void deleteBatchIds() {
        // where id IN (1L, 2L)
        int rows = userMapper.deleteBatchIds(Arrays.asList(1L, 2L));

        System.out.println("影响记录数：　" + rows);
    }


    /**
     * 根据 传入的 map不为null 的属性作为 where 条件删除
     */
    @Test
    public void deleteByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "刘明强");
        map.put("age", 31);

        int rows = userMapper.deleteByMap(map);
        System.out.println("影响记录数：　" + rows);
    }


    /**
     * 根据 where 条件构造器删除
     */
    @Test
    public void deleteByWrapper_1() {
        LambdaQueryWrapper<User> qw = getLambdaQuery()
            .eq(User::getAge, 27)
            .or()
            .gt(User::getAge, 41);

        int rows = userMapper.delete(qw);
        System.out.println("影响记录数：　" + rows);
    }
}

