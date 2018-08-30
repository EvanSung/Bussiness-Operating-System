package com.hafele.bos.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hafele.bos.domain.Role;
import com.hafele.bos.service.IRoleService;
import com.hafele.bos.web.action.base.BaseAction;
/**
 * <p>Title: RoleAction</p>  
 * <p>Description: 角色管理Action</p>  
 * @author Dragon.Wen
 * @date Aug 22, 2018
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = 2941466545489576961L;

	@Autowired
	private IRoleService roleService;
	
	//接收页面拼接的权限字符串
	private String functionIds;
	
	//获取要修改或删除的角色id
	private String roleId;
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}
		
	
	/**
	 * <p>Title: add</p>  
	 * <p>Description: 添加角色</p>  
	 * @return
	 */
	public String add() {
		roleService.save(model,functionIds);
		return LIST;
	}
	
	/**
	 * <p>Title: delete</p>  
	 * <p>Description: 删除角色</p>  
	 * @return
	 */
	public String delete() {
		roleService.delete(roleId);
		return LIST;
	}
	
	/**
	 * <p>Title: edit</p>  
	 * <p>Description: 修改角色</p>  
	 * @return
	 */
	public String edit() {
		//先查询数据库原始数据
		String id = model.getId();
		Role role = roleService.findById(id);
		
		role.setName(model.getName());
		role.setCode(model.getCode());
		role.setDescription(model.getDescription());
		role.setFunctions(model.getFunctions());
		role.setUsers(model.getUsers());
		
		roleService.update(role,functionIds);
		
		return LIST;
	}
	
	/**
	 * <p>Title: pageQuery</p>  
	 * <p>Description: 角色数据分页查询</p>  
	 * @return
	 */
	public String pageQuery() {
		roleService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[] {"functions","users"});
		return NONE;
	}
	
	/**
	 * <p>Title: listajax</p>  
	 * <p>Description: 查询所有的角色数据，返回json</p>  
	 * @return
	 */
	public String listajax() {
		List<Role> list = roleService.findAll();
		this.java2Json(list, new String[]{"functions","users"});
		return NONE;
	}
	
	/**
	 * <p>Title: callBack</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	public String callBack() {
		Role role = roleService.findById(roleId);
		ServletActionContext.getRequest().getSession().setAttribute("editRole", role);
		return EDIT;
	}
	
}
