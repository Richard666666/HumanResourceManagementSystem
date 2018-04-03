/**  
 *
 * @Title:  ObjectWrapperUtils.java   
 * @Package com.ljl.human.utils   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��9�� ����3:25:19   
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
 *	�������������ʵ���Զ���װ
 * @Title:  ObjectWrapperUtils.java   
 * @Package com.ljl.human.utils   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��9�� ����3:25:19   
 * @version V1.0 
 * 
 */
public class ObjectWrapperUtils {
	
	public static <T> T getObject(HttpServletRequest request,Class clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException{
		//����:��ȡҪ��װ�Ķ���
		T t=(T) clazz.newInstance();
		ConvertUtils.register( new MyConverter(),Date.class);
		BeanUtils.populate(t, request.getParameterMap());
		return t;
	}

}
