
package com.ljl.human.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ljl.human.Dao.IEmployeeDao;
import com.ljl.human.Dao.impl.EmployeeDao;
import com.ljl.human.pojo.Employee;
import com.ljl.human.service.IEmployeeService;
import com.ljl.human.utils.ObjectUtils;

import jdk.nashorn.internal.ir.ThrowNode;

/**  
 *
 * @Title:  EmployeeService.java   
 * @Package com.ljl.human.service.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月8日 下午8:38:12   
 * @version V1.0 
 * 
 */
public class EmployeeService implements IEmployeeService {
	private IEmployeeDao employeeDao=null;
	public EmployeeService() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		employeeDao=(IEmployeeDao) ObjectUtils.getObject("employeeDao");
	}

	/**   
	 * <p>Title: insertEmployee</p>   
	 * <p>Description: </p>   
	 * @param employee
	 * @throws SQLException   
	 * @see com.ljl.human.service.IEmployeeService#insertEmployee(com.ljl.human.pojo.Employee)   
	 */  
	@Override
	public void insertEmployee(Employee employee) throws SQLException {
		if(employee.geteAddress().equals("")||employee.geteCreateTime()==null||employee.geteEmail().equals("")||employee.geteGender()==null||employee.geteIdCard().equals("")||employee.geteName().equals("")||employee.geteStu().equals("")||employee.geteTelNum().equals("")||employee.getjId()==null){
			throw new RuntimeException("传入的参数不够");
		}
		employeeDao.insertEmployee(employee);
	}

	/**   
	 * <p>Title: deleteEmployeeById</p>   
	 * <p>Description: </p>   
	 * @param employee
	 * @throws SQLException   
	 * @see com.ljl.human.service.IEmployeeService#deleteEmployeeById(com.ljl.human.pojo.Employee)   
	 */  
	@Override
	public void deleteEmployeeById(Employee employee) throws SQLException {
		if(employee.getdId()==null){
			throw new RuntimeException("传入id为空");
		}
		employeeDao.deleteEmployeeById(employee);
	}

	/**   
	 * <p>Title: updateEmployeeById</p>   
	 * <p>Description: </p>   
	 * @param employee
	 * @throws SQLException   
	 * @see com.ljl.human.service.IEmployeeService#updateEmployeeById(com.ljl.human.pojo.Employee)   
	 */  
	@Override
	public void updateEmployeeById(Employee employee) throws SQLException {
		if(employee.getdId()==null){
			throw new RuntimeException("传入id为空");
		}
		employeeDao.updateEmployeeById(employee);
	}
		
	/**   
	 * <p>Title: findEmployeeByCompositionCondition</p>   
	 * <p>Description: </p>   
	 * @param employee
	 * @return
	 * @throws SQLException   
	 * @see com.ljl.human.service.IEmployeeService#findEmployeeByCompositionCondition(com.ljl.human.pojo.Employee)   
	 */  
	@Override
	public List<Employee> findEmployeeByCompositionCondition(Employee employee) throws SQLException {
		
		return employeeDao.findEmployeeByCompositionCondition(employee);
	}

	/**   
	 * <p>Title: findEmployeeAll</p>   
	 * <p>Description: </p>   
	 * @return
	 * @throws SQLException   
	 * @see com.ljl.human.service.IEmployeeService#findEmployeeAll()   
	 */  
	@Override
	public List<Employee> findEmployeeAll() throws SQLException {
		System.out.println("-------------------------");
		return employeeDao.findEmployeeAll();
	}

	/**   
	 * <p>Title: findEmployeeById</p>   
	 * <p>Description: </p>   
	 * @param employee
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.service.IEmployeeService#findEmployeeById(com.ljl.human.pojo.Employee)   
	 */  
	@Override
	public Employee findEmployeeById(Employee employee) throws SQLException {
		if(employee.geteId()==null){
			System.out.println("id不能为空");
		}
		return employeeDao.findEmployeeById(employee);
	}
	
	
	

}
