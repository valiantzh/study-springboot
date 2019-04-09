package com.study.demo.upms.dao.mapper;

import com.study.demo.upms.model.SysRolePerm;
import com.study.demo.upms.model.SysRolePermExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRolePermMapper {
    int countByExample(SysRolePermExample example);

    int deleteByExample(SysRolePermExample example);

    int deleteByPrimaryKey(@Param("roleId") String roleId, @Param("permVal") String permVal);

    int insert(SysRolePerm record);

    int insertSelective(SysRolePerm record);

    List<SysRolePerm> selectByExample(SysRolePermExample example);

    SysRolePerm selectByPrimaryKey(@Param("roleId") String roleId, @Param("permVal") String permVal);

    int updateByExampleSelective(@Param("record") SysRolePerm record, @Param("example") SysRolePermExample example);

    int updateByExample(@Param("record") SysRolePerm record, @Param("example") SysRolePermExample example);

    int updateByPrimaryKeySelective(SysRolePerm record);

    int updateByPrimaryKey(SysRolePerm record);
}