package com.yswuyu.backend.mapper.custom;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yswuyu.backend.model.auto.TsysDatas;

public interface TsysDatasDao {
	
	public List<TsysDatas> selectByPrimaryKeys(@Param("ids") List<String> ids);
	
}