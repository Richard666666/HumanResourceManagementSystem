/**  
 *
 * @Title:  MyStringResultHandle.java   
 * @Package com.ljl.human.common   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月7日 下午5:45:30   
 * @version V1.0 
 * 
 */
package com.ljl.human.common;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;

/**  
 *这个需要好好理解
 * @Title:  MyStringResultHandle.java   
 * @Package com.ljl.human.common   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月7日 下午5:45:30   
 * @version V1.0 
 * 
 */
public class MyStringResultHandle<T>  implements ResultSetHandler<T>{
	@Override
	public T handle(ResultSet set) throws SQLException {
		set.next();  //表示的是游标向下移动一位
		String val=set.getString(1);
		return (T) val;
	}

}
