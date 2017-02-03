package com.trc.entity;

import java.sql.Timestamp;

/**
 * 
 * @author Administrator
 *
 */
public class Case_bas_config {
	private int id;
	private String son;
	private int parent_id;
	private String description;
	private int creater;
	private Timestamp create_time;
	private int modifier;
	private Timestamp modify_time;
	private String son_type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSon() {
		return son;
	}
	public void setSon(String son) {
		this.son = son;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id2) {
		this.parent_id = parent_id2;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getSon_type() {
		return son_type;
	}
	public void setSon_type(String son_type) {
		this.son_type = son_type;
	}
	
}
