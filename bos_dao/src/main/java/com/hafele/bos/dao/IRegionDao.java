package com.hafele.bos.dao;

import java.util.List;

import com.hafele.bos.dao.base.IBaseDao;
import com.hafele.bos.domain.Region;

/**
 * <p>Title: IRegionDao</p>  
 * <p>Description: 区域设置Dao接口</p>  
 * @author Dragon.Wen
 * @date Aug 1, 2018
 */
public interface IRegionDao extends IBaseDao<Region> {

	/**
	 * <p>Title: findListByQ</p>  
	 * <p>Description: 根据q参数进行模糊查询</p>  
	 * @param q
	 * @return
	 */
	public List<Region> findListByQ(String q);
	
}
