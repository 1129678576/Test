package com.trc.dao;
/**
 * 
 * @author Administrator
 *
 */
public interface AdminDao {
	public Integer setAuthority(int userid ,int add,int modify,int delete );
	public Integer modifyPassword(int userid,int password);
}
