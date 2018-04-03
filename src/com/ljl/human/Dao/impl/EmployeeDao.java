/**  
 *
 * @Title:  EmployeeDao.java   
 * @Package com.ljl.human.Dao.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月7日 下午8:51:06   
 * @version V1.0 
 * 
 */
package com.ljl.human.Dao.impl;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ljl.human.Dao.IEmployeeDao;
import com.ljl.human.pojo.Department;
import com.ljl.human.pojo.Employee;
import com.ljl.human.utils.JdbcUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**  
 *
 * @Title:  EmployeeDao.java   
 * @Package com.ljl.human.Dao.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月7日 下午8:51:06   
 * @version V1.0 
 * 
 */
public class EmployeeDao implements IEmployeeDao {

	/**   
	 * <p>Title: insertEmployee</p>   
	 * <p>Description: </p>   
	 * @param employee   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IEmployeeDao#insertEmployee(com.ljl.human.pojo.Employee)   
	 */  
	@Override
	public void insertEmployee(Employee employee) throws SQLException {
		String sql="insert into t_emp(eName,eGender,eTelNum,eEMail,jId,eStu,dId,eIdCard,eCreateTime,eAddress) values(?,?,?,?,?,?,?,?,?,?)";
		JdbcUtils.getQueryRunner().update(sql,employee.geteName(),employee.geteGender(),employee.geteTelNum(),employee.geteEmail(),employee.getjId(),employee.geteEmail(),employee.getjId(),employee.geteStu(),employee.getdId(),employee.geteIdCard(),employee.geteCreateTime(),employee.geteAddress());
	}

	/**   
	 * <p>Title: deleteEmployeeById</p>   
	 * <p>Description: </p>   
	 * @param employee   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IEmployeeDao#deleteEmployeeById(com.ljl.human.pojo.Employee)   
	 */  
	@Override
	public void deleteEmployeeById(Employee employee) throws SQLException {
		String sql="delete from t_emp where eId=?";
		JdbcUtils.getQueryRunner().update(sql,employee.geteId());
	}

	/**   
	 * <p>Title: updateEmployeeById</p>   
	 * <p>Description: </p>   
	 * @param employee   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IEmployeeDao#updateEmployeeById(com.ljl.human.pojo.Employee)   
	 */  
	@Override
	public void updateEmployeeById(Employee employee) throws SQLException {
		String sql="update t_emp set eName=?,eGender=?,eTelNum=?,eEMail=?,jId=?,eStu=?,dId=?,eIdCard=?,eCreateTime=?,eAddresss=? where eId=? ";
		JdbcUtils.getQueryRunner().update(sql,employee.geteName(),employee.geteGender(),employee.geteTelNum(),employee.geteEmail(),employee.getjId(),employee.geteEmail(),employee.getjId(),employee.geteStu(),employee.getdId(),employee.geteIdCard(),employee.geteCreateTime(),employee.geteAddress(),employee.geteId());
		
	}

	/**   
	 * <p>Title: findEmployeeByCompositionCondition</p>   
	 * <p>Description: </p>   
	 * @param employee
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IEmployeeDao#findEmployeeByCompositionCondition(com.ljl.human.pojo.Employee)   
	 */  
	@Override
	public List<Employee> findEmployeeByCompositionCondition(Employee employee) throws SQLException {
		StringBuilder sql=new StringBuilder("select * from t_emp where 1=1");
		if(employee.getjId()!=null){
			sql.append("and jId='"+employee.getjId()+"'");
		}
		else if(employee.geteName()!=null&&!(employee.geteName().equals(""))){
			sql.append("and eName='"+employee.geteName()+"'");
		}
		else if(employee.geteIdCard()!=null&&!(employee.geteName().equals(""))){
			sql.append("and eIdCard='"+employee.geteName()+"'");
		}
		else if(employee.geteGender()!=null&&!(employee.geteGender().equals(""))){
			sql.append("and eGender='"+employee.geteGender()+"'");
		}
		else if(employee.geteTelNum()!=null&&!(employee.geteTelNum().equals(""))){
			 sql.append("and eTelNum='"+employee.geteTelNum()+"'");
		}
		else if(employee.getdId()!=null&&!(employee.getdId().equals(""))){
			 sql.append("and dId='"+employee.getdId()+"'");
		}
		return JdbcUtils.getQueryRunner().query(sql.toString(),new BeanListHandler<Employee>(Employee.class));
	}

	/**   
	 * <p>Title: findEmployeeAll</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IEmployeeDao#findEmployeeAll()   
	 */  
	@Override
	public List<Employee> findEmployeeAll() throws SQLException {
		String sql="select * from t_emp";
		List<Employee> list = JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Employee>(Employee.class));
		System.out.println(list);
		return 	list;
	}

	/**   
	 * <p>Title: findEmployeeById</p>   
	 * <p>Description: </p>   
	 * @param employee
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IEmployeeDao#findEmployeeById(com.ljl.human.pojo.Employee)   
	 */  
	@Override
	public Employee findEmployeeById(Employee employee) throws SQLException {
		String sql="select * from t_emp where eId=?";
		return JdbcUtils.getQueryRunner().query(sql,new BeanHandler<Employee>(Employee.class),employee.getdId());
	}

}
