/**  
 *
 * @Title:  MyStringResultHandle.java   
 * @Package com.ljl.human.common   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��7�� ����5:45:30   
 * @version V1.0 
 * 
 */
package com.ljl.human.common;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;

/**  
 *�����Ҫ�ú����
 * @Title:  MyStringResultHandle.java   
 * @Package com.ljl.human.common   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��7�� ����5:45:30   
 * @version V1.0 
 * 
 */
public class MyStringResultHandle<T>  implements ResultSetHandler<T>{
	@Override
	public T handle(ResultSet set) throws SQLException {
		set.next();  //��ʾ�����α������ƶ�һλ
		String val=set.getString(1);
		return (T) val;
	}

}
