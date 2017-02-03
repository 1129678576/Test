package com.trc.service;

import java.util.List;

import com.trc.entity.Case_bus_data;

/** 
*@author  作者 : 陈达
*@date 创建时间：2017年1月17日 上午10:56:12
*@version 1.0 
*@parameter  
*@return 
*/
public interface DataService {
	public Boolean updateData(Case_bus_data data);
	public Boolean deleteDatasByGroup(List<Integer> group_id);
	public Boolean updateKey(int method_id,String oldkey,String newkey);
}
