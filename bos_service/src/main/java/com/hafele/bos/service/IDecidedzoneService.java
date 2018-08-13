package com.hafele.bos.service;

import com.hafele.bos.domain.Decidedzone;
import com.hafele.bos.utils.PageBean;

/**
 * <p>Title: IDecidedZoneService</p>  
 * <p>Description: 管理定区、调度排班Service接口</p>  
 * @author Dragon.Wen
 * @date 2018年8月12日
 */
public interface IDecidedzoneService {

	/**
	 * <p>Title: add</p>  
	 * <p>Description: 添加定区、同时关联分区</p>  
	 * @param model
	 * @param subareaid
	 */
	public void add(Decidedzone model, String[] subareaid);

	/**
	 * <p>Title: pageQuery</p>  
	 * <p>Description: 定区分页查询</p>  
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

}
