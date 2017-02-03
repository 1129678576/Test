package com.trc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.trc.dao.DataConfigDao;
import com.trc.dao.DataDao;
import com.trc.dao.GroupDao;
import com.trc.entity.Case_bas_config;
import com.trc.entity.Case_bus_data;
import com.trc.entity.Group;
import com.trc.service.DataConfigService;

/**
 * 
 * @author Administrator
 *
 */
@Service("dataconfigService")
public class DataConfigServiceImpl implements DataConfigService{
	@Resource
	private DataConfigDao configDao;
	@Resource
	private GroupDao groupdao;
	@Resource
	private  DataDao datadao;
	@Override
	public boolean saveDataConfig(Case_bas_config config) {
		// TODO Auto-generated method stub
		if (1==configDao.addConfig(config)) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 修改数据配置项
	 */
	@Override
	public boolean updateDataConfig(Case_bas_config config) {
		// TODO Auto-generated method stub

		if(1==configDao.updateConfig(config)){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 删除数据配置项
	 */
	@Override
	public boolean deleteDataConfig(int id) {
		// TODO Auto-generated method stub
			if(0!=configDao.deleteConfig(id)){
				return true;
			}else {
				return false;
			}
	}
	/**
	 *列出公司
	 */
	@Override
	public List<Case_bas_config> listCompany() {
		// TODO Auto-generated method stub
		return configDao.listCompany();
	}
	/**
	 * 列出下一级
	 */
	@Override
	public List<Case_bas_config> listNext(int parent_id) {
		// TODO Auto-generated method stub
		return configDao.listNext(parent_id);
	}
	/**
	 * 检查配置数据是否存在
	 */
	@Override
	public boolean ifConfigExist(int id) {
		if(null==configDao.findConfigById(id)){
			return false;
		}else {
			return true;
		}
		
	}
	/**
	 * 检查配置的是不是key
	 */
	@Override
	public boolean ifConfigisKey(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see com.trc.service.DataConfigService#ifSonExist(int, java.lang.String)
	 */
	@Override
	public boolean ifSonExist(int id, String son) {
		if(null==configDao.findConfigByIdSon(id, son)){
			return false;
		}else {
			return true;
		}
	}
	/* (non-Javadoc)
	 * @see com.trc.service.DataConfigService#ifHaveNext(int)
	 */
	@Override
	public boolean ifHaveNext(int id) {
		if(0==configDao.listNext(id).size()){
			return false;
		}else {
			return true;
		}
	}

	@Override
	public Case_bas_config findConfigById(int id) {
		// TODO Auto-generated method stub
		return configDao.findConfigById(id);
	}
	
	@Override
	public Boolean deleteConfigByParent(int parent_id) {
		// TODO Auto-generated method stub
		if(0!=configDao.deleteConfigByParent(parent_id)){
			return true;
		}else {
			return false;
		}
	
	}

	@Override
	public boolean deletemethod(int method_id) {
		// TODO Auto-generated method stub
//		 查出method下的所有Group的id.
		 List<Group> group_list=groupdao.listGroup(method_id);
//		 取出所有group的id
		 List<Integer> group_id=new ArrayList<>();
		 for (Group g:group_list) {
			 group_id.add(g.getId());
		}
//		 批量删除所有group_id在list中的data
		datadao.deleteDatasByGroup(group_id);
//		 删除所有的group记录
		groupdao.deleteGroups(group_id);
		configDao.deleteConfigByParent(method_id);
		Boolean bool_method=deleteDataConfig(method_id);
		if(bool_method){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean deletekey(int key_id) {
		// TODO Auto-generated method stub
//		查出父级method的id;
		 int method_id=findConfigById(key_id).getParent_id();
//		 查出method下的所有Group的id.
		 List<Group> group_list=groupdao.listGroup(method_id);
//		 取出所有group的id
		 List<Integer> group_id=new ArrayList<>();
		 for (Group g:group_list) {
			 group_id.add(g.getId());
		}
//		 批量删除所有group_id在list中的data
		 datadao.deleteDatasByGroup(group_id);
//		 删除所有的group记录
		groupdao.deleteGroups(group_id);
		Boolean bool_key=deleteDataConfig(key_id);

		return bool_key;
	}

	@Override
	public Boolean updateKey(Case_bas_config config, String oldkey) {
		// TODO Auto-generated method stub
		datadao.updateKey(config.getParent_id(), oldkey, config.getSon());
		Boolean updateconfig=updateDataConfig(config);
		if(updateconfig){
			return true;
		}else{
			return false;
		}
		
	
	}

	@Override
	public Boolean addKey(Case_bas_config config) {
		// TODO Auto-generated method stub
		List<Group> groups=groupdao.listGroup(config.getParent_id());
		
		if(0==groups.size()){
			 return true;

		}else{
			List <Case_bus_data> list_data=new ArrayList<>();
		
			for (Group g:groups) {
				Case_bus_data data=new Case_bus_data();
				data.setMethod_id(config.getParent_id());
				data.setGroup_id(g.getId());
				data.setKey(config.getSon());
				data.setValue("");
				data.setCreater(150);
				list_data.add(data);
				
			}
//			判断插入情况，
			if (0==datadao.addDatas(list_data)) {
				return false;
			}else{
				return true;
				}
			}
		
	}
}
