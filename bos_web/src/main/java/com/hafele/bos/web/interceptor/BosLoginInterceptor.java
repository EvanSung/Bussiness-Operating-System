package com.hafele.bos.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.hafele.bos.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * <p>Title: BosLoginInterceptor</p>  
 * <p>Description: 自定义拦截器，实现用户未登录自动跳转到登录界面</p>  
 * @author Dragon.Wen
 * @date 2018年7月28日
 */
public class BosLoginInterceptor extends MethodFilterInterceptor {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = -8780457925228516335L;

	//拦截方法
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//从session中获取user对象
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		if(user == null) {
			//没有登录，跳转到登录页面
			return "login";
		}
		//放行
		return invocation.invoke();
	}

}
