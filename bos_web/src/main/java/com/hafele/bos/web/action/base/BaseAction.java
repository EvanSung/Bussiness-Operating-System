package com.hafele.bos.web.action.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
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

	public static final String HOME = "home";
	
	public static final String LOGIN = "login";
	
	//声明模型对象
	private T model;
	
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
		
		//通过反射创建模型对象
		try {
			model = entityClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
