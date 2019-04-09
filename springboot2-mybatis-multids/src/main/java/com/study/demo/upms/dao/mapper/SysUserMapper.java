package com.study.demo.upms.dao.mapper;

import com.study.demo.annotation.DataSource;
import com.study.demo.upms.model.SysUser;
import com.study.demo.upms.model.SysUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@DataSource
public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(String uid);

    int insert(SysUser record);

    int insertSelective(SysUser record);
    @DataSource("slave1")
    List<SysUser> selectByExample(SysUserExample example);
    @DataSource("slave2")
    SysUser selectByPrimaryKey(String uid);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}