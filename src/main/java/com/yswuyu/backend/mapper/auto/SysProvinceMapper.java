package com.yswuyu.backend.mapper.auto;

import com.yswuyu.backend.model.auto.SysProvince;
import com.yswuyu.backend.model.auto.SysProvinceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysProvinceMapper {
    long countByExample(SysProvinceExample example);

    int deleteByExample(SysProvinceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysProvince record);

    int insertSelective(SysProvince record);

    List<SysProvince> selectByExample(SysProvinceExample example);

    SysProvince selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysProvince record, @Param("example") SysProvinceExample example);

    int updateByExample(@Param("record") SysProvince record, @Param("example") SysProvinceExample example);

    int updateByPrimaryKeySelective(SysProvince record);

    int updateByPrimaryKey(SysProvince record);
}