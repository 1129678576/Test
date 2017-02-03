package com.trc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trc.entity.Case_bas_config;
import com.trc.entity.Case_bus_data;
import com.trc.entity.Group;
import com.trc.service.DataConfigService;
import com.trc.service.DataService;
import com.trc.service.GroupService;
import com.trc.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/dataconfig",produces = "text/html;charset=UTF-8")
public class DataConfigController {
	@Resource
	private DataConfigService dataconfigService;
	@Resource
	private DataService dataService;
	@Resource
	private GroupService groupService;
	/**
	 * 新增一个节点
	 * @param son
	 * @param parent_id
	 * @param description
	 * @param son_type
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/add",produces = "text/html;charset=UTF-8")
	public @ResponseBody String add(
			@RequestParam(value = "son", required = false) String son,
			@RequestParam(value = "parent_id", required = false) String parent_id,
			@RequestParam(value = "description", required = false) String description,
			HttpServletResponse response){
	
		JSONObject result=new JSONObject();
//*****	判断为空
		if(StringUtil.isEmpty(parent_id)||StringUtil.isEmpty(son)){
			result.put("success", false);
			result.put("des","parent_id或son不能为空");
			return result.toString();
		}
//***** 判断父级是否存在，不存在直接提示
		if(!dataconfigService.ifConfigExist(Integer.parseInt(parent_id))){
			result.put("success", false);
			result.put("des", "父级目录不存在");
			return result.toString();
		}
		
//**** 判断是否已存在
		if (dataconfigService.ifSonExist(Integer.parseInt(parent_id),son)){
			result.put("success", false);
			result.put("des", "id为"+parent_id+"下面的son:"+son+"已存在");
			return result.toString();
		}

		 	Case_bas_config config=new Case_bas_config();
			config.setSon(son);
			config.setDescription(description);
			config.setParent_id(Integer.parseInt(parent_id));
//*******判断父级为root，则为其指定为company					
		  if("root".equals(dataconfigService.findConfigById(Integer.parseInt(parent_id)).getSon_type())){	
			  config.setSon_type("company");
		  }	
//*******判断父级为company，则为其指定为project					
		  if("company".equals(dataconfigService.findConfigById(Integer.parseInt(parent_id)).getSon_type())){	
			  config.setSon_type("project");
		  }			  
//*******判断父级为project，则为其指定为class					
		  if("project".equals(dataconfigService.findConfigById(Integer.parseInt(parent_id)).getSon_type())){	
			  config.setSon_type("class");
		  }
//*******判断父级为class，则为其指定为method					
		  if("class".equals(dataconfigService.findConfigById(Integer.parseInt(parent_id)).getSon_type())){	
			  config.setSon_type("method");
		  }
		  if("key".equals(dataconfigService.findConfigById(Integer.parseInt(parent_id)).getSon_type())){	
			  result.put("success", false);
			  result.put("des","key没有下一级，施主不带篡改表单这么玩的，我们可是有日志系统的！！！搞事，搞事，搞事！");
		      return result.toString();
		  }	
		  
//**** 判断父级是不是method
		if("method".equals(dataconfigService.findConfigById(Integer.parseInt(parent_id)).getSon_type())){
//				查出父级目录(method)下所有的group
			config.setSon_type("key");
				List<Group> groups=groupService.listGroup(Integer.parseInt(parent_id));
				
				if(0==groups.size()){
					 

				}else{
					List <Case_bus_data> list_data=new ArrayList<>();
				
					for (Group g:groups) {
						Case_bus_data data=new Case_bus_data();
						data.setMethod_id(Integer.parseInt(parent_id));
						data.setGroup_id(g.getId());
						data.setKey(son);
						data.setValue("");
						data.setCreater(150);
						list_data.add(data);
						
					}
//					判断插入情况，
					if (groupService.saveGroupDatas(list_data)) {
						
						
					}else{
						  result.put("success", false);
						  result.put("des","添加key时data数据入库失败");
					      return result.toString();
					}
					
					
					}
				
				
			  }	
		  
		  
		  
					
//***********存储
		  if (dataconfigService.saveDataConfig(config)) {
			  result.put("success", true);
			  result.put("des","成功入库");
			  return result.toString();
		  }else {
			  result.put("success", false);
			  result.put("des","入库失败");
		      return result.toString();
		  }
				
				
	}

	/**
	 * 删除一个节点
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/delete",produces = "text/html;charset=UTF-8")
	public @ResponseBody String delete(
			@RequestParam(value = "id", required = false) String id,
			HttpServletResponse response){
		JSONObject result=new JSONObject();
//*****	    如果为空则返回为空
		if (StringUtil.isEmpty(id)) {
		result.put("success", false);
		result.put("描述", "你传的id为空，朕不知道删哪个！");
		return result.toString();
		}
		if(!dataconfigService.ifConfigExist(Integer.parseInt(id))){
			result.put("success", false);
			result.put("描述", "删了个不存在的你想干啥？？");
			return result.toString();
		}
		
		
//*****		判断如果是key那么删除key返回		
		if("key".equals(dataconfigService.findConfigById(Integer.parseInt(id)).getSon_type())){
				if (dataconfigService.deletekey(Integer.parseInt(id))) {
					result.put("success", true);
					result.put("描述", "删除了一个key,我们一并删除了次key的method下的group还有data");
					return result.toString();
				}else{
					result.put("success", false);
					result.put("描述", "服务器傲娇地删除失败了！！！key");
					return result.toString();
				}			
			
		}
//******* 判断如果是method。删除所有key，删除group,删除data
		if("method".equals(dataconfigService.findConfigById(Integer.parseInt(id)).getSon_type())){
				if (dataconfigService.deletemethod(Integer.parseInt(id))) {
					result.put("success", true);
					result.put("描述", "删除了一个method,我们一并删除了key，group还有data");
					return result.toString();
				}else{
					result.put("success", false);
					result.put("描述", "服务器傲娇地删除失败了！！！");
					return result.toString();
				}			
			
		}		
//******* 判断如果是一般的配置项。如果有下级目录则不许删除
		if (dataconfigService.ifHaveNext(Integer.parseInt(id))) {
		result.put("success", false);
		result.put("描述", "这个节点下方还有数据！你是不是搞事？？？");
		return result.toString();
		}else{		
			if (dataconfigService.deleteDataConfig(Integer.parseInt(id))) {
				result.put("success", true);
				result.put("描述", "删除了一个非key和method的节点");
				return result.toString();
			}else{
				result.put("success", false);
				result.put("描述", "服务器傲娇地删除失败了！！！");
				return result.toString();
			}
		}

	}
	/**
	 *  更新一个配置数据
	 * @param id
	 * @param son
	 * @param description
	 * @param son_type
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/update",produces = "text/html;charset=UTF-8")
	public @ResponseBody String update(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "son", required = false) String son,
			@RequestParam(value = "description", required = false) String description,
			HttpServletResponse response){
		JSONObject result=new JSONObject();
//*****	判断为空
		if(StringUtil.isEmpty(id)||StringUtil.isEmpty(son)){
			result.put("success", false);
			result.put("des","id或son不能为空");
			return result.toString();
		}		
//****** 判断要修改的对象不存在
		if(!dataconfigService.ifConfigExist(Integer.parseInt(id))){
			result.put("success", false);
			result.put("des","要修改的对象不存在");
			return result.toString();
			
		}
		Case_bas_config caseConfig=dataconfigService.findConfigById(Integer.parseInt(id));
		String oldkey=caseConfig.getSon();
		caseConfig.setSon(son);
		caseConfig.setDescription(description);
//****** 判断要修改的对象是不是key，是的话一并更新data
		if("key".equals(caseConfig.getSon_type())){

			if(dataconfigService.updateKey(caseConfig, oldkey)){
				result.put("success", true);
				result.put("des","key修改成功,data更新成功");
				return result.toString();
			}else{
				result.put("success", false);
				result.put("des","key修改失败");
				return result.toString();
			}
			
		}
		



//******修改数据配置项
				if (dataconfigService.updateDataConfig(caseConfig)) {
					result.put("success", true);
					result.put("des","修改成功");
					return result.toString();
				}else {
					result.put("success", false);
					result.put("des","修改失败");
					return result.toString();
				}

	}
	/**
	 * 
	 * @param id
	 * @param response
	 * @return	返回下级目录的字符串数组
	 */
	@RequestMapping(value="/listnext",produces = "text/html;charset=UTF-8")
	public @ResponseBody String listnext(
			@RequestParam(value = "id", required = false) String id,
			HttpServletResponse response){
			JSONArray array=new JSONArray();
			JSONObject result=new JSONObject();
			
			//返回下级目录数组
			if(dataconfigService.ifHaveNext(Integer.parseInt(id))){
				List<Map<String,String>> str=new ArrayList<Map<String,String>>();
				List<Case_bas_config> list=dataconfigService.listNext(Integer.parseInt(id));
				for (int i = 0; i < list.size(); i++) {
					Map<String,String> temp=new HashMap<>();
					temp.put("id",  ""+list.get(i).getId());
					temp.put("parent_id",""+list.get(i).getParent_id());
					temp.put("son", list.get(i).getSon());
					temp.put("son_type", list.get(i).getSon());
					temp.put("description", list.get(i).getDescription());
					str.add(temp);
				}
				 array = JSONArray.fromObject(str);
					result.put("isempty", false);
					array.add(result);
				return array.toString();
				}else{
					result.put("isempty", true);
					array.add(result);
					return array.toString();
			}
			
	}
	
	
	
}
