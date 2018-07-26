package com.hafele.bos.dao.base;

import java.io.Serializable;
import java.util.List;

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
}
