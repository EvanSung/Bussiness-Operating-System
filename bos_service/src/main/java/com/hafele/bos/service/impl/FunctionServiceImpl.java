package com.hafele.bos.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hafele.bos.dao.IFunctionDao;
import com.hafele.bos.domain.Function;
import com.hafele.bos.domain.User;
import com.hafele.bos.service.IFunctionService;
import com.hafele.bos.utils.BOSUtils;
import com.hafele.bos.utils.PageBean;
/**
 * <p>Title: FunctionServiceImpl</p>  
 * <p>Description: 权限管理Service接口实现</p>  
 * @author Dragon.Wen
 * @date Aug 21, 2018
 */
@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {

	@Autowired
	private IFunctionDao functionDao;

	@Override
	public List<Function> findParentNodeAll() {
		return functionDao.findParentNodeAll();
	}

	@Override
	public void save(Function model) {
		Function parentFunction = model.getParentFunction();
		if(parentFunction != null && parentFunction.getId().equals("")) {
			model.setParentFunction(null);
		}
		functionDao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		functionDao.pageQuery(pageBean);
	}

	@Override
	public List<Function> findMenu() {
		List<Function> list = null;
		User user = BOSUtils.getLoginUser();
		if(user.getUsername().equals("admin") || user.getUsername().equals("superadmin")){
			//如果是超级管理员内置用户，查询所有基本功能菜单
			list = functionDao.findAllMenu();
		}else{
			//其他用户，根据用户id查询基本功能菜单
			list = functionDao.findMenuByUserId(user.getId());
		}
		return list;
	}

	@Override
	public List<Function> findAll() {
		return functionDao.findAll();
	}

	@Override
	public Function findById(String functionId) {
		Function function_callBack = functionDao.findById(functionId);
		return function_callBack;
	}

	@Override
	public void delete(String functionId) {
		if(StringUtils.isNotBlank(functionId)) {
			String[] functionIds = functionId.split(",");
			for(String id : functionIds) {
				functionDao.executeUpdate("function.delete", id);
			}
		}
	}

	@Override
	public void update(Function function) {
		Function parentFunction = function.getParentFunction();
		if(parentFunction != null && parentFunction.getId().equals("")) {
			function.setParentFunction(null);
		}
		functionDao.update(function);
	}

	@Override
	public List<Function> findSystemMenu() {
		List<Function> list = null;
		User user = BOSUtils.getLoginUser();
		if(user.getUsername().equals("superadmin")){
			//如果是超级管理员，查询所有系统管理菜单
			list = functionDao.findAllSystemMenu();
		}else{
			//其他管理员，根据管理员id查询系统管理菜单
			list = functionDao.findSystemMenuByUserId(user.getId());
		}
		return list;
	}
	
}
