package com.trc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.trc.dao.DataConfigDao;
import com.trc.dao.DataDao;
import com.trc.dao.GroupDao;
import com.trc.entity.Case_bas_config;
import com.trc.entity.Case_bus_data;
import com.trc.entity.Group;
import com.trc.service.GroupService;

/** 
*@author  作者 : 陈达
*@date 创建时间：2017年1月17日 下午2:36:50
*@version 1.0 
*@parameter  
*@return 
*/
@Service("groupService")
public class GroupServiceImpl implements GroupService{
	@Resource
	private DataDao datadao;
	@Resource
	private GroupDao groupdao;
	@Resource
	private DataConfigDao configdao;
	@Override
	public List<Case_bus_data> listGroupData(int method_id,int group_id) {
		// TODO Auto-generated method stub
		return datadao.listData(method_id, group_id);
	}

	@Override
	public List<Map<String, String>> listGroupDataForShow(int method_id) {
		// TODO Auto-generated method stub
//		获取到method下级的所有的key
		List<Case_bas_config> list_config= configdao.listNext(method_id);
//		获取到所有的组别group		
		List<Group> list_group=groupdao.listGroup(method_id);
//		为了避免多次查询数据库，将所有的数据一次查询出来
		List<Case_bus_data> list_data=datadao.listDataByMethod(method_id);
//		创建结果List
		List<Map<String, String>> list =new ArrayList<>();
//		横竖分别对应行和列
		int shu=list_config.size();
		int heng=list_group.size();
		System.out.println("shu:"+shu+"heng:"+heng);
//		创建表头
		Map<String, String> head=new HashMap<>();
//		为表头创建前三个参数默认为 key的ID，参数，描述
		head.put("keyid", "key_id");
		head.put("key", "参数");
		head.put("description", "描述");
		
		
//		将3到往后的横，填写入group的id
		for (int j = 0; j < heng; j++) {
			head.put("heng"+j, ""+list_group.get(j).getGroup());
		}
//		将head添加到list
		list.add(head);
		
//		循环往list里填充数据
		for (int i = 0; i < shu; i++) {
//			创建临时的map:temp存储临时数据;
			Map<String, String> temp=new HashMap<>();
//			为temp填充前三个参数：key_id,参数，描述。此处与表头对应。
			temp.put("keyid", ""+list_config.get(i).getId());
			temp.put("key", list_config.get(i).getSon());
			temp.put("description",list_config.get(i).getDescription());
//			每组都要添加数据
			for (int j = 0; j < heng; j++) {
//				temp.put("heng"+3+j, data.);
//				获取第j个group的第i个key的value.....反人类
				int group_id=list_group.get(j).getId();
				String son=list_config.get(i).getSon();
//				将data的数据组装为group
				for (int k = 0; k <list_data.size(); k++) {
					if((group_id==list_data.get(k).getGroup_id())&&(son.equals(list_data.get(k).getKey()))){
						temp.put("heng"+j, list_data.get(k).getValue());
						temp.put("heng_id"+j, ""+list_data.get(k).getId());
						
					}
				}
				
			}
			list.add(temp);
		}

		return list;
	}

	@Override
	public Boolean addGroup(Group group) {
		// TODO Auto-generated method stub
		if(0!=groupdao.addGroup(group)){
			return true;
		}else{
			
			return false;
		}
	}

	@Override
	public Boolean deleteGroup(int group_id) {
		// TODO Auto-generated method stub
		if(0!=groupdao.deleteGroup(group_id)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Boolean updateGroup(Group group) {
		// TODO Auto-generated method stub
		if(0!=groupdao.updateGroup(group)){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public Group findGroupBymg(int method_id, int group) {
		// TODO Auto-generated method stub
		return groupdao.findGroupBymg(method_id, group);
	}

	@Override
	public Boolean saveGroupDatas(List<Case_bus_data> datalist) {
		// TODO Auto-generated method stub
		if(0!=datadao.addDatas(datalist)){
			return true;
		}else{
			return false;
		}

	}

	@Override
	public Boolean updateGroupDatas(List<Case_bus_data> datalist) {
		// TODO Auto-generated method stub
		if(0!=datadao.updateDatas(datalist)){
			return true;
		}else{
			return false;
		}
	}


	@Override
	public Boolean ifGroupxist(int method_id, int group) {
		// TODO Auto-generated method stub
		if(null==groupdao.findGroupBymg(method_id, group)){
			return false;
		}else{
			return true;
		}
		
	}


	@Override
	public Boolean deleteDatas(List<Integer> list) {
		// TODO Auto-generated method stub
		if(0!=groupdao.deleteGroups(list)){
			return true;
		}else{
			return false;
		}
	}


	@Override
	public Boolean allAddaGroup( List<Case_bus_data> datalist) {
		// TODO Auto-generated method stub
		if(0!=datadao.addDatas(datalist)){
			return true;
		}else{
			return false;
		}
	}


	@Override
	public List<Group> listGroup(int method_id) {
		// TODO Auto-generated method stub
		return groupdao.listGroup(method_id);
	}

	@Override
	public Boolean deleteGroups(List<Integer> list) {
		// TODO Auto-generated method stub
		if (0==list.size()) {
			return true;
		}
		if(0!=groupdao.deleteGroups(list)){
			return true;
		}else{
			return false;
		}
	}
	
}
