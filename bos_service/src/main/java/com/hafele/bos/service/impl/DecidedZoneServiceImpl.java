package com.hafele.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hafele.bos.dao.IDecidedZoneDao;
import com.hafele.bos.service.IDecidedZoneService;
/**
 * <p>Title: DecidedZoneServiceImpl</p>  
 * <p>Description: 管理定区、调度排班Service接口实现</p>  
 * @author Dragon.Wen
 * @date 2018年8月12日
 */

@Service
@Transactional
public class DecidedZoneServiceImpl implements IDecidedZoneService {
	
	@Autowired
	private IDecidedZoneDao decidedZoneDao;
}
