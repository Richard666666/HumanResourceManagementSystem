/**  
 *
 * @Title:  ObjectWrapperUtils.java   
 * @Package com.ljl.human.utils   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月9日 下午3:25:19   
 * @version V1.0 
 * 
 */
package com.ljl.human.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

/**  
 *	将表单请求的数据实现自动封装
 * @Title:  ObjectWrapperUtils.java   
 * @Package com.ljl.human.utils   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月9日 下午3:25:19   
 * @version V1.0 
 * 
 */
public class ObjectWrapperUtils {
	
	public static <T> T getObject(HttpServletRequest request,Class clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException{
		//步骤:获取要封装的对象
		T t=(T) clazz.newInstance();
		ConvertUtils.register( new MyConverter(),Date.class);
		BeanUtils.populate(t, request.getParameterMap());
		return t;
	}

}
