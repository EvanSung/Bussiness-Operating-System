package com.hafele.bos.web.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hafele.bos.domain.Decidedzone;
import com.hafele.bos.domain.Staff;
import com.hafele.bos.service.IDecidedzoneService;
import com.hafele.bos.web.action.base.BaseAction;
import com.hafele.crm.Customer;
import com.hafele.crm.ICustomerService;
/**
 * <p>Title: DecidedZoneAction</p>  
 * <p>Description: 管理定区、调度排班Action</p>  
 * @author Dragon.Wen
 * @date 2018年8月12日
 */

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = -1400633276853740076L;

	//接收页面提交的分区ID
	private String[] subareaid;
	
	//属性驱动，接收页面提交的多个客户id
	private List<Integer> customerIds;
	
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}
	
	public List<Integer> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}

	@Autowired
	private IDecidedzoneService decidedzoneService;
	
	//注入crm代理对象
	@Autowired
	private ICustomerService customerServiceProxy;
	
	/**
	 * <p>Title: add</p>  
	 * <p>Description: 添加定区</p>  
	 * @return
	 */
	public String add() {
		decidedzoneService.add(model,subareaid);
		return LIST;
	}
	
	/**
	 * <p>Title: pageQuery</p>  
	 * <p>Description: 管理定区分页查询</p>  
	 * @return
	 */
	public String pageQuery() {
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		
		String id = model.getId();
		if(StringUtils.isNoneBlank(id)) {
			detachedCriteria.add(Restrictions.like("id", "%"+id+"%"));
		}
		
		Staff staff = model.getStaff();
		
		if(staff != null) {
			//使用框架提供的别名方式实现多表关联查询
			detachedCriteria.createAlias("staff", "s");
			String station = staff.getStation();
			if(StringUtils.isNotBlank(station)) {
				detachedCriteria.add(Restrictions.like("s.station", "%"+station+"%"));
			}
		}
		
		decidedzoneService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[] {"subareas","decidedzones"});
		return NONE;
	}
	
	/**
	 * <p>Title: findListNotAssociation</p>  
	 * <p>Description: 远程调用crm服务，获取未关联到定区的客户</p>  
	 * @return
	 */
	public String findListNotAssociation(){
		List<Customer> list = customerServiceProxy.findListNotAssociation();
		this.java2Json(list, new String[]{});
		return NONE;
	}
	
	/**
	 * <p>Title: findListHasAssociation</p>  
	 * <p>Description: 远程调用crm服务，获取已经关联到指定的定区的客户</p>  
	 * @return
	 */
	public String findListHasAssociation(){
		String id = model.getId();
		List<Customer> list = customerServiceProxy.findListHasAssociation(id);
		this.java2Json(list, new String[]{});
		return NONE;
	}
	
	/**
	 * <p>Title: assigncustomerstodecidedzone</p>  
	 * <p>Description: 远程调用crm服务，将客户关联到定区</p>  
	 * @return
	 */
	public String assigncustomerstodecidedzone(){
		customerServiceProxy.assigncustomerstodecidedzone(model.getId(), customerIds);
		return LIST;
	}
}
