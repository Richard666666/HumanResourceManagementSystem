/**  
 *
 * @Title:  DepartmentDao.java   
 * @Package com.ljl.human.Dao.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月8日 上午9:32:52   
 * @version V1.0 
 * 
 */
package com.ljl.human.Dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ljl.human.Dao.IDepartmentDao;
import com.ljl.human.common.MyStringResultHandle;
import com.ljl.human.pojo.Department;
import com.ljl.human.pojo.User;
import com.ljl.human.utils.JdbcUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**  
 *
 * @Title:  DepartmentDao.java   
 * @Package com.ljl.human.Dao.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月8日 上午9:32:52   
 * @version V1.0 
 * 
 */
public class DepartmentDao implements IDepartmentDao {

	/**   
	 * <p>Title: insertDepartment</p>   
	 * <p>Description: </p>   
	 * @param department   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IDepartmentDao#insertDepartment(com.ljl.human.pojo.Department)   
	 */  
	@Override
	public void insertDepartment(Department department) throws SQLException {
		String sql="insert into t_dept(dName,dDes) values(?,?)";
		JdbcUtils.getQueryRunner().update(sql,department.getdName(),department.getdDes());
		
	}

	/**   
	 * <p>Title: deleteDepartmentById</p>   
	 * <p>Description: </p>   
	 * @param department   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IDepartmentDao#deleteDepartmentById(com.ljl.human.pojo.Department)   
	 */  
	@Override
	public void deleteDepartmentById(Department department) throws SQLException {
		String sql="delete from t_dept where dId=?";
		JdbcUtils.getQueryRunner().update(sql, department.getdId());
		
	}

	/**   
	 * <p>Title: updateDepartmentById</p>   
	 * <p>Description: </p>   
	 * @param department   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IDepartmentDao#updateDepartmentById(com.ljl.human.pojo.Department)   
	 */  
	@Override
	public void updateDepartmentById(Department department) throws SQLException {
		String sql="update t_dept set dName=?,dDes=? where dId=?";
		JdbcUtils.getQueryRunner().update(sql, department.getdName(),department.getdDes(),department.getdId());
		
	}

	/**   
	 * <p>Title: findDepartmentAll</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IDepartmentDao#findDepartmentAll()   
	 */  
	@Override
	public List<Department> findDepartmentAll() throws SQLException {
		String sql="select * from t_dept";
		return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Department>(Department.class));
	}

	/**   
	 * <p>Title: findNameById</p>   
	 * <p>Description: </p>   
	 * @param dId
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IDepartmentDao#findNameById(int)   
	 */  
	@Override
	public String findNameById(int dId) throws SQLException {
		String sql="select dName from t_dept where dId=?";
		return (String)JdbcUtils.getQueryRunner().query(sql,new MyStringResultHandle<String>(),dId);
	}

	/**   
	 * <p>Title: findUserById</p>   
	 * <p>Description: </p>   
	 * @param department
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IDepartmentDao#findUserById(com.ljl.human.pojo.Department)   
	 */  
	@Override
	public Department findDepartmentById(Department department) throws SQLException {
		String sql="select * from t_dept where dId=?";
		return JdbcUtils.getQueryRunner().query(sql,new BeanHandler<Department>(Department.class),department.getdId());
	}

	/**   
	 * <p>Title: findDepartmentByName</p>   
	 * <p>Description: </p>   
	 * @param department
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IDepartmentDao#findDepartmentByName(com.ljl.human.pojo.Department)   
	 */  
	@Override
	public List<Department> findDepartmentByName(Department department) throws SQLException {
		StringBuilder sql=new StringBuilder("select * from t_dept where 1=1");
		if(department.getdName()!=null&&!(department.getdName().equals(""))){
			sql.append(" and dName='"+department.getdName()+"'");
		}
	
		return JdbcUtils.getQueryRunner().query(sql.toString(),new BeanListHandler<Department>(Department.class));
	}


	
	

}
