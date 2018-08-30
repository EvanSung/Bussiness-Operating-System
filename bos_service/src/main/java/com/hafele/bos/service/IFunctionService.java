package com.hafele.bos.service;

import java.util.List;

import com.hafele.bos.domain.Function;
import com.hafele.bos.utils.PageBean;

/**
 * <p>Title: IFunctionService</p>  
 * <p>Description: 权限管理Service接口</p>  
 * @author Dragon.Wen
 * @date Aug 21, 2018
 */
public interface IFunctionService {

	/**
	 * <p>Title: findParentNodeAll</p>  
	 * <p>Description: 查询所有父节点权限</p>  
	 * @return
	 */
	public List<Function> findParentNodeAll();

	/**
	 * <p>Title: save</p>  
	 * <p>Description: 添加权限</p>  
	 * @param model
	 */
	public void save(Function model);

	/**
	 * <p>Title: pageQuery</p>  
	 * <p>Description: 分页查询</p>  
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * <p>Title: findMenu</p>  
	 * <p>Description: 根据登录人查询对应的菜单</p>  
	 * @return
	 */
	public List<Function> findMenu();

	/**
	 * <p>Title: findAll</p>  
	 * <p>Description: 查询所有权限菜单</p>  
	 * @return
	 */
	public List<Function> findAll();

	/**
	 * <p>Title: findById</p>  
	 * <p>Description: 根据权限id查询信息</p>  
	 * @param functionId
	 * @return
	 */
	public Function findById(String functionId);

	/**
	 * <p>Title: delete</p>  
	 * <p>Description: 删除权限</p>  
	 * @param functionId
	 */
	public void delete(String functionId);

	/**
	 * <p>Title: update</p>  
	 * <p>Description: 修改权限数据</p>  
	 * @param function
	 */
	public void update(Function function);

	/**
	 * <p>Title: findSystemMenu</p>  
	 * <p>Description: 查询系统管理菜单</p>  
	 * @return
	 */
	public List<Function> findSystemMenu();

}
