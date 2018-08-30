package com.hafele.bos.dao;

import com.hafele.bos.dao.base.IBaseDao;
import com.hafele.bos.domain.User;

/**
 * <p>Title: IUserDao</p>  
 * <p>Description: 用户管理Dao接口</p>  
 * @author Dragon.Wen
 * @date Jul 26, 2018
 */
public interface IUserDao extends IBaseDao<User> {
	/**
	 * <p>Title: findByUsernameAndPassword</p>  
	 * <p>Description: 根据用户名和密码查询用户</p>  
	 * @param username
	 * @param password
	 * @return
	 */
	public User findByUsernameAndPassword(String username, String password);

	/**
	 * <p>Title: findByUsername</p>  
	 * <p>Description: 根据用户名查询用户</p>  
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);

}
