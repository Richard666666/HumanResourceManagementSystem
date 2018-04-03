/**  
 *
 * @Title:  DepartmentServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月8日 下午2:12:08   
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
 * @author: 李建良  
 * @date:   2018年1月8日 下午2:12:08   
 * @version V1.0 
 * 
 */
public class DepartmentServlet extends HttpServlet {
		//维护部门的业务逻辑对象
		private IDepartmentService departmentService=null;
		//通过用构造器来节约空间？-----------这里是个问题----通过objectUtils拿对象
		public DepartmentServlet() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
			departmentService=(IDepartmentService) ObjectUtils.getObject("departmentService");
			
		}
		
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//第一步:获取请求的方法
			String methodName=request.getParameter("method");
			//第二步:实现数据的自动封装
			Department department=null;
			try {
				department=ObjectWrapperUtils.getObject(request, Department.class);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
				System.out.println("封装对象失败");
			}
				//判断
				try {
					judgement(methodName, request, response, department);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			
		}
		
		
		//第三步:根据不同的请求方法做出相应的请求处理---
		private void judgement(String methodName, HttpServletRequest request, HttpServletResponse response,Department department) throws ServletException, IOException, JSONException{
			 if("departmentFindAll".equals(methodName)) {  //查询所有的部门
				findDepartmentrAllHandle(request, response);
			}else if("addDepartmentJsp".equals(methodName)) {  //说明是添加部门的页面
				jumpPage("/WEB-INF/jsp/dept/showAddDept.jsp", request, response);
			}else if("addDepartment".equals(methodName)) {    //说明是添加部门
				addDepartmentHandle(department,request,response);
			}else if("delete".equals(methodName)) {
				deleteHandle(department,request,response);
			}else if("updateJsp".equals(methodName)) {    //说明这个只是通过Servlet往JSP页面进行跳转而已
				updateJspHandle(department,request,response);
			}else if("update".equals(methodName)) {       //说明是更改部门
				 updateDepartmentHandle(department,request,response);
			}else if("query".equals(methodName)) {        //表示的是查询
				 queryHandle(department,request,response);
			}else if("getDept".equals(methodName)){
				//下面就应该调用业务逻辑方法
				List<Department> departments;
				try {
					departments = departmentService.findDepartmentAll();
					JSONArray array=new JSONArray();
					//将数据转换成str类型的json格式的数据
					for (int i = 0; i < departments.size(); i++) {
						//创建里面的json对象
						JSONObject object=new JSONObject();
						//将数据放到对象里面
						object.put("jId",departments.get(i).getdId());
						object.put("jName",departments.get(i).getdName());
						array.put(object);
					}
					//将这个json数据放到输出流里面去
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
				//将数据放到域对象里面
				request.setAttribute("departments", departments);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("模糊搜索用户失败");
			}finally{
				//进行页面跳转
				try {
					jumpPage("/WEB-INF/jsp/dept/dept.jsp", request, response);
				} catch (Exception e) {
					 System.out.println("跳转页面失败:"+e.getMessage());
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
			//调用业务逻辑类来更改用户
			 try {
				departmentService.updateDepartmentById(department);
			
				//跳转到查询页面
				jumpPage("/department?method=departmentFindAll", request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("更改用户失败....");
				try {
					jumpPage("/department?method=updateJsp", request, response);
				} catch (Exception e1) {
					System.out.println("更改用户页面失败:"+e1.getMessage());
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
			//通过id查询用户
			try {
				Department department2=departmentService.findDepartmentById(department);
				//将数据放到域对象里面去
				request.setAttribute("department",department2);
				//下面跳转到修改数据的页面去
				jumpPage("/WEB-INF/jsp/dept/showUpdateDept.jsp", request, response);
			} catch (Exception e) {
				System.out.println("通过id查找用户失败...");
				try {
					jumpPage("/department?method=departmentFindAll", request, response);
				} catch (Exception e1) {
					System.out.println("通过id查找用户失败:"+e1.getMessage());
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
			//获取要删除数据的id
			System.out.println("要删除数据的id是:"+department.getdId());
			try {
				//调用业务逻辑,删除数据
				
					departmentService.deleteDepartmentById(department);
				
			}catch (SQLException e) {
				System.out.println("删除数据出现问题了....");
			}finally{
				//跳转页面
				try {
					jumpPage("/department?method=departmentFindAll", request, response);
				} catch (ServletException | IOException e) {
					System.out.print("跳转失败");
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
			//判断当前删的管理员(用户)是不是自己-----user2表示当前在操作的管理员
			Department department2=(Department)request.getSession().getAttribute("department");
			//这个表示进行拆箱
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
								
				//通过service层来增加
				departmentService.insertDepartment(department);
				//第二步:跳转到页面(查询所有的页面)
				jumpPage("/department?method=departmentFindAll", request, response);
			} catch (Exception e) {
			    System.out.println("添加用户失败....");
			    //跳转到添加用户的页面
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
				//查询所用的用户
				List<Department> departments=departmentService.findDepartmentAll();
				//将数据放到域对象
				request.setAttribute("departments", departments);
			} catch (SQLException e) {
				System.out.println("查询所用部门出现问题");
			}
				//通过调整显示页面
				jumpPage("/WEB-INF/jsp/dept/dept.jsp", request, response);
			
		}

	

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
		
		
		
		
		
		

}
