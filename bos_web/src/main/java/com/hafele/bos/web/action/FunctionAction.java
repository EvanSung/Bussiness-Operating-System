package com.hafele.bos.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hafele.bos.domain.Function;
import com.hafele.bos.domain.User;
import com.hafele.bos.service.IFunctionService;
import com.hafele.bos.web.action.base.BaseAction;
/**
 * <p>Title: FunctionAction</p>  
 * <p>Description: 权限管理</p>  
 * @author Dragon.Wen
 * @date Aug 21, 2018
 */
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = 1657458347191896L;

	@Autowired
	private IFunctionService functionService;
	
	//获取权限ID
	private String functionId;
	
	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	/**
	 * <p>Title: listajax</p>  
	 * <p>Description: 查询所有权限，返回json数据</p>  
	 * @return
	 */
	public String listajaxParentNode() {
		List<Function> list = functionService.findParentNodeAll();
		this.java2Json(list, new String[]{"parentFunction","roles","children"});
		return NONE;
	}
	
	public String listajax() {
		List<Function> list = functionService.findAll();
		this.java2Json(list, new String[]{"parentFunction","roles","children"});
		return NONE;
	}
	
	/**
	 * <p>Title: add</p>  
	 * <p>Description: 添加权限</p>  
	 * @return
	 */
	public String add() {
		functionService.save(model);
		return LIST;
	}
	
	/**
	 * <p>Title: pageQuery</p>  
	 * <p>Description: 分页查询</p>  
	 * @return
	 */
	public String pageQuery() {
		String page = model.getPage();
		pageBean.setCurrentPage(Integer.parseInt(page));
		functionService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[]{"parentFunction","roles","children"});
		return NONE;
	}
	
	/**
	 * <p>Title: findMenu</p>  
	 * <p>Description: 根据登录人查询基本管理的菜单，返回json</p>  
	 * @return
	 */
	public String findMenu() {
		List<Function> list = functionService.findMenu();
		this.java2Json(list, new String[]{"parentFunction","children","roles"});
		return NONE;
	}
	
	/**
	 * <p>Title: findSystemMenu</p>  
	 * <p>Description: 查询系统管理菜单</p>  
	 * @return
	 */
	public String findSystemMenu() {
		List<Function> list = functionService.findSystemMenu();
		this.java2Json(list, new String[]{"parentFunction","children","roles"});
		return NONE;
	}
	
	/**
	 * <p>Title: callBack</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	public String callBack() {
		Function function = functionService.findById(functionId);
		ServletActionContext.getRequest().getSession().setAttribute("editFunction", function);
		return EDIT;
	}
	
	/**
	 * <p>Title: edit</p>  
	 * <p>Description: 修改权限</p>  
	 * @return
	 */
	public String edit() {
		//先查询数据库原始数据
		String id = model.getId();
		Function function = functionService.findById(id);
		
		function.setName(model.getName());
		function.setCode(model.getCode());
		function.setDescription(model.getDescription());
		function.setPage(model.getPage());
		function.setGeneratemenu(model.getGeneratemenu());
		function.setZindex(model.getZindex());
		function.setRoles(model.getRoles());
		function.setParentFunction(model.getParentFunction());
		function.setChildren(model.getChildren());
		
		functionService.update(function);
		
		return LIST;
	}
	
	/**
	 * <p>Title: delete</p>  
	 * <p>Description: 删除权限</p>  
	 * @return
	 */
	public String delete() {
		functionService.delete(functionId);
		return LIST;
	}
}
