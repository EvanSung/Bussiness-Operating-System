package com.hafele.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hafele.bos.dao.ISubareaDao;
import com.hafele.bos.domain.Subarea;
import com.hafele.bos.service.ISubareaService;
import com.hafele.bos.utils.PageBean;
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

	@Override
	public void pageQuery(PageBean pageBean) {
		subareaDao.pageQuery(pageBean);
	}

	@Override
	public void delete(String ids) {
		if(StringUtils.isNotBlank(ids)) {
			String[] subareaIds = ids.split(",");
			for(String id : subareaIds) {
				subareaDao.executeUpdate("subarea.delete", id);
			}
		}
	}

	@Override
	public Subarea findById(String id) {
		return subareaDao.findById(id);
	}

	@Override
	public void update(Subarea subarea) {
		subareaDao.update(subarea);
	}

	@Override
	public List<Subarea> findAll() {
		return subareaDao.findAll();
	}

	@Override
	public List<Subarea> findListNotAssociation() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		//添加过滤条件，未分配到定区的分区
		detachedCriteria.add(Restrictions.isNull("decidedzone"));
		return subareaDao.findByCriteria(detachedCriteria);
	}

	@Override
	public List<Subarea> findListByDecidedzoneId(String decidedzoneId) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		detachedCriteria.add(Restrictions.eq("decidedzone.id", decidedzoneId));
		return subareaDao.findByCriteria(detachedCriteria);
	}

	@Override
	public void saveBatch(List<Subarea> subareaList) {
		for(Subarea subarea : subareaList) {
			subareaDao.saveOrUpdate(subarea);
		}
	}

}
