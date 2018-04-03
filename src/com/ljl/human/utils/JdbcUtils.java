/**  
 *
 * @Title:  JdbcUtils.java   
 * @Package com.ljl.human.utils   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��8�� ����9:45:15   
 * @version V1.0 
 * 
 */
package com.ljl.human.utils;



import java.beans.PropertyVetoException;

import org.apache.commons.dbutils.QueryRunner;


import com.mchange.v2.c3p0.ComboPooledDataSource;

/**  
 * ͨ�������صķ����������
 * @Title:  JdbcUtils.java   
 * @Package com.ljl.human.utils   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��8�� ����9:45:15   
 * @version V1.0 
 * 
 */
public class JdbcUtils {
	
	private static ComboPooledDataSource dataSource=null;
	static {
		dataSource=new ComboPooledDataSource();
		
		try {
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			throw new RuntimeException("������������ʧ��....");
		}
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3308/human");
		dataSource.setUser("root");
		dataSource.setPassword("123456");
	}
		
	public static QueryRunner getQueryRunner() {
		return new QueryRunner(dataSource);
	}
	

}
