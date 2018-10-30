package com.wangtao.appmanager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppManagerApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private JdbcTemplate secondaryJdbcTemplate;

    @Test
    public void testPrimary() {
        List<Map<String, Object>> admins = primaryJdbcTemplate.queryForList("select * from admin");
        System.out.println("admins.size() = " + admins.size());
        Assert.assertTrue(admins.size() >= 0);
    }

    @Test
    public void testSecondary() {
        List<Map<String, Object>> dicItems = secondaryJdbcTemplate.queryForList("select * from dic_item");
        System.out.println(dicItems.size());
        Assert.assertTrue(dicItems.size() >= 0);
    }
}
