package com.trc.service;

import java.util.List;

import com.trc.entity.sysUser;
/**
 * 
 * @author Administrator
 *
 */
public interface UserService {
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	public boolean saveUser(sysUser user);
	/**
	 * 通过用户名检查用户是否存在
	 * @param username
	 * @return
	 */
	public boolean ifUsernameExist(String username);
	/**
	 * 登录
	 * @param sysuser
	 * @return
	 */
	public sysUser login(sysUser sysuser);
	/**
	 * 列出所有的user
	 * @return
	 */
	public List<sysUser> findAllUser();
	/**
	 * 通过ID查找用户
	 * @param id
	 * @return
	 */
	public sysUser findUserById(int id);
	/**
	 * 通过用户名寻找用户
	 * @param username
	 * @return
	 */
	public sysUser findUserByUsrname(String username);
	/**
	 * 更新用户
	 * @param sysuser
	 * @return
	 */
	public Integer updateUser(sysUser sysuser);
	/**
	 * 是否可以登录
	 * @param username
	 * @param password
	 * @param sysuser
	 * @return
	 */
	public boolean ifCanLogin(String username,String password,sysUser sysuser);

	
}
