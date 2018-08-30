package com.hafele.bos.realm;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.hafele.bos.dao.IFunctionDao;
import com.hafele.bos.dao.IUserDao;
import com.hafele.bos.domain.Function;
import com.hafele.bos.domain.User;
/**
 * <p>Title: BOSRealm</p>  
 * <p>Description: 自定义Realm</p>  
 * @author Dragon.Wen
 * @date Aug 17, 2018
 */
public class BOSRealm extends AuthorizingRealm {

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IFunctionDao functionDao;
	
	/**
	 * 授权方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		List<Function> list = null;
		if(user.getUsername().equals("admin")) {
			//查询所有权限
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
			//超级管理员内置用户，查询所有权限数据
			list = functionDao.findByCriteria(detachedCriteria);
		}else {
			//查询数据库获取当前用户实际对应的权限
			list = functionDao.findFunctionListByUserId(user.getId());
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//授权
		for(Function function : list) {
			info.addStringPermission(function.getCode());
		}
		
		return info;
	}

	/**
	 * 认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken passwordToke = (UsernamePasswordToken) token;
		String username = passwordToke.getUsername();
		//根据页面传过来的用户名查询数据库中的密码
		User user = userDao.findByUsername(username);
		if(user == null) {
			//页面传递的账号不存在
			return null;
		}
		//用户名存在
		AuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
		return info;
	}

}
