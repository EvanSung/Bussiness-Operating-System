package com.hafele.bos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * <p>Title: Function</p>  
 * <p>Description: 权限实体</p>  
 * @author Dragon.Wen
 * @date Aug 17, 2018
 */

public class Function implements java.io.Serializable {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = -7112978224451223136L;
	// Fields

	private String id;
	private Function parentFunction;//当前权限的上级权限
	private String name;
	private String code;
	private String description;
	private String page;
	private String generatemenu;//是否生成菜单，1：是 0：否
	private Integer zindex;
	private String type;
	private Set roles = new HashSet(0);//当前权限对应的多个角色
	private Set children = new HashSet(0);//当前权限的下级权限
	
	//提供getpId方法，目的是当将权限对象转换为json数据时，其中加入一个pId字段
	public String getpId(){
		if(parentFunction == null){
			return "0";
		}
		return parentFunction.getId();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Function getParentFunction() {
		return parentFunction;
	}
	public void setParentFunction(Function parentFunction) {
		this.parentFunction = parentFunction;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getGeneratemenu() {
		return generatemenu;
	}
	public void setGeneratemenu(String generatemenu) {
		this.generatemenu = generatemenu;
	}
	public Integer getZindex() {
		return zindex;
	}
	public void setZindex(Integer zindex) {
		this.zindex = zindex;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set getRoles() {
		return roles;
	}
	public void setRoles(Set roles) {
		this.roles = roles;
	}
	public Set getChildren() {
		return children;
	}
	public void setChildren(Set children) {
		this.children = children;
	}
}