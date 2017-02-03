package com.trc.service;

import java.util.List;
import java.util.Map;

import com.trc.entity.Case_bus_data;
import com.trc.entity.Group;

/** 
*@author  作者 : 陈达
*@date 创建时间：2017年1月17日 上午10:55:36
*@version 1.0 
*@parameter  
*@return 
*/
public interface GroupService {
	/**
	 * 数据实体的集合
	 * @param method_id
	 * @return 所有的list实体
	 */
	public List<Case_bus_data> listGroupData(int method_id ,int group_id);
	/**
	 * 展示用的集合
	 * @param method_id
	 * @return 直接可以转化为json,对应前端的列表
	 */
	public List<Map<String,String>> listGroupDataForShow(int method_id);
	/**
	 * 新增一个空的Group
	 * @param group
	 * @return
	 */
	public Boolean addGroup(Group group);
	/**
	 * 删除一个Group及其全部数据
	 * @param group_id
	 * @return
	 */
	public Boolean deleteGroup(int group_id);
	/**
	 * 更新一个Group的group字段
	 * @param group
	 * @return
	 */
	public Boolean updateGroup(Group group);
	/**
	 * 通过method_id和group来返回一个Group的实体
	 * @param method_id
	 * @param group
	 * @return
	 */
	public Group  findGroupBymg(int method_id,int group);
	/**
	 * 批量保存一个Group的数据
	 * @param datalist
	 * @return
	 */
	public Boolean saveGroupDatas(List<Case_bus_data> datalist);
	/**
	 * 批量更新数据
	 * @param datalist
	 * @return
	 */
	public Boolean updateGroupDatas(List<Case_bus_data> datalist);
	/**
	 * 判断数据记录是否存在
	 * @param method_id
	 * @param group
	 * @return
	 */
	public Boolean ifGroupxist(int method_id,int group);
	/**
	 * 批量删除数据
	 * @param list
	 * @return
	 */
	public Boolean deleteDatas(List<Integer> list);
	/**
	 * 插入一条Group ，并用其主键插入一个List<Case_bus_data>
	 * @param group
	 * @param datalist
	 * @return
	 */
	public Boolean allAddaGroup(List<Case_bus_data> datalist);
	/**
	 * 根据method_id列出所有的Group
	 * @param method_id
	 * @return
	 */
	public List<Group> listGroup(int method_id);
	/**
	 * 批量删除groups
	 * @param list
	 * @return
	 */
	public Boolean deleteGroups(List<Integer> list);
	
}
