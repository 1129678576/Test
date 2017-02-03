package com.trc.entity;

import java.sql.Timestamp;

/**
 * 
 * @author Administrator
 *
 */
public class Case_bus_data {
	private int id;
	private int method_id;
	private int group_id;
	private String key;
	private String value;
	private int creater;
	private Timestamp create_time;
	private int modifier;
	private Timestamp modify_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMethod_id() {
		return method_id;
	}
	public void setMethod_id(int method_id) {
		this.method_id = method_id;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getCreater() {
		return creater;
	}
	public void setCreater(int creater) {
		this.creater = creater;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public int getModifier() {
		return modifier;
	}
	public void setModifier(int modifier) {
		this.modifier = modifier;
	}
	public Timestamp getModify_time() {
		return modify_time;
	}
	public void setModify_time(Timestamp modify_time) {
		this.modify_time = modify_time;
	}
	
}
