package com.trc.dao;

import java.util.List;

import com.trc.entity.Case_bas_config;
/**
 * 
 * @author Administrator
 *
 */
public interface DataConfigDao {
	/**
	 * 列出所有公司
	 * @return
	 */
	public List<Case_bas_config> listCompany();
	/**
	 * 通过父级元素id列出下级元素列表
	 * @param parent_id
	 * @return
	 */
	public List<Case_bas_config> listNext(int parent_id);
	/**
	 * 增加一条数据配置
	 * @param config
	 * @return
	 */
	public Integer addConfig(Case_bas_config config);
	/**
	 * 更新数据配置
	 * @param config
	 * @return
	 */
	public Integer updateConfig(Case_bas_config config);
	/**
	 * 删除数据配置
	 * @param id
	 * @return
	 */
	public Integer deleteConfig(int id);
	/**
	 * 通过id查找数据配置项
	 * @param id
	 * @return
	 */
	public Case_bas_config findConfigById(int id);
	/**
	 * 通过数据配置的id和son拿到一个实体
	 * @param id
	 * @param son
	 * @return
	 */
	public Case_bas_config findConfigByIdSon(int id,String son);
	/**
	 * 通过parent_id删除数据配置
	 * @param parent_id
	 * @return
	 */
	public Integer deleteConfigByParent(int parent_id);
}
