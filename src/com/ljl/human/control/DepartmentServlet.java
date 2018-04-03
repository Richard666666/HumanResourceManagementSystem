/**  
 *
 * @Title:  DepartmentServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��8�� ����2:12:08   
 * @version V1.0 
 * 
 */
package com.ljl.human.control;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ljl.human.pojo.Department;
import com.ljl.human.service.IDepartmentService;
import com.ljl.human.utils.ObjectUtils;
import com.ljl.human.utils.ObjectWrapperUtils;


/**  
 *
 * @Title:  DepartmentServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��8�� ����2:12:08   
 * @version V1.0 
 * 
 */
public class DepartmentServlet extends HttpServlet {
		//ά�����ŵ�ҵ���߼�����
		private IDepartmentService departmentService=null;
		//ͨ���ù���������Լ�ռ䣿-----------�����Ǹ�����----ͨ��objectUtils�ö���
		public DepartmentServlet() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
			departmentService=(IDepartmentService) ObjectUtils.getObject("departmentService");
			
		}
		
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//��һ��:��ȡ����ķ���
			String methodName=request.getParameter("method");
			//�ڶ���:ʵ�����ݵ��Զ���װ
			Department department=null;
			try {
				department=ObjectWrapperUtils.getObject(request, Department.class);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
				System.out.println("��װ����ʧ��");
			}
				//�ж�
				try {
					judgement(methodName, request, response, department);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			
		}
		
		
		//������:���ݲ�ͬ�����󷽷�������Ӧ��������---
		private void judgement(String methodName, HttpServletRequest request, HttpServletResponse response,Department department) throws ServletException, IOException, JSONException{
			 if("departmentFindAll".equals(methodName)) {  //��ѯ���еĲ���
				findDepartmentrAllHandle(request, response);
			}else if("addDepartmentJsp".equals(methodName)) {  //˵������Ӳ��ŵ�ҳ��
				jumpPage("/WEB-INF/jsp/dept/showAddDept.jsp", request, response);
			}else if("addDepartment".equals(methodName)) {    //˵������Ӳ���
				addDepartmentHandle(department,request,response);
			}else if("delete".equals(methodName)) {
				deleteHandle(department,request,response);
			}else if("updateJsp".equals(methodName)) {    //˵�����ֻ��ͨ��Servlet��JSPҳ�������ת����
				updateJspHandle(department,request,response);
			}else if("update".equals(methodName)) {       //˵���Ǹ��Ĳ���
				 updateDepartmentHandle(department,request,response);
			}else if("query".equals(methodName)) {        //��ʾ���ǲ�ѯ
				 queryHandle(department,request,response);
			}else if("getDept".equals(methodName)){
				//�����Ӧ�õ���ҵ���߼�����
				List<Department> departments;
				try {
					departments = departmentService.findDepartmentAll();
					JSONArray array=new JSONArray();
					//������ת����str���͵�json��ʽ������
					for (int i = 0; i < departments.size(); i++) {
						//���������json����
						JSONObject object=new JSONObject();
						//�����ݷŵ���������
						object.put("jId",departments.get(i).getdId());
						object.put("jName",departments.get(i).getdName());
						array.put(object);
					}
					//�����json���ݷŵ����������ȥ
					response.getWriter().write(array.toString());
					response.getWriter().flush();
					response.getWriter().close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				
				
			}
		}
		
	 
		/**   
		 * @Title: queryHandle   
		 * @Description: TODO  
		 * @param: @param department
		 * @param: @param request
		 * @param: @param response      
		 * @return: void      
		 * @throws   
		 */  
		private void queryHandle(Department department, HttpServletRequest request, HttpServletResponse response) {
			try {
				List<Department> departments=departmentService.findDepartmentByName(department);
				//�����ݷŵ����������
				request.setAttribute("departments", departments);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ģ�������û�ʧ��");
			}finally{
				//����ҳ����ת
				try {
					jumpPage("/WEB-INF/jsp/dept/dept.jsp", request, response);
				} catch (Exception e) {
					 System.out.println("��תҳ��ʧ��:"+e.getMessage());
				}
			}
			
		}

		/**
		 * @throws IOException 
		 * @throws ServletException    
		 * @Title: updateDepartmentHandle   
		 * @Description: TODO  
		 * @param: @param department
		 * @param: @param request
		 * @param: @param response      
		 * @return: void      
		 * @throws   
		 */  
		private void updateDepartmentHandle(Department department, HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			//����ҵ���߼����������û�
			 try {
				departmentService.updateDepartmentById(department);
			
				//��ת����ѯҳ��
				jumpPage("/department?method=departmentFindAll", request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�����û�ʧ��....");
				try {
					jumpPage("/department?method=updateJsp", request, response);
				} catch (Exception e1) {
					System.out.println("�����û�ҳ��ʧ��:"+e1.getMessage());
				} 
				
			}
			
		}

	

		/**
		 * @throws IOException 
		 * @throws ServletException    
		 * @Title: updateJspHandle   
		 * @Description: TODO  
		 * @param: @param department
		 * @param: @param request
		 * @param: @param response      
		 * @return: void      
		 * @throws   
		 */  
		private void updateJspHandle(Department department, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//ͨ��id��ѯ�û�
			try {
				Department department2=departmentService.findDepartmentById(department);
				//�����ݷŵ����������ȥ
				request.setAttribute("department",department2);
				//������ת���޸����ݵ�ҳ��ȥ
				jumpPage("/WEB-INF/jsp/dept/showUpdateDept.jsp", request, response);
			} catch (Exception e) {
				System.out.println("ͨ��id�����û�ʧ��...");
				try {
					jumpPage("/department?method=departmentFindAll", request, response);
				} catch (Exception e1) {
					System.out.println("ͨ��id�����û�ʧ��:"+e1.getMessage());
				} 
			}
			}
			
		

		/**   
		 * @Title: deleteHandle   
		 * @Description: TODO  
		 * @param: @param department
		 * @param: @param request
		 * @param: @param response      
		 * @return: void      
		 * @throws   
		 */  
		private void deleteHandle(Department department, HttpServletRequest request, HttpServletResponse response) {
			//��ȡҪɾ�����ݵ�id
			System.out.println("Ҫɾ�����ݵ�id��:"+department.getdId());
			try {
				//����ҵ���߼�,ɾ������
				
					departmentService.deleteDepartmentById(department);
				
			}catch (SQLException e) {
				System.out.println("ɾ�����ݳ���������....");
			}finally{
				//��תҳ��
				try {
					jumpPage("/department?method=departmentFindAll", request, response);
				} catch (ServletException | IOException e) {
					System.out.print("��תʧ��");
				}
				
			}
			
		}

		/**   
		 * @Title: judgeDeleteValidity   
		 * @Description: TODO  
		 * @param: @param department
		 * @param: @param request
		 * @param: @return      
		 * @return: boolean      
		 * @throws   
		 */  
		private boolean judgeDeleteValidity(Department department, HttpServletRequest request) {
			//�жϵ�ǰɾ�Ĺ���Ա(�û�)�ǲ����Լ�-----user2��ʾ��ǰ�ڲ����Ĺ���Ա
			Department department2=(Department)request.getSession().getAttribute("department");
			//�����ʾ���в���
			if(department.getdId().intValue()==department2.getdId().intValue()){
				return false;
			}
			
			return true;
		}

		/**
		 * @throws IOException 
		 * @throws ServletException    
		 * @Title: addDepartmentHandle   
		 * @Description: TODO  
		 * @param: @param department
		 * @param: @param request
		 * @param: @param response      
		 * @return: void      
		 * @throws   
		 */  
		private void addDepartmentHandle(Department department, HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			try {
								
				//ͨ��service��������
				departmentService.insertDepartment(department);
				//�ڶ���:��ת��ҳ��(��ѯ���е�ҳ��)
				jumpPage("/department?method=departmentFindAll", request, response);
			} catch (Exception e) {
			    System.out.println("����û�ʧ��....");
			    //��ת������û���ҳ��
			    jumpPage("/WEB-INF/jsp/dept/showAddDept.jsp", request, response);
			}
	
			
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
			request.getRequestDispatcher(path).forward(request,response);
			
		}

		/**
		 * @throws IOException 
		 * @throws ServletException    
		 * @Title: findDepartmentrAllHandle   
		 * @Description: TODO  
		 * @param: @param request
		 * @param: @param response      
		 * @return: void      
		 * @throws   
		 */  
		private void findDepartmentrAllHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				//��ѯ���õ��û�
				List<Department> departments=departmentService.findDepartmentAll();
				//�����ݷŵ������
				request.setAttribute("departments", departments);
			} catch (SQLException e) {
				System.out.println("��ѯ���ò��ų�������");
			}
				//ͨ��������ʾҳ��
				jumpPage("/WEB-INF/jsp/dept/dept.jsp", request, response);
			
		}

	

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
		
		
		
		
		
		

}
