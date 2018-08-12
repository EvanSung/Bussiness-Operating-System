package com.hafele.bos.service;

import java.util.List;

import com.hafele.bos.domain.Staff;
import com.hafele.bos.utils.PageBean;

/**
 * <p>Title: IStaffService</p>  
 * <p>Description: 取派员管理Service接口</p>  
 * @author Dragon.Wen
 * @date Jul 30, 2018
 */
public interface IStaffService {

	/**
	 * <p>Title: add</p>  
	 * <p>Description: 添加取派员方法</p>  
	 * @param model
	 */
	public void add(Staff model);

	/**
	 * <p>Title: pageQuery</p>  
	 * <p>Description: 取派员信息分页查询方法</p>  
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * <p>Title: delete</p>  
	 * <p>Description: 删除取派员</p>  
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * <p>Title: findById</p>  
	 * <p>Description: 根据ID查询取派员信息</p>  
	 * @param id
	 * @return
	 */
	public Staff findById(String id);

	/**
	 * <p>Title: update</p>  
	 * <p>Description: 更新取派员信息</p>  
	 * @param staff
	 */
	public void update(Staff staff);

	/**
	 * <p>Title: restore</p>  
	 * <p>Description: 还原取派员信息</p>  
	 * @param ids
	 */
	public void restore(String ids);

	/**
	 * <p>Title: findListNotDelete</p>  
	 * <p>Description: 查询未删除的取派员</p>  
	 * @return
	 */
	public List<Staff> findListNotDelete();

}
