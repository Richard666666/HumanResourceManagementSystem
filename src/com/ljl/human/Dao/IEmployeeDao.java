/**  
 *
 * @Title:  IEmployeeDao.java   
 * @Package com.ljl.human.Dao   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��6�� ����8:51:08   
 * @version V1.0 
 * 
 */
package com.ljl.human.Dao;

import java.sql.SQLException;
import java.util.List;

import com.ljl.human.pojo.Employee;
import com.ljl.human.pojo.User;

/**  
 *
 * @Title:  IEmployeeDao.java   
 * @Package com.ljl.human.Dao   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��6�� ����8:51:08   
 * @version V1.0 
 * 
 */
public interface IEmployeeDao {
	/**
	 * @throws SQLException 
	 * ����Ա��---------------��
	 * @Title: insertUser   
	 * @Description: TODO  
	 * @param: @param user      
	 * @return: void      
	 * @throws
	 */
	void insertEmployee(Employee employee) throws SQLException;
		
	/**
	 * @throws SQLException 
	 * ͨ��id��ɾ��Ա��----------ɾ
	 * @Title: deleteEmployeeById   
	 * @Description: TODO  
	 * @param: @param employee      
	 * @return: void      
	 * @throws
	 */
	void deleteEmployeeById(Employee employee) throws SQLException;
		
	/**
	 * @throws SQLException 
	 * ͨ��id������Ա��---------��
	 * @Title: updateEmployeeById   
	 * @Description: TODO  
	 * @param: @param employee      
	 * @return: void      
	 * @throws
	 */
	void updateEmployeeById(Employee employee) throws SQLException;
	
	/**
	 * @throws SQLException 
	 * ͨ�������������Ա��--------��
	 * @Title: findEmployeeByCompositionCondition   
	 * @Description: TODO  
	 * @param: @param employee
	 * @param: @return      
	 * @return: List<Employee>      
	 * @throws
	 */
	List<Employee> findEmployeeByCompositionCondition(Employee employee) throws SQLException;
	
	
	/**
	 * @throws SQLException 
	 * ���ҳ�����Ա��-----------��
	 * @Title: findEmployeeAll   
	 * @Description: TODO  
	 * @param: @return      
	 * @return: List<Employee>      
	 * @throws
	 */
	List<Employee> findEmployeeAll() throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: findEmployeeById   
	 * @Description: TODO  
	 * @param: @param employee
	 * @param: @return      
	 * @return: Employee      
	 * @throws   
	 */  
	Employee findEmployeeById(Employee employee) throws SQLException;
	
}
