package com.study.upms.service.impl;

import com.study.upms.Springboot2UpmsCoreApplication;
import com.study.upms.bo.SysUserBO;
import com.study.upms.dao.model.SysPerm;
import com.study.upms.dao.model.SysRole;
import com.study.upms.dao.model.SysUser;
import com.study.upms.dao.model.SysUserExample;
import com.study.upms.service.api.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SysUserServiceImpl.class)
@ContextConfiguration(classes = Springboot2UpmsCoreApplication.class)
public class SysUserServiceImplTest {

    private static final Logger LOGGER= LoggerFactory.getLogger(SysUserServiceImplTest.class);
    @Autowired
    SysUserService sysUserService;

    @Test
    public void list(){
        String sort="";
        String order="";
        String search = "关";
        int offset = 0;
        int limit = 5;

        SysUserExample sysUserExample = new SysUserExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            sysUserExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            sysUserExample.or().
                    andNickLike("%" + search + "%");
            sysUserExample.or().
                    andUnameLike("%" + search + "%");
        }

        List<SysUser> rows = sysUserService.selectByExampleForOffsetPage(sysUserExample, offset, limit);
        if(rows != null){
            for(SysUser sysUser: rows){
                LOGGER.info("list:{}",sysUser.toString());
            }
        }


    }

    @Test
    public void listSysPermByUserId() {
        String userId = "1002748017179541505";
        List<SysPerm> sysPermList = sysUserService.listSysPermByUserId(userId);
        //LOGGER.info("UserId={},Perm={}",userId,sysPermList.toString());
        if(sysPermList!=null){
            for(SysPerm sysPerm: sysPermList){
                LOGGER.info("UserId={},Perm={}",userId,sysPerm.toString());
            }
        }
    }


    @Test
    public void listSysRoleByUserId() {
        String userId = "1002748017179541505";
        List<SysRole> sysRoleList = sysUserService.listSysRoleByUserId(userId);

        LOGGER.info("UserId={},Role={}",userId,sysRoleList.toString());
        if(sysRoleList != null){
            for(SysRole sysRole: sysRoleList){
                LOGGER.info("UserId={},Role={}",userId,sysRole.toString());
            }
        }
    }
    @Test
    public void getSysUserByUsername() {
        String username = "guanyu";

        SysUserBO sysUserBO = sysUserService.getSysUserByUsername(username);
        //LOGGER.info("UserId={},Perm={}",userId,sysPermList.toString());
        if(sysUserBO!=null){
            LOGGER.info("username={},sysUser={}",username, sysUserBO.getUser().toString());

            LOGGER.info("username={},Perm={}",username,sysUserBO.getPerms());
            LOGGER.info("username={},Perm.keyset={}",username,sysUserBO.getPerms().keySet());
            LOGGER.info("username={},Perm.values={}",username,sysUserBO.getPerms().values());

            LOGGER.info("username={},Role={}",username,sysUserBO.getRoles());
            LOGGER.info("username={},Role.keyset={}",username,sysUserBO.getRoles().keySet());
            LOGGER.info("username={},Role.values={}",username,sysUserBO.getRoles().values());

        }
    }
}