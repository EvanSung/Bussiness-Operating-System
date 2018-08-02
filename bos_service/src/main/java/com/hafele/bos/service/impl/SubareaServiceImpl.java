package com.hafele.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hafele.bos.dao.ISubareaDao;
import com.hafele.bos.domain.Subarea;
import com.hafele.bos.service.ISubareaService;
/**
 * <p>Title: SubareaServiceImpl</p>  
 * <p>Description: 管理分区Service接口实现</p>  
 * @author Dragon.Wen
 * @date Aug 2, 2018
 */
@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {
	@Autowired
	private ISubareaDao subareaDao;

	@Override
	public void add(Subarea model) {
		subareaDao.save(model);
	}
}
