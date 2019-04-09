package com.study.demo.controller;

import com.study.demo.base.Result;
import com.study.demo.base.ResultGenerator;
import com.study.demo.domain.User;
import com.study.demo.service.ShiroService;
import com.study.demo.vo.UserVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author valiantzh
 * @version 1.0
 */
@RestController
public class RegisterController {
    @Resource
    private ShiroService shiroService;

    @PostMapping("/register")
    public Result register(String username, String password) {

        User user = shiroService.addUser(username, password);
        UserVo userVo = new UserVo();
        userVo.setUsername(user.getUsername());
        userVo.setPassword(user.getPassword());
        return ResultGenerator.genSuccessResult(userVo).setMessage("注册成功");
    }
}
