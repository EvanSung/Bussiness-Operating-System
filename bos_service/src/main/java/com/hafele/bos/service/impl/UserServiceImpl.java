package com.hafele.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hafele.bos.dao.IUserDao;
import com.hafele.bos.domain.User;
import com.hafele.bos.service.IUserService;
/**
 * <p>Title: UserServiceImpl</p>  
 * <p>Description: 用户管理Service</p>  
 * @author Dragon.Wen
 * @date Jul 26, 2018
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;
	
	@Override
	public User login(User model) {
		// TODO Auto-generated method stub
		return null;
	}

}
