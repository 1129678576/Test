package com.trc.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.trc.dao.sysUserDao;
import com.trc.entity.sysUser;
import com.trc.service.UserService;
/**
 * 
 * @author Administrator
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private sysUserDao userdao;

	@Override
	public boolean saveUser(sysUser sysuser) {
		if(1==userdao.addUser(sysuser)){
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public boolean ifUsernameExist(String username) {
		// TODO Auto-generated method stub
		sysUser sysuser=userdao.selectUserByUsername(username);
		if(sysuser!=null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public sysUser login(sysUser sysuser) {
		// TODO Auto-generated method stub
		return userdao .login(sysuser);
	}
	@Override
	public List<sysUser> findAllUser() {
		// TODO Auto-generated method stub
		return userdao.findAllUser();
	}

	@Override
	public sysUser findUserById(int id) {
		return userdao.findUserById(id);
	}

	@Override
	public sysUser findUserByUsrname(String username) {
		// TODO Auto-generated method stub
		return userdao.selectUserByUsername(username);
	}

	@Override
	public Integer updateUser(sysUser sysuser) {
		// TODO Auto-generated method stub
		return userdao.updateUser(sysuser);
	}

	@Override
	public boolean ifCanLogin(String username, String password, sysUser sysuser) {
		// TODO Auto-generated method stub
		if (username.equals(sysuser.getUsername())&&password.equals(sysuser.getPassword())){
			return true;
		}else {
			return false;
		}
	}





}
