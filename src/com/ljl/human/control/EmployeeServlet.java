/**  
 *
 * @Title:  EmployeeServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月7日 下午4:06:43   
 * @version V1.0 
 * 
 */
package com.ljl.human.control;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ljl.human.pojo.Department;
import com.ljl.human.pojo.Employee;
import com.ljl.human.pojo.Job;
import com.ljl.human.service.IDepartmentService;
import com.ljl.human.service.IEmployeeService;
import com.ljl.human.service.IJobService;
import com.ljl.human.utils.ObjectUtils;
import com.ljl.human.utils.ObjectWrapperUtils;

/**  
 *员工的控制层(servlet)
 * @Title:  EmployeeServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月7日 下午4:06:43   
 * @version V1.0 
 * 
 */
public class EmployeeServlet extends HttpServlet {
	private IEmployeeService employeeService=null;//员工的业务逻辑
	private IDepartmentService departmentService=null;   //部门的业务逻辑(为调用外键做准备)
	private IJobService jobService=null;     //职位的业务逻辑(为调用外键做准备)
	

	public EmployeeServlet() {
		try {
			employeeService=(IEmployeeService) ObjectUtils.getObject("employeeService");
			departmentService= (IDepartmentService) ObjectUtils.getObject("departmentService");
			jobService= (IJobService) ObjectUtils.getObject("jobService");
			
			
		} catch (Exception e) {
			System.out.println("反射获得员工对象失败"+e.getMessage());
		}
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//第一步:获取请求的方法
		String methodName=request.getParameter("method");
		//第二步:实现数据的自动封装
		Employee employee=null;
		try {
		 employee=ObjectWrapperUtils.getObject(request, Employee.class);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.out.println("封装对象失败");
		}
			//判断
		try {
			judgement(methodName, request, response, employee);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	//第三步:根据不同的请求方法做出相应的请求处理---
			private void judgement(String methodName, HttpServletRequest request, HttpServletResponse response,Employee employee) throws Exception{
				 if("jumpEmployeeMangerJsp".equals(methodName)){  //查询所有的部门
					findEmployeeAllHandle(request, response);
				}else if("addEmployeeJsp".equals(methodName)) {  //说明是添加部门的页面(完成)
					jumpPage("/WEB-INF/jsp/dept/showAddEmployee.jsp", request, response);
				}else if("addEmployee".equals(methodName)) {    //说明是添加用户
					addEmployeeHandle(employee,request,response);
				}else if("delete".equals(methodName)) {
					deleteHandle(employee,request,response);
				}else if("updateJsp".equals(methodName)) {    //说明这个只是通过Servlet往JSP页面进行跳转而已
					//jumpPage("/WEB-INF/jsp/user/showUpdateUser.jsp", request, response);
					updateJspHandle(employee,request,response);
				}else if("update".equals(methodName)) {       //说明是更改用户
					 updateEmployeeHandle(employee,request,response);
				}else if("query".equals(methodName)) {        //这个表示的是查询
					 queryHandle(employee,request,response);
				}
			}
		
			/**   
			 * @Title: queryHandle   
			 * @Description: TODO  
			 * @param: @param employee
			 * @param: @param request
			 * @param: @param response      
			 * @return: void      
			 * @throws   
			 */  
			private void queryHandle(Employee employee, HttpServletRequest request, HttpServletResponse response) {
				// TODO Auto-generated method stub
				
			}


			/**   
			 * @Title: updateEmployeeHandle   
			 * @Description: TODO  
			 * @param: @param employee
			 * @param: @param request
			 * @param: @param response      
			 * @return: void      
			 * @throws   
			 */  
			private void updateEmployeeHandle(Employee employee, HttpServletRequest request,
					HttpServletResponse response) {
				//通过id查询用户
				try {
					Employee employee2=employeeService.findEmployeeById(employee);
					//将数据放到域对象里面去
					request.setAttribute("employee",employee2);
					//下面跳转到修改数据的页面去
					jumpPage("/WEB-INF/jsp/employee/showUpdateEmployee.jsp", request, response);
				} catch (Exception e) {
					System.out.println("通过id查找用户失败...");
					try {
						jumpPage("/employee?method=employeeFindAll", request, response);
					} catch (Exception e1) {
						System.out.println("通过id查找用户失败:"+e1.getMessage());
					} 
				}
				
			}


			/**   
			 * @Title: updateJspHandle   
			 * @Description: TODO  
			 * @param: @param employee
			 * @param: @param request
			 * @param: @param response      
			 * @return: void      
			 * @throws   
			 */  
			private void updateJspHandle(Employee employee, HttpServletRequest request, HttpServletResponse response) {
				// TODO Auto-generated method stub
				
			}


			/**   
			 * @Title: deleteHandle   
			 * @Description: TODO  
			 * @param: @param employee
			 * @param: @param request
			 * @param: @param response      
			 * @return: void      
			 * @throws   
			 */  
			private void deleteHandle(Employee employee, HttpServletRequest request, HttpServletResponse response) {
				// TODO Auto-generated method stub
				
			}


			/**
			 * @throws IOException 
			 * @throws ServletException    
			 * @Title: addEmployeeHandle   
			 * @Description: TODO  
			 * @param: @param employee
			 * @param: @param request
			 * @param: @param response      
			 * @return: void      
			 * @throws   
			 */  
			private void addEmployeeHandle(Employee employee, HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
				try {
					
					//通过service层来增加
					employeeService.insertEmployee(employee);
					//第二步:跳转到页面(查询所有的页面)
					jumpPage("/employee?method=employeeFindAll", request, response);
				} catch (Exception e) {
					e.printStackTrace();
				    System.out.println("添加用户失败....");
				    //跳转到添加用户的页面
				    jumpPage("/WEB-INF/jsp/employee/showAddEmployee.jsp", request, response);
				}
				
			}


			/**
			 * @throws Exception    
			 * @Title: findEmployeeAllHandle   
			 * @Description: TODO  
			 * @param: @param request
			 * @param: @param response      
			 * @return: void      
			 * @throws   
			 */  
			private void findEmployeeAllHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
				//1、查询所有的员工
				List<Employee> employees=employeeService.findEmployeeAll();
			 	//2、对员工进行封装----这步需要理解******(这里需要好好理解)
				employees=wrapperEmloyee(employees);
				//3、放到域对象里面
				request.setAttribute("employees", employees);
				//4、跳转页面
				jumpPage("/WEB-INF/jsp/employee/employee.jsp", request, response);
				
			}


	/**
	 * @throws IOException 
	 * @throws ServletException    
	 * @Title: jumpPage   
	 * @Description: TODO  
	 * @param: @param string
	 * @param: @param request
	 * @param: @param response     
	 * @return: void      
	 * @throws   
	 */  
	private void jumpPage(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
		
	}


	/**这个需要好好理解
	 * @throws SQLException    对员工进行封装
	 * @Title: wrapperEmloyee   
	 * @Description: TODO  
	 * @param: @param employees
	 * @param: @return      
	 * @return: List<Employee>      
	 * @throws   
	 */  
	private List<Employee> wrapperEmloyee(List<Employee> employees) throws SQLException {
		List<Employee> employeesReturn=new ArrayList<Employee>();
		for(int i=0;i<employees.size();i++){
			int dId=employees.get(i).getdId();
			int jId=employees.get(i).getjId();
			//调用部门和职位的业务逻辑
			String jName=jobService.findNameById(jId);
			String dName=departmentService.findNameById(dId);
			
			
			employees.get(i).setJob(new Job(0,jName,""));
			employees.get(i).setDepartment(new Department(0,dName,""));
			employeesReturn.add(employees.get(i));
				
		}
		
		return employeesReturn;
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}
	
	

}
