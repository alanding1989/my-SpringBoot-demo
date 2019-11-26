package com.alanding.mp.dao;


import com.alanding.mp.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *  @Author      :  AlanDing
 *  @Time        :  2019/11/23 下午10:07
 *  @File        :  RetrieveTest.java
 *  @Description :
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RetrieveTest extends BaseTest {

    /** ===============================================================================
     * 简单查询，返回表中 `全部字段`，下面有返回指定字段的例子
     * ============================================================================= */

    // 查询全部表记录，不分页
    @Test
    public void select() {
        List<User> users = userMapper.selectList(null);
        Assert.assertEquals(6, users.size());
        users.forEach(System.out::println);
    }


    // 根据 ID 查询，不分页
    @Test
    public void selectById() {
        User user = userMapper.selectById(1094590409767661570L);
        System.out.println(user);
    }


    // 通过多个主键查询 （根据ID 批量查询），不分页
    @Test
    public void selectIds() {
        List<Long> ids = Arrays.asList(1088248166370832385L, 1094592041087729666L,
                                       1198231209436745729L);
        List<User> users = userMapper.selectBatchIds(ids);
        users.forEach(System.out::println);
    }


    /**
     * 通过列的Map对象查询，就是根据多个表字段查询，不分页
     * where name="王天风" and age=30
     */
    @Test
    public void selectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "王天风");
        map.put("age", 25);

        // map.put("age", 31);
        // 当只有一个条件时，可能会选出多个满足条件的表记录，
        // 所以返回值类型也是 List
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);


        // 等同于上面的查询，但底层原理不同
        // QueryWrapper<User> querywrapper = getQueryWrapper().allEq(map);
        //
        // userMapper.selectList(querywrapper)
        //           .forEach(System.out::println);
    }


    /**
     * 查询满足条件的总记录数，自己编码实现的，扩展性不一定好，selectCount_2()下面有直接的 api调用。
     */
    @Test
    public void selectCount_1() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "王天风");
        map.put("age", 25);

        System.out.println("总记录数 = " + userMapper.selectByMap(map).size());
    }


    /** ================================================================================
     * 条件查询基本用法，返回表中 `全部字段`，下面有返回指定字段的例子
     * 根据条件构造器 QueryWrapper 构造 where 子句进行查询
     * =============================================================================== */

    /**
     * １、名字中包含｀雨｀，并且年龄小于40
     *    name like '%雨%' and age < 40
     */
    @Test
    public void selectByWrapper1() {
        QueryWrapper<User> queryWrapper = getQueryWrapper()
            .like("name", "雨")
            .lt("age", 40);

        userMapper.selectList(queryWrapper)
                  .forEach(System.out::println);
    }


    /**
     * ２、名字中包含｀雨｀，并且年龄大于等于20且小于等于40，并且email不为空
     *    name Like '%雨%' And age Between 20 And 40 And email is not NULL
     */
    @Test
    public void selectByWrapper2() {
        QueryWrapper<User> queryWrapper = getQueryWrapper()
            .like("name", "雨")
            // between包括左右边界，注意字段若是DataTime，会查不到超过00:00:00的值，少查一天
            .between("age", 20, 40)
            .isNotNull("email");

        userMapper.selectList(queryWrapper)
                  .forEach(System.out::println);
    }


    /**
     * 3、名字为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
     *    name like '王%' or age >=25 order by age desc,id asc
     *
     *    注意`王`要开头，因此要用likeRight()方法，模糊匹配百分号在右边
     */
    @Test
    public void selectByWrapper3() {
        QueryWrapper<User> queryWrapper = getQueryWrapper()
            .likeRight("name", "王")
            .or()
            .ge("age", 25)
            .orderByDesc("age")
            .orderByAsc("id");

        userMapper.selectList(queryWrapper)
                  .forEach(System.out::println);
    }


    /**
     * sql 子查询
     * 4、创建日期为2019年2月14日并且直属上级名字为王姓
     *    date_format(create_time,'%Y-%m-%d') = '2019-02-14'
     *    and
     *    manager_id in (select id from user where name like '王%')
     */
    @Test
    public void selectByWrapper4() {
        QueryWrapper<User> queryWrapper = getQueryWrapper()
            // apply()用来拼接sql，用参数占位符{0}没有sql注入风险
            .apply("date_format(create_time, '%Y-%m-%d') = {0}", "2019-02-14")
            // 不用参数占位符{0}会有sql注入风险
            // .apply("date_format(create_time, '%Y-%m-%d') = '2019-02-14'")
            // 子查询用inSql()方法
            .inSql("manager_id", "select id from user where name like '王%'");

        userMapper.selectList(queryWrapper)
                  .forEach(System.out::println);
    }


    /**
     * 嵌套 and 查询
     * 5、名字为王姓并且（年龄小于40或邮箱不为空）
     *    name like '王%' and (age<40 or email is not null)
     */
    @Test
    public void selectByWrapper5() {
        QueryWrapper<User> queryWrapper = getQueryWrapper()
            .likeRight("name", "王")
            .and(qw -> qw.lt("age", 40)
                         .or()
                         .isNotNull("email"));

        userMapper.selectList(queryWrapper)
                  .forEach(System.out::println);
    }


    /**
     * 6、名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
     *    name like '王%' or (age<40 and age>20 and email is not null)
     */
    @Test
    public void selectByWrapper6() {
        QueryWrapper<User> queryWrapper = getQueryWrapper()
            .likeRight("name", "王")
            .or(qw -> qw.gt("age", 20)
                        .lt("age", 40)
                        .isNotNull("email"));

        userMapper.selectList(queryWrapper)
                  .forEach(System.out::println);
    }


    /**
     * 7、（年龄小于40或邮箱不为空）并且名字为王姓
     *     (age<40 or email is not null) and name like '王%'
     */
    @Test
    public void selectByWrapper7() {
        QueryWrapper<User> queryWrapper = getQueryWrapper()
            .nested(qw -> qw.lt("age", 40)
                            .isNotNull("email"))
            .likeRight("name", "王");

        // 等价于下面的这个查询，只不过把嵌套的放在了前面
        //     .likeRight("name", "王")
        //     .and(qw -> qw.lt("name", 40)
        //                  .isNotNull("email"));

        userMapper.selectList(queryWrapper)
                  .forEach(System.out::println);
    }


    /**
     * 8、年龄为30、31、34、35
     *    age in (30、31、34、35)
     */
    @Test
    public void selectByWrapper8() {
        QueryWrapper<User> querywrapper = getQueryWrapper()
            .in("age", Arrays.asList(30, 31, 34, 35));

        userMapper.selectList(querywrapper)
                  .forEach(System.out::println);
    }


    /**
     * 9、只返回满足条件的其中一条语句即可
     *    limit 1
     */
    @Test
    public void selectByWrapper9() {
        QueryWrapper<User> querywrapper = getQueryWrapper()
            .in("age", Arrays.asList(30, 31, 34, 35))
            // 无视优化规则直接拼接到 sql 的最后
            // 注意事项: 只能调用一次，多次调用以最后一次为准。
            // 有sql注入的风险，谨慎使用。
            .last("limit 1");

        userMapper.selectList(querywrapper)
                  .forEach(System.out::println);
    }


    /** ===============================================================================
     * 应用场景：　返回满足条件的实体类(表记录)。
     *    条件查询，select()`指定的字段`会有值，未指定的字段在实体类中都为 null。
     *    这样不会把满足条件的表记录的所有字段都查出来赋给实体类属性。
     *
     *    下面有未指定字段不返回 null 的例子。
     * 调用 selectList()，根据条件构造器 QueryWrapper 构造 where 子句进行查询
     ================================================================================*/

    /**
     * 10、名字中包含雨并且年龄小于40 (需求1加强版)
     * 第一种情况：select id,name
     * 	           from user
     * 	           where name like '%雨%' and age < 40
     */
    @Test
    public void selectByWrapper10_1() {
        QueryWrapper<User> querywrapper = getQueryWrapper()
            // 用select()来过滤字段
            .select("id", "name")
            .like("name", "雨")
            .lt("age", 40);

        userMapper.selectList(querywrapper)
                  .forEach(System.out::println);
    }


    /**
     * 10、名字中包含雨并且年龄小于40(需求1加强版)
     * 第二种情况：select id,name,age,email
     * 	           from user
     * 	           where name like '%雨%' and age<40
     *      表中字段多的话一个一个写到select很麻烦，可以用 Predicate 排除
     */
    @Test
    public void selectByWrapper10_2() {
        QueryWrapper<User> querywrapper = getQueryWrapper()
            // 用select()来过滤字段
            .select(User.class, info -> !info.getColumn().equals("create_time")
                && !info.getColumn().equals("manager_id"))
            .like("name", "雨")
            .lt("age", 40);

        // 等价于上面的查询
        // .select("id", "name", "age", "email")
        // .like("name", "雨")
        // .lt("age", 40);

        userMapper.selectList(querywrapper)
                  .forEach(System.out::println);
    }


    /** ================================================================================
     * 以上like()，likeRight()，lt()，gt()，le()，ge() 等方法中第一个参数 condition 的作用，通过一个列子来看。
     *
     * １、如果前端传的入参中有个属性值为null，或者是空字符串，那么都需要在Service中进行空值检查很麻烦。
     *    就像下面 condition1() 中用 StringUtils 判断一样。
     *
     * ２、如果 condition 为 false，就不会将该字段的查询语句加入到 where 子句中。
     *    可以直接传入一个判断语句来对相关值进行判断，
     *    就像下面 condition2() 中一样。
     *
     * ３、跟这个 mapper.xml 中 `if test=...` 作用一样。
     *  <!--高级查询条件-->
     *   <sql id="where_sql">
     *     <where>
     *       <if test="name != null">
     *         (
     *         user.name like concat('%', #{keyword}, '%')
     *         )
     *       </if>
     *     </where>
     *   </sql>
     ============================================================================= */
    @Test
    public void testCondition() {
        String name = "王";
        String email = "";

        condition0(name, email);
        System.out.println("\n\n");

        condition1(name, email);
        System.out.println("\n\n");

        condition2(name, email);
    }

    // 原始状态，啥都不加
    public void condition0(String name, String email) {
        QueryWrapper<User> querywrapper = getQueryWrapper()
            .select("id", "name", "email")
            .like("name", name)
            .like("email", email);

        userMapper.selectList(querywrapper)
                  .forEach(System.out::println);
    }

    // 自己在Service中进行空值判断
    public void condition1(String name, String email) {
        QueryWrapper<User> querywrapper = getQueryWrapper()
            .select("id", "name", "email");

        if (StringUtils.isNotEmpty(name)) {
            querywrapper.like("name", name);
        }
        if (StringUtils.isNotEmpty(email)) {
            querywrapper.like("email", email);
        }

        userMapper.selectList(querywrapper)
                  .forEach(System.out::println);
    }

    // 直接在like()中语句判断入参是否为空，来决定是否生成对应的 where查询子句
    public void condition2(String name, String email) {
        QueryWrapper<User> querywrapper = getQueryWrapper()
            .select("id", "name", "email")
            .like(StringUtils.isNotEmpty(name), "name", name)
            .like(StringUtils.isNotEmpty(email), "email", email);

        userMapper.selectList(querywrapper)
                  .forEach(System.out::println);
    }


    /** ==============================================================================
     * 应用场景：　返回满足条件的多个字段。
     *    注意不是返回 `实体类`，这样只会返回指定的字段，而不像返回实体类时未指定的字段全是 null。
     *    条件查询，返回表中 `指定字段`，包装成一个 Map返回。
     *
     * 调用 selectMaps()，根据条件构造器 QueryWrapper 构造 where 子句进行查询
     ================================================================================*/

    /**
     * 用 selectMaps()进行查询，返回 Map 而不是实体类。
     */
    @Test
    public void selectByWrapperMaps_1() {
        QueryWrapper<User> queryWrapper = getQueryWrapper()
            // select() 指定返回的字段
            .select("name", "age")
            .like("name", "雨")
            .lt("age", 40);

        // 注意 返回值类型
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }


    /**
     * 统计查询：
     * 11、按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄。
     *     并且只取年龄总和小于500的组。
     *     select avg(age) avg_age, min(age) min_age, max(age) max_age
     *     from user
     *     group by manager_id
     *     having sum(age) <500
     */
    @Test
    public void selectByWrapperMaps_2() {
        QueryWrapper<User> queryWrapper = getQueryWrapper()
            // select() 指定返回的字段
            .select("avg(age) avg_age", "min(age) min_age", "max(age) max_age")
            .groupBy("manager_id")
            .having("sum(age) < {0}", 500);

        // 注意 返回值类型
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }


    /** ==============================================================================
     * 应用场景：　查询满足条件的 `一个` 表字段，例如人名。
     *
     * 调用 selectObjs()，返回 Object 而不是实体类，注意只返回第一列。
     * 根据条件构造器 QueryWrapper 构造 where 子句进行查询
     ================================================================================*/
    @Test
    public void selectByWrapperObjs() {
        QueryWrapper<User> queryWrapper = getQueryWrapper()
            // select() 指定返回的字段
            .select("name")
            .like("name", "雨")
            .lt("age", 40);

        // 注意 返回值类型
        List<Object> objs = userMapper.selectObjs(queryWrapper);
        objs.forEach(System.out::println);
    }


    /** ================================================================================
     * 应用场景：
     * 有的公司允许 controller 用实体类或者Map作为入参，这时候就不需要用QueryWrapper 调用 api函数查询了。
     *
     * 构造 QueryWrapper 时传入一个实体类对象，实体类对象中不为 null 的属性
     * 将作为 sql语句的 where 条件，默认使用等值比较来进行查询。
     * =============================================================================*/
    @Test
    public void selectByWrapperEntity() {
        // 实体类设置的值将作为 where 查询语句的条件
        User user = new User().setName("刘红雨").setAge(32);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user)
            .select("name", "age");

        // 注意：用构造器传入的和用 api 函数传入的条件是互不干扰的，注意不要设置重了
        // queryWrapper.like("name", "雨")
        //             .lt("age", 40);

        userMapper.selectList(queryWrapper)
                  .forEach(System.out::println);

        userMapper.selectMaps(queryWrapper)
                  .forEach(System.out::println);
    }


    /**
     * 构造 QueryWrapper 时传入一个 Map 对象，
     * key 为数据库字段名，value　为字段值
     * null2isnull： 若字段为 null，设置了该参数为false时，where查询就会忽略掉该字段
     */
    @Test
    public void selectByWrapperAllEq() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "王天风");
        params.put("age", 25);

        QueryWrapper<User> queryWrapper = getQueryWrapper()
            // 对第一个参数进行字段过滤
            // .allEq((k, v) -> !k.equals("name"), params);
            // 空值检测
            .allEq((k, v) -> {
                if (v == null) {
                    return false;
                }
                return !(v instanceof CharSequence)
                    || StringUtils.isNotEmpty((CharSequence) v);
            }, params);

        userMapper.selectList(queryWrapper)
                  .forEach(System.out::println);
    }


    /** ================================================================================
     * 查询满足条件的总记录数
     * ===============================================================================*/
    @Test
    public void selectCount_02() {
        QueryWrapper<User> querywrapper = getQueryWrapper()
            .like("name", "雨")
            .lt("age", 40);

        System.out.println("满足条件的记录数 = " + userMapper.selectCount(querywrapper));
    }


    /** ================================================================================
     * 只查询一个表记录
     * ===============================================================================*/
    @Test
    public void selectOne() {
        QueryWrapper<User> querywrapper = getQueryWrapper()
            .like("name", "刘红雨")
            .lt("age", 40);

        User user = userMapper.selectOne(querywrapper);
        System.out.println(user);
    }


    /** ================================================================================
     * 应用场景：
     * 因为使用实体类方法引用来作为字段参数，所以可以防误写。
     * 根据条件构造器 LambdaQueryWrapper 构造 where 子句进行查询
     * =============================================================================*/
    @Test
    public void selectLambda() {
        LambdaQueryWrapper<User> querywrapper = getLambdaQuery()
            .like(User::getName, "雨")
            .lt(User::getAge, 40);

        List<User> user = userMapper.selectList(querywrapper);
        System.out.println(user);
    }

    /**
     * 嵌套 and 查询
     * 5、名字为王姓并且（年龄小于40或邮箱不为空）
     *    name like '王%' and (age<40 or email is not null)
     */
    @Test
    public void selectLambda2() {
        LambdaQueryWrapper<User> queryWrapper = getLambdaQuery()
            .select(User::getName, User::getAge)
            .likeRight(User::getName, "王")
            .and(qw -> qw.lt(User::getAge, 40)
                         .or()
                         .isNotNull(User::getEmail));

        userMapper.selectMaps(queryWrapper)
                  .forEach(System.out::println);
    }


    /**
     * 直接链式调用，只能返回实体类对象，不能返回 map
     */
    @Test
    public void selectLambda3() {
        getLambdaQueryChain()
            .like(User::getName, "雨")
            .ge(User::getAge, 20)
            .list()
            .forEach(System.out::println);
    }


    /**
     * mapper 接口中 自定义 sql 查询语句
     */
    @Test
    public void selecAll() {
        LambdaQueryWrapper<User> querywrapper = getLambdaQuery()
            .like(User::getName, "雨")
            .lt(User::getAge, 40);

        List<User> user = userMapper.mySelectAll(querywrapper);
        System.out.println(user);
    }
}

