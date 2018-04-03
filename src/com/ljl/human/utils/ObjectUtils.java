
package com.ljl.human.utils;
import java.util.Properties;
import java.io.IOException;



/**  
 * 用反射来生成对象
 * @Title:  ObjectUtils.java   
 * @Package com.ljl.human.utils   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月8日 下午8:55:38   
 * @version V1.0 
 * 
 */
public class ObjectUtils {
	private static Properties properties=null;
	static{
		properties=new Properties();
		try{
			properties.load(ObjectUtils.class.getClassLoader().getResourceAsStream("object.properties"));
		}catch (Exception e) {
			
		}
	}
	public  static Object getObject(String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		//第一步:获取我们的类路径(键对应我们的值-我们类的路径)
		String classpath=properties.getProperty(name);
		
		//第二步:通过反射创建我们的对象
		Class classObj=Class.forName(classpath);
		
		return classObj.newInstance();
	}
	
	
	
	
	

}
