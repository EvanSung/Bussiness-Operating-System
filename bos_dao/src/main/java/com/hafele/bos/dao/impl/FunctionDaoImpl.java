package com.hafele.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hafele.bos.dao.IFunctionDao;
import com.hafele.bos.dao.base.impl.BaseDaoImpl;
import com.hafele.bos.domain.Function;
/**
 * <p>Title: FunctionDaoImpl</p>  
 * <p>Description: 权限管理Dao接口实现</p>  
 * @author Dragon.Wen
 * @date Aug 21, 2018
 */
@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {
	
	/**
	 * 重写父类的findAll方法
	 */
	@Override
	public List<Function> findParentNodeAll() {
		String hql = "FROM Function f WHERE f.parentFunction IS NULL";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public List<Function> findFunctionListByUserId(String id) {
		String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles"
				+ " r LEFT OUTER JOIN r.users u WHERE u.id = ?";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql, id);
		return list;
	}

	@Override
	public List<Function> findAllMenu() {
		String hql = "FROM Function f WHERE f.generatemenu = '1' AND type = '1' ORDER BY f.zindex ASC";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public List<Function> findMenuByUserId(String id) {
		String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles"
				+ " r LEFT OUTER JOIN r.users u WHERE u.id = ? AND f.generatemenu = '1' "
				+ "ORDER BY f.zindex DESC";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql, id);
		return list;
	}

	@Override
	public List<Function> findAllSystemMenu() {
		String hql = "FROM Function f WHERE f.generatemenu = '1' AND type = '0' ORDER BY f.zindex ASC";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public List<Function> findSystemMenuByUserId(String id) {
		String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles"
				+ " r LEFT OUTER JOIN r.users u WHERE u.id = ? AND f.generatemenu = '1' AND f.type = '0' "
				+ "ORDER BY f.zindex DESC";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql, id);
		return list;
	}

}
