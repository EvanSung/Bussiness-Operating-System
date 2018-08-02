package com.hafele.bos.service;

import com.hafele.bos.domain.User;
/**
 * 
 * <p>Title: IUserService</p>  
 * <p>Description: 用户管理service接口</p>  
 * @author Dragon.Wen
 * @date Jul 26, 2018
 */
public interface IUserService {
	public User login(User model);

	public void editPassword(User user);
}
