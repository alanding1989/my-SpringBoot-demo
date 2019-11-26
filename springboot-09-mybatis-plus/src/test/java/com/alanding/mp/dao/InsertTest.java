package com.alanding.mp.dao;

import com.alanding.mp.entity.User;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertTest extends BaseTest {

    @Test
    public void insert() {
        User user = new User()
            .setName("向北")
            .setAge(26)
            .setManagerId(1088248166370832385L)
            .setCreateTime(LocalDateTime.now())
            .setRemark("我是一个备注信息哦~~");

        int rows = userMapper.insert(user);
        System.out.println("影响记录数：　" + rows);
    }

}
