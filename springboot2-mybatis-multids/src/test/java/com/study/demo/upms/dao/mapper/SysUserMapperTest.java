package com.study.demo.upms.dao.mapper;

import com.study.demo.Springboot2MybatisMultidsApplication;
import com.study.demo.upms.model.SysUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SysUserMapper.class)
@ContextConfiguration(classes = Springboot2MybatisMultidsApplication.class)
public class SysUserMapperTest {

    @Autowired
    SysUserMapper userMapper;

    @Test
    public void countByExample() {
    }

    @Test
    public void deleteByExample() {
    }

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        SysUser user = new SysUser();
        user.setUid("001");
        user.setUname("001");
        user.setPwd("123456");
        user.setSalt("001");
        user.setNick("123");
        user.setLocked(false);
        user.setCreated(new java.util.Date());
        user.setUpdated(new java.util.Date());
        Assert.assertEquals(1, userMapper.insert(user));
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByExample() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByExampleSelective() {
    }

    @Test
    public void updateByExample() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}