package com.study.demo.upms.controller;

import com.study.demo.upms.model.SysUser;
import com.study.demo.upms.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("test")
    @ResponseBody
    public SysUser test(String username){
        return sysUserService.findByUsername(username);
    }
}
