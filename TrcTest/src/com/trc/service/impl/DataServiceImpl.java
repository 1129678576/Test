package com.trc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.trc.dao.DataDao;
import com.trc.entity.Case_bus_data;
import com.trc.service.DataService;

/** 
*@author  作者 : 陈达
*@date 创建时间：2017年1月19日 上午10:56:10
*@version 1.0 
*@parameter  
*@return 
*/
@Service("dataService")
public class DataServiceImpl implements DataService{

	@Resource
	private DataDao datadao;
	@Override
	public Boolean updateData(Case_bus_data data) {
		// TODO Auto-generated method stub
		if(1==datadao.updateData(data)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Boolean deleteDatasByGroup(List<Integer> group_id) {
		// TODO Auto-generated method stub
		if (0==group_id.size()) {
			return true;
		}
		
		if(0!=datadao.deleteDatasByGroup(group_id)){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public Boolean updateKey(int method_id, String oldkey, String newkey) {
		// TODO Auto-generated method stub
		if(1==datadao.updateKey(method_id, oldkey, newkey)){
			return true;
		}else{
			return false;
		}
	}
	
	
	
}
