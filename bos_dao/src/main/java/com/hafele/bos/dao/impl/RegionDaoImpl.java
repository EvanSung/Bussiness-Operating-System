package com.hafele.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hafele.bos.dao.IRegionDao;
import com.hafele.bos.dao.base.impl.BaseDaoImpl;
import com.hafele.bos.domain.Region;
/**
 * <p>Title: RegionDaoImpl</p>  
 * <p>Description: 区域设置Dao接口实现</p>  
 * @author Dragon.Wen
 * @date Aug 1, 2018
 */
@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {

	@Override
	public List<Region> findListByQ(String q) {
		String hql = "FROM Region r WHERE r.shortcode LIKE ? "
				+ "	OR r.citycode LIKE ? OR r.province LIKE ? "
				+ "OR r.city LIKE ? OR r.district LIKE ?";
		List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql, "%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
		return list;
	}

}
