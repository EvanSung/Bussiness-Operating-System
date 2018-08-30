package com.hafele.bos.web.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hafele.bos.domain.User;
import com.hafele.bos.service.IUserService;
import com.hafele.bos.utils.BOSUtils;
import com.hafele.bos.utils.MD5Utils;
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
	
	//接收页面提交的多个角色id
	private String[] roleIds;
	
	//获取要修改的用户id
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

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
			Subject subject = SecurityUtils.getSubject();
			AuthenticationToken token = new UsernamePasswordToken(model.getUsername(),MD5Utils.md5(model.getPassword()));
			try {
				subject.login(token);
			} catch (AuthenticationException e) {
				this.addActionError("用户名或者密码错误！");
				e.printStackTrace();
				return LOGIN;
			}
			
			//获取user对象
			User user = (User) subject.getPrincipal();
			//登录成功，将user对象放入session，跳转到首页
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
			return HOME;
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
		try{
			userService.editPassword(user.getId(),model.getPassword());
		}catch(Exception e){
			tag = "0";
			e.printStackTrace();
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(tag);
		return NONE;
	}
	
	/**
	 * <p>Title: delete</p>  
	 * <p>Description: 删除用户信息</p>  
	 * @return
	 */
	public String delete() {
		userService.delete(userId);
		return LIST;
	}
	
	/**
	 * <p>Title: add</p>  
	 * <p>Description: 添加用户</p>  
	 * @return
	 */
	public String add() {
		userService.save(model,roleIds);
		return LIST;
	}
	
	/**
	 * <p>Title: edit</p>  
	 * <p>Description: 修改用户数据</p>  
	 * @return
	 */
	public String edit() {
		//先查询数据库原始数据
		String id = model.getId();
		User user = userService.findById(id);
		
		user.setUsername(model.getUsername());
		if(user.getPassword().equals(model.getPassword())) {
			user.setPassword(user.getPassword());
		}else {
			user.setPassword(MD5Utils.md5(model.getPassword()));
		}
		user.setSalary(model.getSalary());
		user.setBirthday(model.getBirthday());
		user.setGender(model.getGender());
		user.setStation(model.getStation());
		user.setTelephone(model.getTelephone());
		user.setRemark(model.getRemark());
		user.setRoles(model.getRoles());
		
		userService.update(user,roleIds);
		
		return LIST;
	}
	
	/**
	 * <p>Title: pageQuery</p>  
	 * <p>Description: 用户数据分页查询</p>  
	 * @return
	 */
	public String pageQuery() {
		userService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[] {"noticebills","roles"});
		return NONE;
	}
	
	/**
	 * <p>Title: callBack</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	public String callBack() {
		User user = userService.findById(userId);
		ServletActionContext.getRequest().getSession().setAttribute("editUser", user);
		return EDIT;
	}
}
