package com.trc.dao;
import java.util.List;

import com.trc.entity.Group;
/**
 * 
 * @author Administrator
 *
 */
public interface GroupDao {
	/**
	 * 通过methodid和group
	 * @param method_id
	 * @param group
	 * @return
	 */
	public List<Group> listGroup(int method_id);
	/**
	 * 插入一个group返回主键ID
	 * @param group
	 * @return
	 */
	public Integer addGroupGetID(Group group);
	/**
	 * 增加新的group
	 * @param group
	 * @return
	 */
	public Integer addGroup(Group group);
	/**
	 * 删除group
	 * @param group_id
	 * @return
	 */
	public Integer deleteGroup(int group_id);
	/**
	 * 更新group
	 * @param group
	 * @return
	 */
	public Integer updateGroup(Group group);
	/**
	 * 通过method_id和group的值获取一个group
	 * @param method_id
	 * @param group
	 * @return
	 */
	public Group  findGroupBymg(int method_id,int group);
	/**
	 * 批量删除Group
	 * @param list
	 * @return
	 */
	public Integer deleteGroups(List<Integer> list);
}
