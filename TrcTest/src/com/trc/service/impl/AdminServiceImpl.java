package com.trc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.trc.dao.sysUserDao;
import com.trc.entity.sysUser;
import com.trc.service.AdminService;

/** 
*@author  作者 : 陈达
*@date 创建时间：2017年1月16日 下午2:59:11
*@version 1.0 
*@parameter  
*@return 
*/
@Service("adminService")
public class AdminServiceImpl implements AdminService{
	@Resource
	private sysUserDao userdao;

	@Override
	public boolean updatePassword(int id, String password) {
		// TODO Auto-generated method stub
		sysUser user=userdao.findUserById(id);
		user.setPassword(password);
		if(1==userdao.updateUser(user)){
			return true;
		}else {
			
			return false;
		}
	}


	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		if (1==userdao.deleteUser(id)) {
			return true;
		}else {
			
			return false;
		}
	}

	@Override
	public boolean updateAuthority(sysUser user) {
		// TODO Auto-generated method stub
		if(1==userdao.updateUser(user)){
			return true;
		}else{
			return false;
		}

	}

}
