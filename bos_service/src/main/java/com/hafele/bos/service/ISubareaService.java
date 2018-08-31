package com.hafele.bos.service;

import java.util.List;

import com.hafele.bos.domain.Subarea;
import com.hafele.bos.utils.PageBean;

/**
 * <p>Title: ISubareaService</p>  
 * <p>Description: 管理分区Service接口</p>  
 * @author Dragon.Wen
 * @date Aug 2, 2018
 */
public interface ISubareaService {

	/**
	 * <p>Title: add</p>  
	 * <p>Description: 添加分区</p>  
	 * @param model
	 */
	public void add(Subarea model);

	/**
	 * <p>Title: pageQuery</p>  
	 * <p>Description: 分区分页查询</p>  
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * <p>Title: delete</p>  
	 * <p>Description: 删除分区</p>  
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * <p>Title: findById</p>  
	 * <p>Description: 根据ID查询分区</p>  
	 * @param id
	 * @return
	 */
	public Subarea findById(String id);

	/**
	 * <p>Title: update</p>  
	 * <p>Description: 修改分区</p>  
	 * @param subarea
	 */
	public void update(Subarea subarea);
	
	/**
	 * <p>Title: findAll</p>  
	 * <p>Description: 查询所有分区数据</p>  
	 * @return
	 */
	public List<Subarea> findAll();

	/**
	 * <p>Title: findListNotAssociation</p>  
	 * <p>Description: 查询所有未分配到定区的分区</p>  
	 * @return
	 */
	public List<Subarea> findListNotAssociation();

	/**
	 * <p>Title: findListByDecidedzoneId</p>  
	 * <p>Description: 根据定区id查询对应的分区</p>  
	 * @param decidedzoneId
	 * @return
	 */
	public List<Subarea> findListByDecidedzoneId(String decidedzoneId);

	/**
	 * <p>Title: saveBatch</p>  
	 * <p>Description: 分区数据批量保存</p>  
	 * @param subareaList
	 */
	public void saveBatch(List<Subarea> subareaList);


}
