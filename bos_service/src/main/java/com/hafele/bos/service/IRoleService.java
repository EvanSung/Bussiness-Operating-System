package com.hafele.bos.service;

import java.util.List;

import com.hafele.bos.domain.Role;
import com.hafele.bos.utils.PageBean;

/**
 * <p>Title: IRoleService</p>  
 * <p>Description: 权限管理Service接口</p>  
 * @author Dragon.Wen
 * @date Aug 22, 2018
 */
public interface IRoleService {

	/**
	 * <p>Title: save</p>  
	 * <p>Description: 添加一个角色，同时需要关联权限</p>  
	 * @param model
	 * @param ids
	 */
	public void save(Role model, String ids);

	/**
	 * <p>Title: pageQuery</p>  
	 * <p>Description: 角色数据分页查询</p>  
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * <p>Title: findAll</p>  
	 * <p>Description: 查询所有的角色数据，返回json</p>  
	 * @return
	 */
	public List<Role> findAll();

	/**
	 * <p>Title: findById</p>  
	 * <p>Description: 根据角色ID查询角色详情</p>  
	 * @param roleId
	 * @return
	 */
	public Role findById(String roleId);

	/**
	 * <p>Title: update</p>  
	 * <p>Description: 更角色信息</p>  
	 * @param role
	 * @param functionIds
	 */
	public void update(Role role, String functionIds);

	/**
	 * <p>Title: delete</p>  
	 * <p>Description: 删除角色</p>  
	 * @param roleId
	 */
	public void delete(String roleId);

}
