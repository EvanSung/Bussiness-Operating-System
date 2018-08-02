package com.hafele.bos.service;

import java.util.List;

import com.hafele.bos.domain.Region;
import com.hafele.bos.utils.PageBean;

/**
 * <p>Title: IRegionService</p>  
 * <p>Description: 区域设置Service接口</p>  
 * @author Dragon.Wen
 * @date Aug 1, 2018
 */
public interface IRegionService {

	/**
	 * <p>Title: saveBatch</p>  
	 * <p>Description: 区域数据批量保存</p>  
	 * @param regionList
	 */
	public void saveBatch(List<Region> regionList);

	/**
	 * <p>Title: pageQuery</p>  
	 * <p>Description: 区域分页查询</p>  
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * <p>Title: findAll</p>  
	 * <p>Description: 查询所有区域</p>  
	 * @return
	 */
	public List<Region> findAll();

	/**
	 * <p>Title: findListByQ</p>  
	 * <p>Description: 根据页面输入进行模糊查询</p>  
	 * @param q
	 * @return
	 */
	public List<Region> findListByQ(String q);

}
