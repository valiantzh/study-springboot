package com.study.demo.upms.service.impl;

import com.study.demo.Springboot2MybatisApplication;
import com.study.demo.upms.model.SysUser;
import com.study.demo.upms.service.SysUserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.ContextConfiguration;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SysUserServiceImpl.class)
@ContextConfiguration(classes = Springboot2MybatisApplication.class)
public class SysUserServiceImplTest {

    @Autowired
    SysUserService userService;
    @Test
    public void findByUsername() {
        SysUser user = userService.findByUsername("001");
        System.out.println(user.toString());
    }

    @Test
    public void createUser(){
        SysUser user = new SysUser();
        user.setUid("001");
        user.setUname("001");
        user.setPwd("123456");
        user.setSalt("001");
        user.setNick("123");
        user.setLocked(false);
        userService.createUser(user);
    }
}