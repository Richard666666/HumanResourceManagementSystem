/**  
 *
 * @Title:  MyConverter.java   
 * @Package com.ljl.human.utils   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月7日 下午9:23:42   
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
 * @author: 李建良  
 * @date:   2018年1月7日 下午9:23:42   
 * @version V1.0 
 * 
 */
public class MyConverter implements Converter {
	//初始化需要格式化数据的这个转换类
	SimpleDateFormat[] fomat= {
			new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyy年MM月dd日"),
			new SimpleDateFormat("yyyy/MM/dd")
	};
	@Override
	public Object convert(Class clazz, Object val) {
		
		//将数据转换成字符串的格式
		String val1=(String) val;
		//首先判断转换的类对不对
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
