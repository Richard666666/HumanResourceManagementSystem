/**  
 *
 * @Title:  JobServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月9日 上午11:00:09   
 * @version V1.0 
 * 
 */
package com.ljl.human.control;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONArray;
import org.json.JSONObject;

import com.ljl.human.pojo.Job;
import com.ljl.human.service.IJobService;
import com.ljl.human.utils.ObjectUtils;
import com.ljl.human.utils.ObjectWrapperUtils;



/**  
 *
 * @Title:  JobServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月9日 上午11:00:09   
 * @version V1.0 
 * 
 */
public class JobServlet extends HttpServlet{

			//维护用户的业务逻辑对象
			private IJobService jobService=null;
			//通过用构造器来节约空间？-----------这里是个问题----通过objectUtils拿对象
			public JobServlet() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
				jobService=(IJobService) ObjectUtils.getObject("jobService");
				
			}
			
		
			@Override
			protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//第一步:获取请求的方法
				String methodName=request.getParameter("method");
				//第二步:实现数据的自动封装
				Job job=null;
				try {
					job=ObjectWrapperUtils.getObject(request, Job.class);
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
					System.out.println("封装对象失败");
				}
					//判断
					try {
						judgement(methodName, request, response, job);
					} catch (Exception e) {
						
						e.printStackTrace();
					}
			
			}
		
			/**
			 * @throws Exception 
			 * @throws IOException 
			 * @throws ServletException    
			 * @Title: judgement   
			 * @Description: TODO  
			 * @param: @param methodName
			 * @param: @param request
			 * @param: @param response
			 * @param: @param job      
			 * @return: void      
			 * @throws   
			 */  
			private void judgement(String methodName, HttpServletRequest request, HttpServletResponse response,
					Job job) throws Exception {
				 if("jobFindAll".equals(methodName)) {  //查询所有的部门
						findJobAllHandle(request, response);
					}else if("addJobJsp".equals(methodName)) {  //说明是添加部门的页面(完成)
						jumpPage("/WEB-INF/jsp/job/showAddJob.jsp", request, response);
					}else if("addJob".equals(methodName)) {    //说明是添加用户
						addJobHandle(job,request,response);
					}else if("delete".equals(methodName)) {
						deleteHandle(job,request,response);
					}else if("updateJsp".equals(methodName)) {    //说明这个只是通过Servlet往JSP页面进行跳转而已
						//jumpPage("/WEB-INF/jsp/user/showUpdateUser.jsp", request, response);
						updateJspHandle(job,request,response);
					}else if("update".equals(methodName)) {       //说明是更改用户
						 updateJobHandle(job,request,response);
					}else if("query".equals(methodName)) {        //这个表示的是查询
						 queryHandle(job,request,response);
					}else if("getJob".equals(methodName)){
						handleGetAllJobs(response);
					}
				
			}


			/**处理获取所有职位的方法----------
			 * @throws Exception    
			 * @Title: handleGetAllJobs   
			 * @Description: TODO  
			 * @param: @param response      
			 * @return: void      
			 * @throws   
			 */  
			private void handleGetAllJobs(HttpServletResponse response) throws Exception {
				//下面就应该调用业务逻辑方法
				List<Job> jobs=jobService.findJobAll();
				
				JSONArray array=new JSONArray();
				//将数据转换成str类型的json格式的数据
				for (int i = 0; i < jobs.size(); i++) {
					//创建里面的json对象
					JSONObject object=new JSONObject();
					//将数据放到对象里面
					object.put("jId",jobs.get(i).getjId());
					object.put("jName",jobs.get(i).getjName());
					array.put(object);
				}
				//将这个json数据放到输出流里面去
				response.getWriter().write(array.toString());
				response.getWriter().flush();
				response.getWriter().close();

			}


			/**   
			 * @Title: queryHandle   
			 * @Description: TODO  
			 * @param: @param job
			 * @param: @param request
			 * @param: @param response      
			 * @return: void      
			 * @throws   
			 */  
			private void queryHandle(Job job, HttpServletRequest request, HttpServletResponse response) {
				try {
					List<Job> jobs=jobService.findJobByName(job);
					//将数据放到域对象里面
					request.setAttribute("jobs", jobs);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("模糊搜索用户失败");
				}finally{
					//进行页面跳转
					try {
						jumpPage("/WEB-INF/jsp/job/job.jsp", request, response);
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
			 * @param: @param job
			 * @param: @param request
			 * @param: @param response      
			 * @return: void      
			 * @throws   
			 */  
			private void updateJobHandle(Job job, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//调用业务逻辑类来更改用户
				 try {
					jobService.updateJobById(job);
				
					//跳转到查询页面
					jumpPage("/job?method=jobFindAll", request, response);
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("更改用户失败....");
					try {
						jumpPage("/job?method=updateJsp", request, response);
					} catch (Exception e1) {
						System.out.println("更改用户页面失败:"+e1.getMessage());
					} 
					
				}
				
			}


			/**   
			 * @Title: updateJspHandle   
			 * @Description: TODO  
			 * @param: @param job
			 * @param: @param request
			 * @param: @param response      
			 * @return: void      
			 * @throws   
			 */  
			private void updateJspHandle(Job job, HttpServletRequest request, HttpServletResponse response) {
				//通过id查询用户
				try {
					Job job2=jobService.findJobById(job);
					//将数据放到域对象里面去
					request.setAttribute("job",job2);
					//下面跳转到修改数据的页面去
					jumpPage("/WEB-INF/jsp/job/showUpdateJob.jsp", request, response);
				} catch (Exception e) {
					System.out.println("通过id查找用户失败...");
					try {
						jumpPage("/job?method=jobFindAll", request, response);
					} catch (Exception e1) {
						System.out.println("通过id查找用户失败:"+e1.getMessage());
					} 
				}
				
			}


			/**   
			 * @Title: deleteHandle   
			 * @Description: TODO  
			 * @param: @param job
			 * @param: @param request
			 * @param: @param response      
			 * @return: void      
			 * @throws   
			 */  
			private void deleteHandle(Job job, HttpServletRequest request, HttpServletResponse response) {
				//获取要删除数据的id
				System.out.println("要删除数据的id是:"+job.getjId());
				try {
					//调用业务逻辑,删除数据
					
						jobService.deleteJobById(job);
					
				}catch (SQLException e) {
					System.out.println("删除数据出现问题了....");
				}finally{
					//跳转页面
					try {
						jumpPage("/job?method=jobFindAll", request, response);
					} catch (ServletException | IOException e) {
						System.out.print("跳转失败");
					}
					
				}
				
			}


			/**
			 * @throws IOException 
			 * @throws ServletException    
			 * @Title: addJobHandle   
			 * @Description: TODO  
			 * @param: @param job
			 * @param: @param request
			 * @param: @param response      
			 * @return: void      
			 * @throws   
			 */  
			private void addJobHandle(Job job, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
					try {
										
						//通过service层来增加
						jobService.insertJob(job);
						//第二步:跳转到页面(查询所有的页面)
						jumpPage("/job?method=jobFindAll", request, response);
					} catch (Exception e) {
					    System.out.println("添加用户失败....");
					    //跳转到添加用户的页面
					    jumpPage("/WEB-INF/jsp/job/showAddJob.jsp", request, response);
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
			 * @Title: findJobAllHandle   
			 * @Description: TODO  
			 * @param: @param request
			 * @param: @param response      
			 * @return: void      
			 * @throws   
			 */  
			private void findJobAllHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				try {
					//查询所用的用户
					List<Job> jobs=jobService.findJobAll();
					//将数据放到域对象
					request.setAttribute("jobs", jobs);
				} catch (SQLException e) {
					System.out.println("查询所用部门出现问题");
				}
					//通过调整显示页面
					jumpPage("/WEB-INF/jsp/job/job.jsp", request, response);
				
			}


			@Override
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doGet(request, response);
			}
			
			
			
			
			
}
