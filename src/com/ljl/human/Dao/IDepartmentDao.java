/**  
 *
 * @Title:  IDepartmentDao.java   
 * @Package com.ljl.human.Dao   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月6日 下午8:49:20   
 * @version V1.0 
 * 
 */
package com.ljl.human.Dao;

import java.sql.SQLException;
import java.util.List;

import com.ljl.human.pojo.Department;
import com.ljl.human.pojo.User;

/**  
 *
 * @Title:  IDepartmentDao.java   
 * @Package com.ljl.human.Dao   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月6日 下午8:49:20   
 * @version V1.0 
 * 
 */
public interface IDepartmentDao {
	/**
	 * @throws SQLException 
	 * 增加部门-----------------------增
	 * @Title: insertUser   
	 * @Description: TODO  
	 * @param: @param user      
	 * @return: void      
	 * @throws
	 */
	void insertDepartment(Department department) throws SQLException;
	
	/**
	 * @throws SQLException 
	 * 通过id删除部门------------------删
	 * @Title: deleteDepartmentById   
	 * @Description: TODO  
	 * @param: @param department      
	 * @return: void      
	 * @throws
	 */
	void deleteDepartmentById(Department department) throws SQLException;
	/**
	 * @throws SQLException 
	 * 通过id更改部门-----------------改
	 * @Title: updateDepartmentById   
	 * @Description: TODO  
	 * @param: @param department      
	 * @return: void      
	 * @throws
	 */
	void updateDepartmentById(Department department) throws SQLException;
	
	/**
	 * @throws SQLException 
	 * 查询部门---------------------查
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
	 * @return: Department      
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
