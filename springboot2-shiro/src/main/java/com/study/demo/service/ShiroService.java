package com.study.demo.service;

import com.study.demo.domain.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟用户服务
 * @author valiantzh
 * @version 1.0
 */
@Service
public class ShiroService {

    public static List<User> userList = new ArrayList<User>();

    public String getPasswordByUsername(String username){
        switch (username){
            case "zhangsan":
                return "123";
            case "lisi":
                return "456";
            default:
                return null;
        }
    }
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    public User getUserByUsername(String username) {
        System.out.println("登陆的用户 " + username);
        for (User user : userList) {
            if (username.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
    }

    /**
     * 添加用户
     *
     * @param username
     * @param password
     * @return
     */
    public User addUser(String username, String password) {
        //String newpwd = encodeByBCrypt(password);
        //密码加密
        RandomNumberGenerator saltGen = new SecureRandomNumberGenerator();
        String salt = saltGen.nextBytes().toBase64();
        String hashedPwd = new Sha256Hash(password, salt, 1024).toBase64();
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPwd);
        user.setSalt(salt);
        userList.add(user);

        System.out.println(user);
        return user;
    }
}
