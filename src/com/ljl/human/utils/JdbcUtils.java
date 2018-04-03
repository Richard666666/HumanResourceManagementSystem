/**  
 *
 * @Title:  JdbcUtils.java   
 * @Package com.ljl.human.utils   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月8日 上午9:45:15   
 * @version V1.0 
 * 
 */
package com.ljl.human.utils;



import java.beans.PropertyVetoException;

import org.apache.commons.dbutils.QueryRunner;


import com.mchange.v2.c3p0.ComboPooledDataSource;

/**  
 * 通过常量池的方法获得连接
 * @Title:  JdbcUtils.java   
 * @Package com.ljl.human.utils   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月8日 上午9:45:15   
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
			throw new RuntimeException("加载驱动程序失败....");
		}
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3308/human");
		dataSource.setUser("root");
		dataSource.setPassword("123456");
	}
		
	public static QueryRunner getQueryRunner() {
		return new QueryRunner(dataSource);
	}
	

}
