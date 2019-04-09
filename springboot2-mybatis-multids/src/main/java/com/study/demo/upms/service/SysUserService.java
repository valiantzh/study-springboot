package com.study.demo.upms.service;

import com.study.demo.upms.model.SysUser;

public interface SysUserService {
    SysUser findByUsername(String username);
    void createUser(SysUser user);
}
