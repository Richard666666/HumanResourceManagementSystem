
package com.ljl.human.utils;
import java.util.Properties;
import java.io.IOException;



/**  
 * �÷��������ɶ���
 * @Title:  ObjectUtils.java   
 * @Package com.ljl.human.utils   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��8�� ����8:55:38   
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
		//��һ��:��ȡ���ǵ���·��(����Ӧ���ǵ�ֵ-�������·��)
		String classpath=properties.getProperty(name);
		
		//�ڶ���:ͨ�����䴴�����ǵĶ���
		Class classObj=Class.forName(classpath);
		
		return classObj.newInstance();
	}
	
	
	
	
	

}
