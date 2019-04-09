package com.study.demo.upms.dao.mapper.master;

import com.study.demo.annotation.DataSource;
import com.study.demo.upms.model.SysUser;
import com.study.demo.upms.model.SysUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MasterSysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(String uid);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(String uid);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}