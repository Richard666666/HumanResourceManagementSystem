
package com.ljl.human.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ljl.human.Dao.IDepartmentDao;
import com.ljl.human.Dao.impl.DepartmentDao;
import com.ljl.human.pojo.Department;
import com.ljl.human.pojo.User;
import com.ljl.human.service.IDepartmentService;
import com.ljl.human.utils.ObjectUtils;

/**  
 *
 * @Title:  DepartmentService.java   
 * @Package com.ljl.human.service.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月8日 下午8:35:41   
 * @version V1.0 
 * 
 */
public class DepartmentService implements IDepartmentService {
	private IDepartmentDao departmentDao=null;

	public DepartmentService() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		departmentDao=(IDepartmentDao) ObjectUtils.getObject("departmentDao");
	}

	/**   
	 * <p>Title: insertDepartment</p>   
	 * <p>Description: </p>   
	 * @param department
	 * @throws SQLException   
	 * @see com.ljl.human.service.IDepartmentService#insertDepartment(com.ljl.human.pojo.Department)   
	 */  
	@Override
	public void insertDepartment(Department department) throws SQLException {
		if(department.getdDes().equals("")||department.getdName().equals("")){
			throw new RuntimeException("参数有误不能进行添加");
		}
		departmentDao.insertDepartment(department);
		
	}
	/**   
	 * <p>Title: deleteDepartmentById</p>   
	 * <p>Description: </p>   
	 * @param department
	 * @throws SQLException   
	 * @see com.ljl.human.service.IDepartmentService#deleteDepartmentById(com.ljl.human.pojo.Department)   
	 */  
	@Override
	public void deleteDepartmentById(Department department) throws SQLException {
		if(department.getdId()==null){
			throw new RuntimeException("Id为null不能删除数据");
		}
		departmentDao.deleteDepartmentById(department);
	}

	/**   
	 * <p>Title: updateDepartmentById</p>   
	 * <p>Description: </p>   
	 * @param department
	 * @throws SQLException   
	 * @see com.ljl.human.service.IDepartmentService#updateDepartmentById(com.ljl.human.pojo.Department)   
	 */  
	@Override
	public void updateDepartmentById(Department department) throws SQLException {
		if(department.getdId()==null){
			throw new RuntimeException("id为null不能更新");
		}
		departmentDao.updateDepartmentById(department);
	}

	/**   
	 * <p>Title: findDepartmentAll</p>   
	 * <p>Description: </p>   
	 * @return
	 * @throws SQLException   
	 * @see com.ljl.human.service.IDepartmentService#findDepartmentAll()   
	 */  
	@Override
	public List<Department> findDepartmentAll() throws SQLException {
		
		return  departmentDao.findDepartmentAll();
	}

	/**   
	 * <p>Title: findNameById</p>   
	 * <p>Description: </p>   
	 * @param dId
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.service.IDepartmentService#findNameById(int)   
	 */  
	@Override
	public String findNameById(int dId) throws SQLException {
		
		return departmentDao.findNameById(dId);
	}

	/**   
	 * <p>Title: findDepartmentById</p>   
	 * <p>Description: </p>   
	 * @param department
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.service.IDepartmentService#findDepartmentById(com.ljl.human.pojo.Department)   
	 */  
	@Override
	public Department findDepartmentById(Department department) throws SQLException {
		
		if(department.getdId()==null){
			System.out.println("id不能为空");
		}
		return departmentDao.findDepartmentById(department);
	}

	/**   
	 * <p>Title: findDepartmentByName</p>   
	 * <p>Description: </p>   
	 * @param department
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.service.IDepartmentService#findDepartmentByName(com.ljl.human.pojo.Department)   
	 */  
	@Override
	public List<Department> findDepartmentByName(Department department) throws SQLException {
		return departmentDao.findDepartmentByName(department);
	}

}
