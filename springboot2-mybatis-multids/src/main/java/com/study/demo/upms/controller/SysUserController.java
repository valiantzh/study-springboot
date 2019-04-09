package com.study.demo.upms.controller;

import com.study.demo.upms.dao.mapper.cluster.ClusterSysUserMapper;
import com.study.demo.upms.dao.mapper.master.MasterSysUserMapper;
import com.study.demo.upms.model.SysUser;
import com.study.demo.upms.model.SysUserExample;
import com.study.demo.upms.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    
    @Autowired
    private MasterSysUserMapper masterUserMapper;
    
    @Autowired
    private ClusterSysUserMapper clusterUserMapper;

    @RequestMapping("test")
    @ResponseBody
    public SysUser test(String username){
        return sysUserService.findByUsername(username);
    }

    /************************主库控制层接口-start******************************/
    @RequestMapping("user/getAllMaster")
    public List<SysUser> getAllMaster() {
        SysUserExample example = new SysUserExample();
        return masterUserMapper.selectByExample(example);
    }

    @RequestMapping("user/getMaster")
    public SysUser getUserMaster(String userId) {
        return masterUserMapper.selectByPrimaryKey(userId);
    }

    @RequestMapping("user/insertMaster")
    public String insertMaster(SysUser user) {
        user.setCreated(new java.util.Date());
        user.setLocked(false);
        masterUserMapper.insert(user);
        // 必须使用对象获取id，否则无法获取到主键，而是获取到0（插入失败）或者1（插入成功）
        return user.getUid();
    }

    @RequestMapping("user/updateMaster")
    public Integer updateMaster(SysUser user) {
        user.setUpdated(new java.util.Date());
        return masterUserMapper.updateByPrimaryKey(user);
    }

    @RequestMapping("user/deleteMaster")
    public Integer deleteMaster(String userId) {
        return masterUserMapper.deleteByPrimaryKey(userId);
    }
    /************************主库控制层接口-end******************************/

    /************************从库控制层接口-start******************************/
    @RequestMapping("user/getAllCluster")
    public List<SysUser> getAllCluster() {
        SysUserExample example = new SysUserExample();
        return clusterUserMapper.selectByExample(example);
    }

    @RequestMapping("user/getCluster")
    public SysUser getUserCluster(String userId) {
        return clusterUserMapper.selectByPrimaryKey(userId);
    }

    @RequestMapping("user/insertCluster")
    public String insertCluster(SysUser user) {
        user.setCreated(new java.util.Date());
        user.setLocked(false);
        clusterUserMapper.insert(user);
        // 必须使用对象获取id，否则无法获取到主键，而是获取到0（插入失败）或者1（插入成功）
        return user.getUid();
    }

    @RequestMapping("user/updateCluster")
    public Integer updateCluster(SysUser user) {
        user.setUpdated(new java.util.Date());
        return clusterUserMapper.updateByPrimaryKey(user);
    }

    @RequestMapping("user/deleteCluster")
    public Integer deleteCluster(String userId) {
        return clusterUserMapper.deleteByPrimaryKey(userId);
    }
    /************************从库控制层接口-end******************************/
}
