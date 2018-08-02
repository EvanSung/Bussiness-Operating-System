package com.hafele.bos.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
/**
 * <p>Title: PageBean</p>  
 * <p>Description: 封装分页属性</p>  
 * @author Dragon.Wen
 * @date Jul 31, 2018
 */
public class PageBean {
	/**
	 * 当前页码
	 */
	private int currentPage;
	/**
	 * 每页显示的记录数
	 */
	private int pageSize;
	/**
	 * 查询条件
	 */
	private DetachedCriteria detachedCriteria;
	/**
	 * 总记录数
	 */
	private int total;
	/**
	 * 当前页需要展示的数据集合
	 */
	@SuppressWarnings("rawtypes")
	private List rows;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}
	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
}
