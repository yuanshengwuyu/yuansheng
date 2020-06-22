package com.yswuyu.backend.mapper.custom;

import com.yswuyu.backend.model.auto.TsysUser;

/**
 * @ClassName: TsysUserDao
 * @author Hankin
 * @date 2020年8月25日
 *
 */
public interface TsysUserDao {
	/**
	 * 根据用户名字查询用户
	 * @param username
	 * @return
	 */
	public TsysUser queryUserName(String username);
}
