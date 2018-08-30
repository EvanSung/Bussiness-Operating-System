package com.hafele.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hafele.bos.domain.Staff;
import com.hafele.bos.service.IStaffService;
import com.hafele.bos.web.action.base.BaseAction;
/**
 * <p>Title: StaffAction</p>  
 * <p>Description: 取派员管理Action</p>  
 * @author Dragon.Wen
 * @date Jul 30, 2018
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = 6693692769057172841L;
	
	@Autowired
	private IStaffService staffService;
	
	//接收取派员ids
	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * <p>Title: add</p>  
	 * <p>Description: 添加取派员</p>  
	 * @return
	 */
	public String add() {
		staffService.add(model);
		return LIST;
	}
	
	/**
	 * <p>Title: pageQuery</p>  
	 * <p>Description: </p>  
	 * @return
	 * @throws IOException
	 */
	public String pageQuery() throws IOException {
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		
		//姓名
		String name = model.getName();
		
		//电话
		String telephone = model.getTelephone();
		
		//单位
		String station = model.getStation();
		
		if(StringUtils.isNotBlank(name)) {
			detachedCriteria.add(Restrictions.like("name", "%"+name+"%"));
		}
		
		if(StringUtils.isNotBlank(telephone)) {
			detachedCriteria.add(Restrictions.like("telephone", "%"+telephone+"%"));
		}
		
		if(StringUtils.isNotBlank(station)) {
			detachedCriteria.add(Restrictions.like("station", "%"+station+"%"));
		}
		
		staffService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[] {"decidedzones","currentPage","pageSize","detachedCriteria"});
		return NONE;
	}
	
	/**
	 * <p>Title: edit</p>  
	 * <p>Description: 修改取派员信息</p>  
	 * @return
	 */
	public String edit() {
		//先查询数据库原始数据
		String id = model.getId();
		Staff staff = staffService.findById(id);
		
		//根据页面提交的参数进行覆盖，参数已经封装到model对象
		staff.setHaspda(model.getHaspda());
		staff.setName(model.getName());
		staff.setStandard(model.getStandard());
		staff.setStation(model.getStation());
		staff.setTelephone(model.getTelephone());
		
		staffService.update(staff);//提交事务
		return LIST;
	}
	
	
	/**
	 * <p>Title: restore</p>  
	 * <p>Description: 还原取派员信息方法</p>  
	 * @return
	 */
	public String restore() {
		staffService.restore(ids);
		return LIST;
	}
	
	/**
	 * <p>Title: delete</p>  
	 * <p>Description: 删除取派员方法</p>  
	 * @return
	 */
//	@RequiresPermissions("staff.delete")
	public String delete() {
		staffService.delete(ids);
		return LIST;
	}
	
	/**
	 * <p>Title: listajax</p>  
	 * <p>Description: 查询所有取派员，返回json数据</p>  
	 * @return
	 */
	public String listajax() {
		List<Staff> list = staffService.findListNotDelete();
		this.java2Json(list, new String[] {"decidedzones"});
		return NONE;
	}
}
