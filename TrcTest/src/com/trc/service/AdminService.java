package com.trc.service;

import com.trc.entity.sysUser;

/**
 * 管理员服务
 * @author Administrator
 *
 */
public interface AdminService {
	/**
	 * 改密码
	 * @param id
	 * @param passowrd
	 * @return
	 */
	public boolean updatePassword(int id,String passowrd);
	/**
	 * 删用户
	 * @param id
	 * @return
	 */
	public boolean deleteUser(int id);
	/**
	 * 更新用户权限
	 * @param user
	 * @return
	 */
	public boolean updateAuthority(sysUser user);
}
