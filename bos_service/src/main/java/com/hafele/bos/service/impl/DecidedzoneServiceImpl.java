package com.hafele.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hafele.bos.dao.IDecidedzoneDao;
import com.hafele.bos.dao.ISubareaDao;
import com.hafele.bos.domain.Decidedzone;
import com.hafele.bos.domain.Subarea;
import com.hafele.bos.service.IDecidedzoneService;
import com.hafele.bos.utils.PageBean;
/**
 * <p>Title: DecidedZoneServiceImpl</p>  
 * <p>Description: 管理定区、调度排班Service接口实现</p>  
 * @author Dragon.Wen
 * @date 2018年8月12日
 */

@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {
	
	@Autowired
	private IDecidedzoneDao decidedZoneDao;
	
	@Autowired
	private ISubareaDao subareaDao;

	@Override
	public void add(Decidedzone model, String[] subareaid) {
		decidedZoneDao.save(model);
		for(String id : subareaid) {
			Subarea subarea = subareaDao.findById(id);
			subarea.setDecidedzone(model);//分区关联定区
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		decidedZoneDao.pageQuery(pageBean);
	}
}
