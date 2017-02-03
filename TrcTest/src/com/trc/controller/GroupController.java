package com.trc.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trc.entity.Case_bus_data;
import com.trc.entity.Group;
import com.trc.service.GroupService;
import com.trc.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
*@author  作者 : 陈达
*@date 创建时间：2017年1月17日 下午6:27:16
*@version 1.0 
*@parameter  
*@return 
*/
@Controller
@RequestMapping(value="/group",produces = "text/html;charset=UTF-8")
public class GroupController {
		@Resource
		private GroupService groupService;
		
		/**
		 * 列出所有的group，通过method_id
		 * @param method_id
		 * @param response
		 * @return
		 */
		@RequestMapping(value="/listdeatil",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String listdeatil(
				@RequestParam(value = "method_id", required = false) String method_id,
				HttpServletResponse response){
			
			JSONObject result1=new JSONObject();
			if(StringUtil.isEmpty(method_id)){
				result1.put("sucess", false);
				result1.put("des", "method_id不能为空");
				return result1.toString();
			}
			
			
			JSONArray array=new JSONArray();
			List<Map<String, String>> list=groupService.listGroupDataForShow(Integer.parseInt(method_id));
			
			for (int i = 0; i < list.size(); i++) {
				JSONObject result=JSONObject.fromObject(list.get(i));
				array.add(result);
			}
			if(0==array.size()){
				result1.put("sucess", false);
				result1.put("des", "此method下未找到结果");
				return result1.toString();
			}
			
			return array.toString();
		}
		/**
		 * 列出全部的groups
		 * @param method_id
		 * @param response
		 * @return
		 */
		@RequestMapping(value="/list",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String list(
				@RequestParam(value = "method_id", required = false) String method_id,
				HttpServletResponse response){
			JSONObject result1=new JSONObject();
			if(StringUtil.isEmpty(method_id)){
				result1.put("sucess", false);
				result1.put("des", "method_id不能为空");
				return result1.toString();
			}
			
			JSONArray array=new JSONArray();
			JSONObject head=new JSONObject();

			head.put("id", "id");
			head.put("creater", "创建者");
			head.put("create_time", "创建时间");
			head.put("group","组号");
			head.put("method_id","方法的id");
			head.put("modify_time", "修改时间");
			array.add(head);
			
			
			List<Group> groups= groupService.listGroup(Integer.parseInt(method_id));
			for (int i = 0; i < groups.size(); i++) {
				JSONObject group=new JSONObject();
				Group temp=groups.get(i);
				group.put("id", temp.getId());
				group.put("creater", temp.getCreater());
				group.put("create_time", temp.getCreate_time());
				group.put("group", temp.getGroup());
				group.put("method_id", temp.getMethod_id());
				group.put("modify_time", temp.getModify_time());
				array.add(group);
			}
			
			if(0==array.size()){
				result1.put("sucess", false);
				result1.put("des", "此method下未找到结果");
				return result1.toString();
			}
			
			return array.toString();
		}
		/**
		 * 列出一个group下的data
		 * @param group_id
		 * @param response
		 * @return json
		 */
		@RequestMapping(value="/listdatas",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String listdatas(
				@RequestParam(value = "group_id", required = false) String group_id,
				@RequestParam(value = "method_id", required = false) String method_id,
				HttpServletResponse response){
			
			JSONObject result1=new JSONObject();
			if(StringUtil.isEmpty(method_id)){
				result1.put("sucess", false);
				result1.put("des", "method_id不能为空");
				return result1.toString();
			}
			if(StringUtil.isEmpty(group_id)){
				result1.put("sucess", false);
				result1.put("des", "group_id不能为空");
				return result1.toString();
			}
			
			
			JSONArray array=new JSONArray();
			List<Case_bus_data> datas= groupService.listGroupData(Integer.parseInt(method_id), Integer.parseInt(group_id));
			JSONObject head=new JSONObject();
			head.put("id", "id");
			head.put("key", "key");
			head.put("value", "value");
			head.put("creater", "创建者id");
			array.add(head);
			
			for (int i = 0; i < datas.size(); i++) {
				
				JSONObject data=new JSONObject();
				Case_bus_data temp=datas.get(i);
				data.put("id", temp.getId());
				data.put("key", temp.getKey());
				data.put("value",temp.getValue());
				data.put("creater",temp.getCreater());
				array.add(data);
			}
			if(0==array.size()){
				result1.put("sucess", false);
				result1.put("des", "此method、group下未找到结果");
				return result1.toString();
			}
			
			return array.toString();
		}
		/**
		 * 新增一个group
		 * @param method_id
		 * @param group
		 * @param response
		 * @return
		 */
		@RequestMapping(value="/add",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String add(
				@RequestParam(value = "method_id", required = false) String method_id,
				@RequestParam(value = "group", required = false) String group,
				HttpServletResponse response){
				JSONObject result1=new JSONObject();
				if(StringUtil.isEmpty(method_id)){
					result1.put("sucess", false);
					result1.put("des", "method_id不能为空");
					return result1.toString();
				}
				if(StringUtil.isEmpty(group)){
					result1.put("sucess", false);
					result1.put("des", "group不能为空");
					return result1.toString();
				}
				Group group1 =new Group();
				group1.setGroup(group);
				if(groupService.addGroup(group1)){
					result1.put("sucess", true);
					result1.put("des", "group新增成功");
					return result1.toString();
				}else{
					result1.put("sucess", false);
					result1.put("des", "新增失败");
					return result1.toString();
				}

		}
		/**
		 * 保存数据信息，待完善
		 * @param json
		 * @param response
		 * @return
		 */
		
		@RequestMapping(value="/savedatas",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String savedatas(
				@RequestParam(value = "json", required = false) String json,
				HttpServletResponse response){
				JSONObject result1=new JSONObject();
				if(StringUtil.isEmpty(json)){
					result1.put("sucess", false);
					result1.put("des", "method_id不能为空");
					return result1.toString();
				}
				return result1.toString();
		}	
		/**
		 * 删除一个Group
		 * @param group_id
		 * @param response
		 * @return
		 */
		@RequestMapping(value="/delete",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String delete(
				@RequestParam(value = "group_id", required = false) String group_id,
				HttpServletResponse response){                                                                                                                                                                                    
				JSONObject result1=new JSONObject();
				if(StringUtil.isEmpty(group_id)){
					result1.put("sucess", false);
					result1.put("des", "method_id不能为空");
					return result1.toString();
				}
				if(groupService.deleteGroup(Integer.parseInt(group_id))){
					result1.put("sucess", true);
					result1.put("des", "删除成功");
					return result1.toString();
				}else{
					result1.put("sucess", false);
					result1.put("des", "删除失败");
					return result1.toString();
				}
			}
		/**
		 * 批量删除ListData
		 * @param group_id
		 * @param response
		 * @return
		 */
		@RequestMapping(value="/deletes",produces = "text/html;charset=UTF-8")
		public  @ResponseBody String deletes(
				@RequestParam(value = "group_id[]", required = false) List<Integer> group_id,
				HttpServletResponse response){                                                                                                                                                                                    
				JSONObject result1=new JSONObject();
				if(0==group_id.size()){
					result1.put("sucess", false);
					result1.put("des", "你传了空数组");
				}
				if(groupService.deleteGroups(group_id)){
					result1.put("sucess", true);
					result1.put("des", "删除成功");
					return result1.toString();
				}else{
					result1.put("sucess", false);
					result1.put("des", "删除失败");
					return result1.toString();
				}
			}
			
		

}
