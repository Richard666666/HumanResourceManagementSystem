/**  
 *
 * @Title:  EmployeeServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��7�� ����4:06:43   
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
 *Ա���Ŀ��Ʋ�(servlet)
 * @Title:  EmployeeServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��7�� ����4:06:43   
 * @version V1.0 
 * 
 */
public class EmployeeServlet extends HttpServlet {
	private IEmployeeService employeeService=null;//Ա����ҵ���߼�
	private IDepartmentService departmentService=null;   //���ŵ�ҵ���߼�(Ϊ���������׼��)
	private IJobService jobService=null;     //ְλ��ҵ���߼�(Ϊ���������׼��)
	

	public EmployeeServlet() {
		try {
			employeeService=(IEmployeeService) ObjectUtils.getObject("employeeService");
			departmentService= (IDepartmentService) ObjectUtils.getObject("departmentService");
			jobService= (IJobService) ObjectUtils.getObject("jobService");
			
			
		} catch (Exception e) {
			System.out.println("������Ա������ʧ��"+e.getMessage());
		}
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//��һ��:��ȡ����ķ���
		String methodName=request.getParameter("method");
		//�ڶ���:ʵ�����ݵ��Զ���װ
		Employee employee=null;
		try {
		 employee=ObjectWrapperUtils.getObject(request, Employee.class);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.out.println("��װ����ʧ��");
		}
			//�ж�
		try {
			judgement(methodName, request, response, employee);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	//������:���ݲ�ͬ�����󷽷�������Ӧ��������---
			private void judgement(String methodName, HttpServletRequest request, HttpServletResponse response,Employee employee) throws Exception{
				 if("jumpEmployeeMangerJsp".equals(methodName)){  //��ѯ���еĲ���
					findEmployeeAllHandle(request, response);
				}else if("addEmployeeJsp".equals(methodName)) {  //˵������Ӳ��ŵ�ҳ��(���)
					jumpPage("/WEB-INF/jsp/dept/showAddEmployee.jsp", request, response);
				}else if("addEmployee".equals(methodName)) {    //˵��������û�
					addEmployeeHandle(employee,request,response);
				}else if("delete".equals(methodName)) {
					deleteHandle(employee,request,response);
				}else if("updateJsp".equals(methodName)) {    //˵�����ֻ��ͨ��Servlet��JSPҳ�������ת����
					//jumpPage("/WEB-INF/jsp/user/showUpdateUser.jsp", request, response);
					updateJspHandle(employee,request,response);
				}else if("update".equals(methodName)) {       //˵���Ǹ����û�
					 updateEmployeeHandle(employee,request,response);
				}else if("query".equals(methodName)) {        //�����ʾ���ǲ�ѯ
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
				//ͨ��id��ѯ�û�
				try {
					Employee employee2=employeeService.findEmployeeById(employee);
					//�����ݷŵ����������ȥ
					request.setAttribute("employee",employee2);
					//������ת���޸����ݵ�ҳ��ȥ
					jumpPage("/WEB-INF/jsp/employee/showUpdateEmployee.jsp", request, response);
				} catch (Exception e) {
					System.out.println("ͨ��id�����û�ʧ��...");
					try {
						jumpPage("/employee?method=employeeFindAll", request, response);
					} catch (Exception e1) {
						System.out.println("ͨ��id�����û�ʧ��:"+e1.getMessage());
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
					
					//ͨ��service��������
					employeeService.insertEmployee(employee);
					//�ڶ���:��ת��ҳ��(��ѯ���е�ҳ��)
					jumpPage("/employee?method=employeeFindAll", request, response);
				} catch (Exception e) {
					e.printStackTrace();
				    System.out.println("����û�ʧ��....");
				    //��ת������û���ҳ��
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
				//1����ѯ���е�Ա��
				List<Employee> employees=employeeService.findEmployeeAll();
			 	//2����Ա�����з�װ----�ⲽ��Ҫ���******(������Ҫ�ú����)
				employees=wrapperEmloyee(employees);
				//3���ŵ����������
				request.setAttribute("employees", employees);
				//4����תҳ��
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


	/**�����Ҫ�ú����
	 * @throws SQLException    ��Ա�����з�װ
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
			//���ò��ź�ְλ��ҵ���߼�
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
