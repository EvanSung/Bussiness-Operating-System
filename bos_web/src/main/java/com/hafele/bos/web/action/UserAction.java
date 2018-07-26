package com.hafele.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hafele.bos.domain.User;
import com.hafele.bos.service.IUserService;
import com.hafele.bos.web.action.base.BaseAction;

/**
 * <p>Title: UserAction</p>  
 * <p>Description: 用户管理</p>  
 * @author Dragon.Wen
 * @date Jul 26, 2018
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = -4627238745299806534L;

	//接收页面输入的验证码
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	@Autowired
	private IUserService userService;
}
