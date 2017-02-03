package com.trc.service;

import java.util.List;

import com.trc.entity.Case_bas_config;

/** 
 *@author  ���� : �´�
 *@date ����ʱ�䣺2017��1��12�� ����2:50:52
 *@version 1.0 
 *@parameter  
 *@return 
 */
public interface DataConfigService {
	/**
	 * 保存一个配置
	 * @param config
	 * @return
	 */
	public boolean saveDataConfig(Case_bas_config config);
	/**
	 * 修改一个配置项
	 * @param config
	 * @return
	 */
	public boolean updateDataConfig(Case_bas_config config);
	/**
	 * 删除配置
	 * @param id
	 * @return
	 */
	public boolean deleteDataConfig(int id);
	/**
	 * 列出公司
	 * @return
	 */
	public List<Case_bas_config> listCompany();
	/**
	 * 列出下级列表
	 * @param parent_id
	 * @return
	 */
	public List<Case_bas_config> listNext(int parent_id);
	/**
	 * 配置项是否存在
	 * @param id
	 * @return
	 */
	public boolean ifConfigExist(int id);
	/**
	 * son是否已存在
	 * @param id
	 * @param son
	 * @return
	 */
	public boolean ifSonExist(int id,String son);
	/**
	 * 当前页是不是key
	 * @param id
	 * @return
	 */
	public boolean ifConfigisKey(int id);
	/**
	 * 此ID是否还关联下级属性
	 * @param id
	 * @return
	 */
	public boolean ifHaveNext(int id);
	/**
	 * 查找一个config
	 * @param id
	 * @return
	 */
	public Case_bas_config findConfigById(int id);
	/**
	 * 通过父级元素id删除一个配置数据
	 * @param parent_id
	 * @return
	 */
	public Boolean deleteConfigByParent(int parent_id);
	/**
	 * 删除一个方法节点
	 * @param method_id
	 * @return
	 */
	public boolean deletemethod(int method_id);
	/**
	 * 删除一个key节点
	 * @param key_id
	 * @return
	 */
	public boolean deletekey(int key_id);
	/**
	 * 更新key
	 * @param config
	 * @param oldkey
	 * @return
	 */
	public Boolean updateKey(Case_bas_config config,String oldkey);
	public Boolean addKey(Case_bas_config config);
	
	
}
