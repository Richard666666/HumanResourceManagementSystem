
package com.ljl.human.service;

import java.sql.SQLException;
import java.util.List;

import com.ljl.human.pojo.Department;
import com.ljl.human.pojo.User;

/**  
 *
 * @Title:  IDepartmentService.java   
 * @Package com.ljl.human.service   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��8�� ����8:25:44   
 * @version V1.0 
 * 
 */
public interface IDepartmentService {
	/**
	 * @throws SQLException 
	 * ���Ӳ���-----------------------��
	 * @Title: insertUser   
	 * @Description: TODO  
	 * @param: @param user      
	 * @return: void      
	 * @throws
	 */
	void insertDepartment(Department department) throws SQLException;
	
	/**
	 * @throws SQLException 
	 * ͨ��idɾ������------------------ɾ
	 * @Title: deleteDepartmentById   
	 * @Description: TODO  
	 * @param: @param department      
	 * @return: void      
	 * @throws
	 */
	void deleteDepartmentById(Department department) throws SQLException;
	/**
	 * @throws SQLException 
	 * ͨ��id���Ĳ���-----------------��
	 * @Title: updateDepartmentById   
	 * @Description: TODO  
	 * @param: @param department      
	 * @return: void      
	 * @throws
	 */
	void updateDepartmentById(Department department) throws SQLException;
	
	/**
	 * @throws SQLException 
	 * ��ѯ����---------------------��
	 * @Title: findDepartmentAll   
	 * @Description: TODO  
	 * @param: @return      
	 * @return: List<Department>      
	 * @throws
	 */
	List<Department> findDepartmentAll() throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: findNameById   
	 * @Description: TODO  
	 * @param: @param dId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	String findNameById(int dId) throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: findDepartmentById   
	 * @Description: TODO  
	 * @param: @param department
	 * @param: @return      
	 * @return: User      
	 * @throws   
	 */  
	Department findDepartmentById(Department department) throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: findDepartmentByName   
	 * @Description: TODO  
	 * @param: @param department
	 * @param: @return      
	 * @return: List<User>      
	 * @throws   
	 */  
	List<Department> findDepartmentByName(Department department) throws SQLException;

}
