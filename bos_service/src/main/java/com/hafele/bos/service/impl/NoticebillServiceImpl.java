package com.hafele.bos.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hafele.bos.dao.IDecidedzoneDao;
import com.hafele.bos.dao.INoticebillDao;
import com.hafele.bos.dao.IWorkbillDao;
import com.hafele.bos.domain.Decidedzone;
import com.hafele.bos.domain.Noticebill;
import com.hafele.bos.domain.Staff;
import com.hafele.bos.domain.User;
import com.hafele.bos.domain.Workbill;
import com.hafele.bos.service.INoticebillService;
import com.hafele.bos.utils.BOSUtils;
import com.hafele.crm.ICustomerService;
/**
 * <p>Title: NoticebillServiceImpl</p>  
 * <p>Description: 业务通知单管理Service接口实现</p>  
 * @author Dragon.Wen
 * @date Aug 16, 2018
 */
@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService {
	
	@Autowired
	private INoticebillDao noticebillDao;
	
	@Autowired
	private IDecidedzoneDao decidedzoneDao;
	
	@Autowired
	private IWorkbillDao workbillDao;
	
	@Autowired
	private ICustomerService customerServiceProxy;

	@Override
	public void save(Noticebill model) {
		User user = BOSUtils.getLoginUser();
		model.setUser(user);//设置当前登录用户
		noticebillDao.save(model);
		
		//获取客户取件地址
		String pickaddress = model.getPickaddress();
		//远程调用crm服务，根据取件地址查询定区id
		String decidedzoneId = customerServiceProxy.findDecidedzoneByAddress(pickaddress);
		if(decidedzoneId != null) {
			//查询到了定区id，可以完成自动分单
			Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);
			Staff staff = decidedzone.getStaff();
			model.setStaff(staff);//业务通知单关联取派员对象
			//设置分单类型为：自动分单
			model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
			//为取派员产生一个工单
			Workbill workbill = new Workbill();
			workbill.setAttachbilltimes(0);//追单次数
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//创建时间，当前系统时间
			workbill.setNoticebill(model);//工单关联页面通知单
			workbill.setPickstate(Workbill.PICKSTATE_NO);//取件状态
			workbill.setRemark(model.getRemark());//备注信息
			workbill.setStaff(staff);//工单关联取派员
			workbill.setType(Workbill.TYPE_1);//工单类型
			workbillDao.save(workbill);
			//调用短信平台，发送短信(正在选择合适短信平台...)
		}else {
			//没有查询到定区id，不能完成自动分单
			model.setOrdertype(Noticebill.ORDERTYPE_MAN);
		}
	}
}
