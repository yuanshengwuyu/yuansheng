package com.yswuyu.backend.mapper.auto;

import com.yswuyu.backend.model.auto.SysCity;
import com.yswuyu.backend.model.auto.SysCityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysCityMapper {
    long countByExample(SysCityExample example);

    int deleteByExample(SysCityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysCity record);

    int insertSelective(SysCity record);

    List<SysCity> selectByExample(SysCityExample example);

    SysCity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysCity record, @Param("example") SysCityExample example);

    int updateByExample(@Param("record") SysCity record, @Param("example") SysCityExample example);

    int updateByPrimaryKeySelective(SysCity record);

    int updateByPrimaryKey(SysCity record);
}