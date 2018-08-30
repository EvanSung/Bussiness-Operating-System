package com.hafele.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hafele.bos.dao.IUserDao;
import com.hafele.bos.dao.base.impl.BaseDaoImpl;
import com.hafele.bos.domain.User;
/**
 * 
 * <p>Title: UserDaoImpl</p>  
 * <p>Description:用户管理Dao实现类 </p>  
 * @author Dragon.Wen
 * @date Jul 26, 2018
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	/**
	 * 根据用户名和密码查询用户
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User findByUsernameAndPassword(String username, String password) {
		String hql = "FROM User WHERE username = ? AND password = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username, password);
		if(list != null && list.size() > 0) {
			//查询到数据了
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据用户名查询用户
	 */
	@Override
	public User findByUsername(String username) {
		String hql = "FROM User WHERE username = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username);
		if(list != null && list.size() > 0) {
			//查询到数据了
			return list.get(0);
		}
		return null;
	}

}
