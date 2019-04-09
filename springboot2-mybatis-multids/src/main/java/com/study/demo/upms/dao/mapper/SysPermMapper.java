package com.study.demo.upms.dao.mapper;

import com.study.demo.upms.model.SysPerm;
import com.study.demo.upms.model.SysPermExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermMapper {
    int countByExample(SysPermExample example);

    int deleteByExample(SysPermExample example);

    int deleteByPrimaryKey(String pval);

    int insert(SysPerm record);

    int insertSelective(SysPerm record);

    List<SysPerm> selectByExample(SysPermExample example);

    SysPerm selectByPrimaryKey(String pval);

    int updateByExampleSelective(@Param("record") SysPerm record, @Param("example") SysPermExample example);

    int updateByExample(@Param("record") SysPerm record, @Param("example") SysPermExample example);

    int updateByPrimaryKeySelective(SysPerm record);

    int updateByPrimaryKey(SysPerm record);
}