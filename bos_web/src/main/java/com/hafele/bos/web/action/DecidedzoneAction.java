package com.hafele.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hafele.bos.domain.Decidedzone;
import com.hafele.bos.service.IDecidedzoneService;
import com.hafele.bos.web.action.base.BaseAction;
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
	
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}

	@Autowired
	private IDecidedzoneService decidedzoneService;
	
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
		decidedzoneService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[] {"subareas","decidedzones"});
		return NONE;
	}
}
