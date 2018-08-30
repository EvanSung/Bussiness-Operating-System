package com.hafele.bos.service;

import com.hafele.bos.domain.User;
import com.hafele.bos.utils.PageBean;
/**
 * 
 * <p>Title: IUserService</p>  
 * <p>Description: 用户管理service接口</p>  
 * @author Dragon.Wen
 * @date Jul 26, 2018
 */
public interface IUserService {
	/**
	 * <p>Title: login</p>  
	 * <p>Description: 用户登录</p>  
	 * @param model
	 * @return
	 */
	public User login(User model);

	/**
	 * <p>Title: editPassword</p>  
	 * <p>Description: 修改密码</p>  
	 * @param id
	 * @param password
	 */
	public void editPassword(String id, String password);

	/**
	 * <p>Title: save</p>  
	 * <p>Description: 添加用户保存，同时关联角色</p>  
	 * @param model
	 * @param roleIds
	 */
	public void save(User model, String[] roleIds);

	/**
	 * <p>Title: pageQuery</p>  
	 * <p>Description: 用户数据分页查询</p>  
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * <p>Title: findById</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	public User findById(String id);

	/**
	 * <p>Title: delete</p>  
	 * <p>Description: 删除用户信息</p>  
	 * @param userId
	 */
	public void delete(String userId);

	/**
	 * <p>Title: update</p>  
	 * <p>Description: 更新用户信息</p>  
	 * @param user
	 * @param roleIds 
	 */
	public void update(User user, String[] roleIds);

	
}
