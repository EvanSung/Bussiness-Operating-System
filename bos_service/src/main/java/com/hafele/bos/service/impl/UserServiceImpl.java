package com.hafele.bos.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hafele.bos.dao.IUserDao;
import com.hafele.bos.domain.Role;
import com.hafele.bos.domain.User;
import com.hafele.bos.service.IUserService;
import com.hafele.bos.utils.MD5Utils;
import com.hafele.bos.utils.PageBean;
/**
 * <p>Title: UserServiceImpl</p>  
 * <p>Description: 用户管理service实现类</p>  
 * @author Dragon.Wen
 * @date Jul 26, 2018
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	public User login(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		//使用MD5加密
		password = MD5Utils.md5(password);
		System.out.println(password);
		User user_callback = userDao.findByUsernameAndPassword(username,password);
		return user_callback;
	}

	@Override
	public void editPassword(String id, String password) {
		//使用MD5加密密码
		password = MD5Utils.md5(password);
		userDao.executeUpdate("user.editpassword", password,id);
	}

	@Override
	public void save(User model, String[] roleIds) {
		String password = MD5Utils.md5(model.getPassword());
		model.setPassword(password);
		userDao.save(model);
		if(roleIds != null && roleIds.length > 0) {
			for(String roleId : roleIds) {
				Role role = new Role(roleId);//托管对象
				model.getRoles().add(role);//用户关联角色
			}
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		userDao.pageQuery(pageBean);
	}

	@Override
	public User findById(String id) {
		User user_callBabk = userDao.findById(id);
		return user_callBabk;
	}

	@Override
	public void delete(String userId) {
		if(StringUtils.isNotBlank(userId)) {
			String[] userIds = userId.split(",");
			for(String id : userIds) {
				userDao.executeUpdate("user.delete", id);
			}
		}
	}

	@Override
	public void update(User user, String[] roleIds) {
		userDao.update(user);
		if(roleIds != null && roleIds.length > 0) {
			for(String roleId : roleIds) {
				Role role = new Role(roleId);//托管对象
				user.getRoles().add(role);//用户关联角色
			}
		}
	}


}
