package com.trc.entity;
/**
 * 
 * @author Administrator
 *
 */
public class sysUser {
	private int id;
	private String username;
	private String password;
	private String nickname;
	private String face;
	private int add_acconut;
	private int modify_account;
	private int delete_account;
	private	int user_type;
	public int getAdd_acconut() {
		return add_acconut;
	}
	public void setAdd_acconut(int add_acconut) {
		this.add_acconut = add_acconut;
	}
	public int getModify_account() {
		return modify_account;
	}
	public void setModify_account(int modify_account) {
		this.modify_account = modify_account;
	}
	public int getDelete_account() {
		return delete_account;
	}
	public void setDelete_account(int delete_account) {
		this.delete_account = delete_account;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	@Override
	public String toString() {
		return "User [userId=" + id + ", userName=" + username
				+ ", password=" + password + ", nickname=" + nickname +  ", face=" + face +"]";
	}
	
	
	}
