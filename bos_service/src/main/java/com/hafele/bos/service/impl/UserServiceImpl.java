package com.hafele.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hafele.bos.dao.IUserDao;
import com.hafele.bos.domain.User;
import com.hafele.bos.service.IUserService;
import com.hafele.bos.utils.MD5Utils;
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
	public void editPassword(User user) {
		user.setPassword(MD5Utils.md5(user.getPassword()));
		userDao.update(user);
	}

}
