/**  
 *
 * @Title:  JobServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��9�� ����11:00:09   
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
 * @author: ���  
 * @date:   2018��1��9�� ����11:00:09   
 * @version V1.0 
 * 
 */
public class JobServlet extends HttpServlet{

			//ά���û���ҵ���߼�����
			private IJobService jobService=null;
			//ͨ���ù���������Լ�ռ䣿-----------�����Ǹ�����----ͨ��objectUtils�ö���
			public JobServlet() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
				jobService=(IJobService) ObjectUtils.getObject("jobService");
				
			}
			
		
			@Override
			protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//��һ��:��ȡ����ķ���
				String methodName=request.getParameter("method");
				//�ڶ���:ʵ�����ݵ��Զ���װ
				Job job=null;
				try {
					job=ObjectWrapperUtils.getObject(request, Job.class);
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
					System.out.println("��װ����ʧ��");
				}
					//�ж�
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
				 if("jobFindAll".equals(methodName)) {  //��ѯ���еĲ���
						findJobAllHandle(request, response);
					}else if("addJobJsp".equals(methodName)) {  //˵������Ӳ��ŵ�ҳ��(���)
						jumpPage("/WEB-INF/jsp/job/showAddJob.jsp", request, response);
					}else if("addJob".equals(methodName)) {    //˵��������û�
						addJobHandle(job,request,response);
					}else if("delete".equals(methodName)) {
						deleteHandle(job,request,response);
					}else if("updateJsp".equals(methodName)) {    //˵�����ֻ��ͨ��Servlet��JSPҳ�������ת����
						//jumpPage("/WEB-INF/jsp/user/showUpdateUser.jsp", request, response);
						updateJspHandle(job,request,response);
					}else if("update".equals(methodName)) {       //˵���Ǹ����û�
						 updateJobHandle(job,request,response);
					}else if("query".equals(methodName)) {        //�����ʾ���ǲ�ѯ
						 queryHandle(job,request,response);
					}else if("getJob".equals(methodName)){
						handleGetAllJobs(response);
					}
				
			}


			/**�����ȡ����ְλ�ķ���----------
			 * @throws Exception    
			 * @Title: handleGetAllJobs   
			 * @Description: TODO  
			 * @param: @param response      
			 * @return: void      
			 * @throws   
			 */  
			private void handleGetAllJobs(HttpServletResponse response) throws Exception {
				//�����Ӧ�õ���ҵ���߼�����
				List<Job> jobs=jobService.findJobAll();
				
				JSONArray array=new JSONArray();
				//������ת����str���͵�json��ʽ������
				for (int i = 0; i < jobs.size(); i++) {
					//���������json����
					JSONObject object=new JSONObject();
					//�����ݷŵ���������
					object.put("jId",jobs.get(i).getjId());
					object.put("jName",jobs.get(i).getjName());
					array.put(object);
				}
				//�����json���ݷŵ����������ȥ
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
					//�����ݷŵ����������
					request.setAttribute("jobs", jobs);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ģ�������û�ʧ��");
				}finally{
					//����ҳ����ת
					try {
						jumpPage("/WEB-INF/jsp/job/job.jsp", request, response);
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
			 * @param: @param job
			 * @param: @param request
			 * @param: @param response      
			 * @return: void      
			 * @throws   
			 */  
			private void updateJobHandle(Job job, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//����ҵ���߼����������û�
				 try {
					jobService.updateJobById(job);
				
					//��ת����ѯҳ��
					jumpPage("/job?method=jobFindAll", request, response);
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("�����û�ʧ��....");
					try {
						jumpPage("/job?method=updateJsp", request, response);
					} catch (Exception e1) {
						System.out.println("�����û�ҳ��ʧ��:"+e1.getMessage());
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
				//ͨ��id��ѯ�û�
				try {
					Job job2=jobService.findJobById(job);
					//�����ݷŵ����������ȥ
					request.setAttribute("job",job2);
					//������ת���޸����ݵ�ҳ��ȥ
					jumpPage("/WEB-INF/jsp/job/showUpdateJob.jsp", request, response);
				} catch (Exception e) {
					System.out.println("ͨ��id�����û�ʧ��...");
					try {
						jumpPage("/job?method=jobFindAll", request, response);
					} catch (Exception e1) {
						System.out.println("ͨ��id�����û�ʧ��:"+e1.getMessage());
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
				//��ȡҪɾ�����ݵ�id
				System.out.println("Ҫɾ�����ݵ�id��:"+job.getjId());
				try {
					//����ҵ���߼�,ɾ������
					
						jobService.deleteJobById(job);
					
				}catch (SQLException e) {
					System.out.println("ɾ�����ݳ���������....");
				}finally{
					//��תҳ��
					try {
						jumpPage("/job?method=jobFindAll", request, response);
					} catch (ServletException | IOException e) {
						System.out.print("��תʧ��");
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
										
						//ͨ��service��������
						jobService.insertJob(job);
						//�ڶ���:��ת��ҳ��(��ѯ���е�ҳ��)
						jumpPage("/job?method=jobFindAll", request, response);
					} catch (Exception e) {
					    System.out.println("����û�ʧ��....");
					    //��ת������û���ҳ��
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
					//��ѯ���õ��û�
					List<Job> jobs=jobService.findJobAll();
					//�����ݷŵ������
					request.setAttribute("jobs", jobs);
				} catch (SQLException e) {
					System.out.println("��ѯ���ò��ų�������");
				}
					//ͨ��������ʾҳ��
					jumpPage("/WEB-INF/jsp/job/job.jsp", request, response);
				
			}


			@Override
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doGet(request, response);
			}
			
			
			
			
			
}
