package com.hafele.bos.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hafele.bos.domain.User;

/**
 * 
 * <p>Title: BOSUtils</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2018年7月28日
 */
public class BOSUtils {
	/**
	 * 
	 * <p>Title: getSession</p>  
	 * <p>Description: 获取session工具方法</p>  
	 * @return
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	
	public static User getLoginUser() {
		return (User) getSession().getAttribute("loginUser");
	}
}
