package com.hafele.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hafele.bos.domain.Decidedzone;
import com.hafele.bos.service.IDecidedZoneService;
import com.hafele.bos.web.action.base.BaseAction;
/**
 * <p>Title: DecidedZoneAction</p>  
 * <p>Description: 管理定区、调度排班Action</p>  
 * @author Dragon.Wen
 * @date 2018年8月12日
 */

@Controller
@Scope("protoType")
public class DecidedZoneAction extends BaseAction<Decidedzone> {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = -1400633276853740076L;

	@Autowired
	private IDecidedZoneService decidedZoneService;
	
}
