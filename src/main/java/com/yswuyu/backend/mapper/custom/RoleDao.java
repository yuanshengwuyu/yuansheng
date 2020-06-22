
package com.yswuyu.backend.mapper.custom;

import java.util.List;

import com.yswuyu.backend.model.auto.TsysRole;

/**
 * 角色Dao
 * @ClassName: RoleDao
 * @author Hankin
 * @date 2020年8月25日
 *
 */
public interface RoleDao {
	/**
	 * 根据用户id查询角色
	 * @param userid
	 * @return
	 */
	public List<TsysRole> queryUserRole(String userid);
}
