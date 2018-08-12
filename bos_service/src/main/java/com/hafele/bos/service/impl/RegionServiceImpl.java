package com.hafele.bos.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hafele.bos.dao.IRegionDao;
import com.hafele.bos.domain.Region;
import com.hafele.bos.service.IRegionService;
import com.hafele.bos.utils.PageBean;
/**
 * <p>Title: RegionServiceImpl</p>  
 * <p>Description: 区域设置Service层接口实现</p>  
 * @author Dragon.Wen
 * @date Aug 1, 2018
 */
@Service
@Transactional
public class RegionServiceImpl implements IRegionService {

	@Autowired
	private IRegionDao regionDao;
	
	@Override
	public void saveBatch(List<Region> regionList) {
		for (Region region : regionList) {
			regionDao.saveOrUpdate(region);
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		regionDao.pageQuery(pageBean);
	}

	@Override
	public List<Region> findAll() {
		return regionDao.findAll();
	}

	@Override
	public List<Region> findListByQ(String q) {
		return regionDao.findListByQ(q);
	}

	@Override
	public void delete(String ids) {
		if(StringUtils.isNotBlank(ids)) {
			String[] regionIds = ids.split(",");
			for(String id : regionIds) {
				regionDao.executeUpdate("region.delete", id);
			}
		}
	}

	@Override
	public void add(Region model) {
		regionDao.save(model);
	}

	@Override
	public Region findById(String id) {
		return regionDao.findById(id);
	}

	@Override
	public void update(Region region) {
		regionDao.update(region);
	}

}
