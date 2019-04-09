package com.study.demo.upms.service.impl;

import com.study.demo.upms.dao.mapper.SysUserMapper;
import com.study.demo.upms.model.SysUser;
import com.study.demo.upms.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.selectByPrimaryKey(username);
    }

    @Override
    public void createUser(SysUser user) {
        user.setCreated(new java.util.Date());
        sysUserMapper.insert(user);
    }

}
