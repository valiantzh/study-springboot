package com.study.upms.bo;

import com.study.upms.dao.model.SysPerm;
import com.study.upms.dao.model.SysRole;
import com.study.upms.dao.model.SysUser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author valiantzh
 * @version 1.0
 */
public class SysUserBO  implements Serializable {
    private SysUser user;
    private Map<String,String> sysPermMap;
    private Map<String,String> sysRoleMap;

    public SysUserBO(SysUser user){
        this.user = user;
        this.sysPermMap = new HashMap<>();
        this.sysRoleMap = new HashMap<>();
    }

    public SysUser getUser() {
        return user;
    }


    public Map<String, String> getPerms() {
        return sysPermMap;
    }

    public Map<String, String> getRoles() {
        return sysRoleMap;
    }

    public void addSysRole(String id, String name){
        sysRoleMap.put(id, name);
    }

    public void addSysPerm(String id, String name){
        sysPermMap.put(id, name);
    }
}
