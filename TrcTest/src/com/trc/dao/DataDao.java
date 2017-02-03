package com.trc.dao;

import java.util.List;

import com.trc.entity.Case_bus_data;

/**
 * 
 * @author Administrator
 *
 */
public interface DataDao {
	/**
	 * 列出method_id和group_id下面的所有Data
	 * @param method_id
	 * @param group_id
	 * @return
	 */
	public List<Case_bus_data> listData(int method_id,int group_id);
	/**
	 * 返回指定数据类型复杂集合
	 * @param method_id
	 * @return
	 */
	public List<Case_bus_data> listDataByMethod(int method_id);
	/**
	 * 增加一条data
	 * @param data
	 * @return
	 */
	public Integer addData(Case_bus_data data);
	/**
	 * 删除一条data
	 * @param id
	 * @return
	 */
	public Integer deleteData(int id);
	/**
	 * 更新一条data
	 * @param data
	 * @return
	 */
	public Integer updateData(Case_bus_data data);
	/**
	 * 通过mgk查找出一条data
	 * @param method_id
	 * @param group_id
	 * @param key
	 * @return
	 */
	public Integer findDataBymgk(int method_id,int group_id,String key);
	/**
	 * 批量新增数据
	 * @param list
	 * @return
	 */
	public Integer addDatas(List<Case_bus_data> list);
	/**
	 * 批量更新数据
	 * @param list
	 * @return
	 */
	public Integer updateDatas(List<Case_bus_data> list);
	/**
	 * 批量删除Data
	 * @param list
	 * @return
	 */
	public Integer deleteDatas(List<Integer> list);
	/**
	 * 通过GroupID批量删除Data
	 * @param group_id
	 * @return
	 */
	public Integer deleteDatasByGroup(List<Integer> group_id);
	/**更新data的key值
	 * @param method_id
	 * @param oldkey
	 * @param newkey
	 * @return
	 */
	public Integer updateKey(int method_id,String oldkey,String newkey);
	
	
	
	
}
