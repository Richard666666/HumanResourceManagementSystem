/**  
 *
 * @Title:  MyConverter.java   
 * @Package com.ljl.human.utils   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��7�� ����9:23:42   
 * @version V1.0 
 * 
 */
package com.ljl.human.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

/**  
 *
 * @Title:  MyConverter.java   
 * @Package com.ljl.human.utils   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��7�� ����9:23:42   
 * @version V1.0 
 * 
 */
public class MyConverter implements Converter {
	//��ʼ����Ҫ��ʽ�����ݵ����ת����
	SimpleDateFormat[] fomat= {
			new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyy��MM��dd��"),
			new SimpleDateFormat("yyyy/MM/dd")
	};
	@Override
	public Object convert(Class clazz, Object val) {
		
		//������ת�����ַ����ĸ�ʽ
		String val1=(String) val;
		//�����ж�ת������Բ���
		if(clazz!=Date.class) {
			return null;
		}
		if(val==null||val1.equals("")) {
			return null;
		}
		for (int i = 0; i < fomat.length; i++) {
			try {
				return fomat[i].parse(val1);
			} catch (ParseException e) {
				continue;
			}
		}
		return null;
	}

}
