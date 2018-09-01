package com.hafele.crm.service;

import java.util.List;

import javax.jws.WebService;

import com.hafele.crm.domain.Customer;
/**
 * <p>Title: ICustomerService</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date Aug 14, 2018
 */
@WebService
public interface ICustomerService {
	/**
	 * <p>Title: findAll</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	public List<Customer> findAll();
	
	/**
	 * <p>Title: findListNotAssociation</p>  
	 * <p>Description: 查询未关联到定区的客户</p>  
	 * @return
	 */
	public List<Customer> findListNotAssociation();
	
	/**
	 * <p>Title: findListHasAssociation</p>  
	 * <p>Description: 查询已经关联到指定定区的客户</p>  
	 * @param decidedzoneId
	 * @return
	 */
	public List<Customer> findListHasAssociation(String decidedzoneId);
	
	/**
	 * <p>Title: assigncustomerstodecidedzone</p>  
	 * <p>Description: 定区关联客户</p>  
	 * @param decidedzoneId
	 * @param customerIds
	 */
	public void assigncustomerstodecidedzone(String decidedzoneId,Integer[] customerIds);
	
	/**
	 * <p>Title: findCustomerByTelephone</p>  
	 * <p>Description: 根据手机号查询客户信息</p>  
	 * @param telephone
	 * @return
	 */
	public Customer findCustomerByTelephone(String telephone);
	
	/**
	 * <p>Title: findDecidedzoneByAddress</p>  
	 * <p>Description: 根据取件地址查询定区ID</p>  
	 * @param address
	 * @return
	 */
	public String findDecidedzoneByAddress(String address);
}
