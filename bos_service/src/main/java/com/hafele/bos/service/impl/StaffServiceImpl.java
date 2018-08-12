package com.hafele.bos.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hafele.bos.dao.IStaffDao;
import com.hafele.bos.domain.Staff;
import com.hafele.bos.service.IStaffService;
import com.hafele.bos.utils.PageBean;
/**
 * <p>Title: StaffServiceImpl</p>  
 * <p>Description: 取派员管理Service接口实现</p>  
 * @author Dragon.Wen
 * @date Jul 30, 2018
 */
@Service
@Transactional
public class StaffServiceImpl implements IStaffService {
	
	@Autowired
	private IStaffDao staffDao;
	
	@Override
	public void add(Staff model) {
		staffDao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
	}

	@Override
	public void delete(String ids) {
		if(StringUtils.isNotBlank(ids)) {
			String[] staffIds = ids.split(",");
			for(String id : staffIds) {
				staffDao.executeUpdate("staff.delete",id);
			}
		}
	}

	@Override
	public Staff findById(String id) {
		return staffDao.findById(id);
	}

	@Override
	public void update(Staff staff) {
		staffDao.update(staff);
	}

	@Override
	public void restore(String ids) {
		if(StringUtils.isNotBlank(ids)) {
			String[] staffIds = ids.split(",");
			for(String id : staffIds) {
				staffDao.executeUpdate("staff.restore", id);
			}
		}
	}

	@Override
	public List<Staff> findListNotDelete() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		//添加过滤条件，未删除的
		detachedCriteria.add(Restrictions.ne("deltag", "1"));
		return staffDao.findByCriteria(detachedCriteria);
	}

}
