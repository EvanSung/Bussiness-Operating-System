package com.hafele.bos.web.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hafele.bos.domain.User;
import com.hafele.bos.service.IUserService;
import com.hafele.bos.utils.BOSUtils;
import com.hafele.bos.web.action.base.BaseAction;

/**
 * <p>Title: UserAction</p>  
 * <p>Description: 用户管理Action</p>  
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
	
	/**
	 * <p>Title: login</p>  
	 * <p>Description: 用户登录方法</p>  
	 * @return
	 */
	public String login() {
		//从session中获取生成的验证码
		String validateCode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		//校验验证码是否输入正确
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(validateCode)) {
			//输入的验证码正确
			User user = userService.login(model);
			if(user == null) {
				//登录失败，,设置提示信息，跳转到登录页面
				this.addActionError("用户名或者密码输入错误！");
				return LOGIN;
			}else {
				//登录成功,将user对象放入session，跳转到首页
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				return HOME;
			}
		}else {
			//输入的验证码错误,设置提示信息，跳转到登录页面
			this.addActionError("输入的验证码错误！");
			return LOGIN;
		}
	}
	
	/**
	 * <p>Title: logout</p>  
	 * <p>Description: 用户注销方法</p>  
	 * @return
	 */
	public String logout() {
		//销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}
	
	/**
	 * <p>Title: editPassword</p>  
	 * <p>Description: 修改密码</p>  
	 * @return
	 * @throws IOException 
	 */
	public String editPassword() throws IOException {
		String tag = "1"; //修改结果标识位，1 表示成功， 0 表示失败
		User user = BOSUtils.getLoginUser();
		user.setPassword(model.getPassword());
		try {
			userService.editPassword(user);
		} catch (Exception e) {
			tag = "0";
			e.printStackTrace();
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(tag);
		return NONE;
	}
}
