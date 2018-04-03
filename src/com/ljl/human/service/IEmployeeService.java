
package com.ljl.human.service;

import java.sql.SQLException;
import java.util.List;

import com.ljl.human.pojo.Employee;

/**  
 *
 * @Title:  IEmployeeService.java   
 * @Package com.ljl.human.service   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月8日 下午8:26:41   
 * @version V1.0 
 * 
 */
public interface IEmployeeService {
	/**
	 * @throws SQLException 
	 * 增加员工---------------增
	 * @Title: insertUser   
	 * @Description: TODO  
	 * @param: @param user      
	 * @return: void      
	 * @throws
	 */
	void insertEmployee(Employee employee) throws SQLException;
		
	/**
	 * @throws SQLException 
	 * 通过id来删除员工----------删
	 * @Title: deleteEmployeeById   
	 * @Description: TODO  
	 * @param: @param employee      
	 * @return: void      
	 * @throws
	 */
	void deleteEmployeeById(Employee employee) throws SQLException;
		
	/**
	 * @throws SQLException 
	 * 通过id来更改员工---------改
	 * @Title: updateEmployeeById   
	 * @Description: TODO  
	 * @param: @param employee      
	 * @return: void      
	 * @throws
	 */
	void updateEmployeeById(Employee employee) throws SQLException;
	
	/**
	 * @throws SQLException 
	 * 通过组合条件查找员工--------查
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
	 * 查找出所有员工-----------查
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
