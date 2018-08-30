package com.hafele.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hafele.bos.dao.IRoleDao;
import com.hafele.bos.domain.Function;
import com.hafele.bos.domain.Role;
import com.hafele.bos.service.IRoleService;
import com.hafele.bos.utils.PageBean;
/**
 * <p>Title: RoleServiceImpl</p>  
 * <p>Description: 权限管理Service接口实现</p>  
 * @author Dragon.Wen
 * @date Aug 22, 2018
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;

	@Override
	public void save(Role model, String ids) {
		roleDao.save(model);//持久状态
		if(StringUtils.isNotBlank(ids)) {
			String[] functionIds = ids.split(",");
			for(String functionId : functionIds) {
				Function function = new Function();
				function.setId(functionId);//托管对象
				model.getFunctions().add(function);//角色关联权限
			}
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		roleDao.pageQuery(pageBean);
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public Role findById(String roleId) {
		return roleDao.findById(roleId);
	}

	@Override
	public void update(Role role, String ids) {
		roleDao.save(role);//持久状态
		if(StringUtils.isNotBlank(ids)) {
			String[] functionIds = ids.split(",");
			for(String functionId : functionIds) {
				Function function = new Function();
				function.setId(functionId);//托管对象
				role.getFunctions().add(function);//角色关联权限
			}
		}
	}

	@Override
	public void delete(String roleId) {
		if(StringUtils.isNotBlank(roleId)) {
			String[] roleIds = roleId.split(",");
			for(String id : roleIds) {
				roleDao.executeUpdate("role.delete", id);
			}
		}
	}
	
}
