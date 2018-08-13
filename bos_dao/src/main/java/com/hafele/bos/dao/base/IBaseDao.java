package com.hafele.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hafele.bos.utils.PageBean;



/**
 * <p>Title: IBaseDao</p>  
 * <p>Description: 持久层通用接口</p>  
 * @author Dragon.Wen
 * @date Jul 25, 2018
 * @param <T>
 */
public interface IBaseDao<T> {
	/**
	 * <p>Title: save</p>  
	 * <p>Description: 保存</p>  
	 * @param entity
	 */
	public void save(T entity);
	
	/**
	 * <p>Title: delete</p>  
	 * <p>Description: 删除</p>  
	 * @param entity
	 */
	public void delete(T entity);
	
	/**
	 * <p>Title: update</p>  
	 * <p>Description: 更新</p>  
	 * @param entity
	 */
	public void update(T entity);
	
	/**
	 * <p>Title: findById</p>  
	 * <p>Description: 根据ID查询</p>  
	 * @param id
	 * @return
	 */
	public T findById(Serializable id);
	
	/**
	 * <p>Title: findAll</p>  
	 * <p>Description: 查询所有</p>  
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * <p>Title: executeUpdate</p>  
	 * <p>Description: 自定义通用更新方法</p>  
	 * @param queryName
	 * @param objects
	 */
	public void executeUpdate(String queryName,Object...objects);
	
	/**
	 * <p>Title: pageQuery</p>  
	 * <p>Description: 通用分页查询方法</p>  
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);
	
	/**
	 * <p>Title: saveOrUpdate</p>  
	 * <p>Description: 更新保存</p>  
	 * @param region
	 */
	public void saveOrUpdate(T entity);
	
	/**
	 * <p>Title: findByCriteria</p>  
	 * <p>Description: </p>  
	 * @param detachedCriteria
	 * @return
	 */
	public List<T> findByCriteria(DetachedCriteria detachedCriteria);
}
