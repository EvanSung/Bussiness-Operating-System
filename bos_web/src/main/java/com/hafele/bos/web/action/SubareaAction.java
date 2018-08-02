package com.hafele.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hafele.bos.domain.Subarea;
import com.hafele.bos.service.ISubareaService;
import com.hafele.bos.web.action.base.BaseAction;
/**
 * <p>Title: SubareaAction</p>  
 * <p>Description: 管理分区Action</p>  
 * @author Dragon.Wen
 * @date Aug 2, 2018
 */
@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = -7251431507689259093L;
	
	@Autowired
	private ISubareaService subareaService;
	
	/**
	 * <p>Title: add</p>  
	 * <p>Description: 添加分区</p>  
	 * @return
	 */
	public String add() {
		subareaService.add(model);
		return LIST;
	}
	
	public String pageQuery() {
		
		return NONE;
	}
}
