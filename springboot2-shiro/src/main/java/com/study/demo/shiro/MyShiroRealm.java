package com.study.demo.shiro;

import com.study.demo.domain.User;
import com.study.demo.service.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author valiantzh
 * @version 1.0
 */
public class MyShiroRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Resource
    public ShiroService shiroService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    /**
     * 登录认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        if (username == null) {
            throw new AccountException("用户名不能为空");
        }

        //获取用户账号
        //String username = token.getPrincipal().toString();

        //String password = shiroService.getPasswordByUsername(username);
        User user = shiroService.getUserByUsername(username);
        if (user != null) {
            ByteSource credentialsSalt = ByteSource.Util.bytes(username);
            /**
             * 四个参数
             * principal：认证的实体信息，可以是username，也可以是数据库表对应的用户的实体对象
             * credentials：数据库中的密码（经过加密的密码）
             * credentialsSalt：盐值（使用用户名）
             * realmName：当前realm对象的name，调用父类的getName()方法即可
             */
            AuthenticationInfo info = new SimpleAuthenticationInfo(
                    username,   //认证通过后，存放在session,一般存放user对象
                    user.getPassword(),   //用户数据库中的密码
                    credentialsSalt,
                    getName());    //当前realm对象的name，调用父类的getName()方法即可
            return info;
        }
        return null;
    }
}
