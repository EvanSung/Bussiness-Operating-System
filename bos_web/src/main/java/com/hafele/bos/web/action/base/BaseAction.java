package com.hafele.bos.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.hafele.bos.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
/**
 * 
 * <p>Title: BaseAction</p>  
 * <p>Description: 表现层通用实现类</p>  
 * @author Dragon.Wen
 * @date Jul 25, 2018
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = -6975806919723219027L;

	protected PageBean pageBean =  new PageBean();;
	
	DetachedCriteria detachedCriteria = null;
	
	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}

	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}
	
	public static final String HOME = "home";
	public static final String LIST = "list";
	public static final String EDIT = "edit";
	
	//声明模型对象
	protected T model;
	
	@Override
	public T getModel() {
		return model;
	}

	//在构造方法中动态获取实体的类型，通过反射创建模型对象
	public BaseAction() {
		//获得父类（BaseAction<T>）的类型
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		
		//获得父类上声明的泛型数组
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		
		//获得实体类
		Class<T> entityClass = (Class<T>) actualTypeArguments[0];
		
		detachedCriteria = DetachedCriteria.forClass(entityClass);
		pageBean.setDetachedCriteria(detachedCriteria);
		
		//通过反射创建模型对象
		try {
			model = entityClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 将指定Java对象转为json，并响应到客户端页面
	 * @param object
	 * @param exclueds
	 */
	public void java2Json(Object object ,String[] exclueds){
		JsonConfig jsonConfig = new JsonConfig();
		//指定哪些属性不需要转json
		jsonConfig.setExcludes(exclueds);
		String json = JSONObject.fromObject(object,jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将指定Java对象转为json，并响应到客户端页面
	 * @param list
	 * @param exclueds
	 */
	public void java2Json(List list ,String[] exclueds){
		JsonConfig jsonConfig = new JsonConfig();
		//指定哪些属性不需要转json
		jsonConfig.setExcludes(exclueds);
		String json = JSONArray.fromObject(list,jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
