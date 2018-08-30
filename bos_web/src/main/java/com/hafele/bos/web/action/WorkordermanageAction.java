package com.hafele.bos.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hafele.bos.domain.Workordermanage;
import com.hafele.bos.service.IWorkordermanageService;
import com.hafele.bos.web.action.base.BaseAction;
/**
 * <p>Title: WorkordermanageAction</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date Aug 16, 2018
 */
@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = 4581596270454602544L;
	
	@Autowired
	private IWorkordermanageService workordermanageService;
	
	/**
	 * <p>Title: add</p>  
	 * <p>Description: 添加工作单</p>  
	 * @return
	 * @throws IOException 
	 */
	public String add() throws IOException {
		String f = "1";
		try{
			workordermanageService.save(model);
		}catch(Exception e){
			e.printStackTrace();
			f = "0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(f);
		return NONE;
	}
}
