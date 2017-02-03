package com.trc.dao;

import java.util.List;

import com.trc.entity.sysUser;
/**
 *  用户dao
 * @author Administrator
 *
 */
public interface sysUserDao {
	/**
	 * 通过ID超找用户
	 * @param id
	 * @return
	 */
	public sysUser findUserById(int id);
	/**
	 * 列出所有用户
	 * @return
	 */
	public List<sysUser> findAllUser();
	/**
	 * 通过用户名查找用户
	 * @param username
	 * @return
	 */
	public sysUser selectUserByUsername(String username);
	/**
	 * 更新用户
	 * @param sysuser
	 * @return
	 */
	public Integer updateUser(sysUser sysuser);
	/**
	 * 获取用户数量
	 * @return
	 */
	public Integer getUsercount();
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public Integer deleteUser(int id);
	/**
	 * 增加用户
	 * @param sysuser
	 * @return
	 */
	public Integer addUser(sysUser sysuser);
	/**
	 * 登录用户
	 * @param sysuser
	 * @return
	 */
	public sysUser login(sysUser sysuser);

}
