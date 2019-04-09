package com.study.upms.dao.custom.mapper;




import com.study.upms.dao.model.SysPerm;
import com.study.upms.dao.model.SysRole;

import java.util.List;

/**
 * 用户VOMapper
 * @author valiantzh
 * @version 1.0
 */
public interface SysUserDao {

	// 根据用户id获取所拥有的权限
	List<SysPerm> selectSysPermByUserId(String userId);

	// 根据用户id获取所属的角色
	List<SysRole> selectSysRoleByUserId(String userId);
	
}