package com.study.upms.shiro;


import com.study.upms.bo.SysUserBO;
import com.study.upms.dao.model.SysUser;
import com.study.upms.service.api.SysPermService;
import com.study.upms.service.api.SysRoleService;
import com.study.upms.service.api.SysUserService;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 *
 * @author valiantzh
 * @version 1.0
 */
public class UserRealm extends AuthorizingRealm {

    private static final Logger log =LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysPermService permService;

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        //设置用于匹配密码的CredentialsMatcher
        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
        hashMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        hashMatcher.setStoredCredentialsHexEncoded(false);
        hashMatcher.setHashIterations(1024);
        super.setCredentialsMatcher(hashMatcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        SysUserBO user = (SysUserBO) getAvailablePrincipal(principals);
        Map<String,String> roles = user.getRoles();
        Map<String,String> perms = user.getPerms();
        log.info("获取角色权限信息: roles: {}, perms: {}",roles,perms);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles.keySet());//
        info.setStringPermissions(perms.keySet());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        if (username == null) {
            throw new AccountException("用户名不能为空");
        }

        SysUserBO sysUserBO = userService.getSysUserByUsername(username);
        if (sysUserBO == null) {
            throw new UnknownAccountException("找不到用户（"+username+"）的帐号信息");
        }
        SysUser sysUser = sysUserBO.getUser();

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUserBO, sysUser.getPwd(), getName());
        if (sysUser.getSalt() != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(sysUser.getSalt()));
        }

        return info;

    }





}
