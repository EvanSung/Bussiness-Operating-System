package com.hafele.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hafele.bos.dao.IWorkordermanageDao;
import com.hafele.bos.domain.Workordermanage;
import com.hafele.bos.service.IWorkordermanageService;
/**
 * <p>Title: WorkordermanageServiceImpl</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date Aug 16, 2018
 */
@Service
@Transactional
public class WorkordermanageServiceImpl implements IWorkordermanageService {

	@Autowired
	private IWorkordermanageDao workordermanageDao;
	
	@Override
	public void save(Workordermanage model) {
		workordermanageDao.save(model);
	}

}
