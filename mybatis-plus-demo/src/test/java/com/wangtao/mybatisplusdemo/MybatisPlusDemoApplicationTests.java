package com.wangtao.mybatisplusdemo;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangtao.mybatisplusdemo.common.AgeEnum;
import com.wangtao.mybatisplusdemo.mapper.UserMapper;
import com.wangtao.mybatisplusdemo.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusDemoApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void select1() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);

    }

    @Test
    public void select2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq(true, "name", "Jack");
        wrapper.like("email", "com");
        IPage<User> iPage = userMapper.selectPage(new Page<>(0, 10), wrapper);
        List<User> records = iPage.getRecords();
        records.forEach(s -> System.out.println(JSON.toJSONString(s)));
    }

    @Test
    public void update1() {

        User user = userMapper.selectById(1070909710207012866L);
        user.setEmail("333111@QQQ.CCC" + ((int) Math.random() * 100));
        int i = userMapper.updateById(user);
        System.out.println("i = " + i);
    }

    @Test
    public void insert1() {
        User user = new User();
        user.setName("TestInsert26");
        user.setAge(AgeEnum.TWENTY_SIX);
        user.setEmail("123@qq.com");


        int insert = userMapper.insert(user);
        System.out.println("insert = " + insert);
    }

    @Test
    public void del1() {

        int del = userMapper.deleteById(1070877006321057793L);
        System.out.println("del = " + del);

    }
}
