package com.hafele.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hafele.bos.domain.Noticebill;
import com.hafele.bos.service.INoticebillService;
import com.hafele.bos.web.action.base.BaseAction;
import com.hafele.crm.Customer;
import com.hafele.crm.ICustomerService;

/**
 * <p>Title: NoticebillAction</p>  
 * <p>Description: 业务通知单管理Action</p>  
 * @author Dragon.Wen
 * @date Aug 16, 2018
 */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = 76499374672299285L;

	@Autowired
	private INoticebillService noticebillService;
	
	@Autowired
	private ICustomerService customerServiceProxy;
	
	/**
	 * <p>Title: findCustomerByTelephone</p>  
	 * <p>Description: 调用crm服务,根据手机号码查询客户信息</p>  
	 * @return
	 */
	public String findCustomerByTelephone() {
		String telephone = model.getTelephone();
		Customer customer = customerServiceProxy.findCustomerByTelephone(telephone);
		this.java2Json(customer, new String[] {});
		return NONE;
	} 
	
	/**
	 * <p>Title: add</p>  
	 * <p>Description: 保存业务通知单，尝试自动分单</p>  
	 * @return
	 */
	public String add() {
		noticebillService.save(model);
		return "noticebill_add";
	}
}
