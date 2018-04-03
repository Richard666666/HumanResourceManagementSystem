/**  
 *
 * @Title:  UserServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��9�� ����2:16:02   
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



import com.ljl.human.pojo.User;
import com.ljl.human.service.IUserService;
import com.ljl.human.service.impl.UserService;
import com.ljl.human.utils.ObjectUtils;
import com.ljl.human.utils.ObjectWrapperUtils;

/**  
 *�û���servlet
 * @Title:  UserServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��9�� ����2:16:02   
 * @version V1.0 
 * 
 */
public class UserServlet extends HttpServlet {
	//ά���û���ҵ���߼�����
	private IUserService userService=null;
	//ͨ���ù���������Լ�ռ䣿-----------�����Ǹ�����----ͨ��objectUtils�ö���
	public UserServlet() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		userService=(IUserService) ObjectUtils.getObject("userService");
		
	}

	
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//��һ��:��ȡ����ķ���
			String methodName=request.getParameter("method");
			//�ڶ���:ʵ�����ݵ��Զ���װ
			User user=null;
			try {
				user=ObjectWrapperUtils.getObject(request, User.class);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
				System.out.println("��װ����ʧ��");
			}
			
				judgement(methodName, request, response, user);
	}
	
	
	
	
	
				
	
	//������:���ݲ�ͬ�����󷽷�������Ӧ��������---
	private void judgement(String methodName, HttpServletRequest request, HttpServletResponse response,User user) throws ServletException, IOException{
		if("login".equals(methodName)) {   //˵���û�����ʵ�ֵ�½
			loginHandle(request, response, user);
		}else if("cancerLogin".equals(methodName)) {  //�����������ע����½��
			cancerLoginHadle(request, response);
		}else if("userFindAll".equals(methodName)) {  //˵��Ӧ�ò�ѯ���е��û�(���)
			findUserAllHandle(request, response);
		}else if("addUserJsp".equals(methodName)) {  //˵��������û���ҳ��(���)
			jumpPage("/WEB-INF/jsp/user/showAddUser.jsp", request, response);
		}else if("addUser".equals(methodName)) {    //˵��������û�
			addUserHandle(user,request,response);
		}else if("delete".equals(methodName)) {
			deleteHandle(user,request,response);
		}else if("updateJsp".equals(methodName)) {    //˵�����ֻ��ͨ��Servlet��JSPҳ�������ת����
			//jumpPage("/WEB-INF/jsp/user/showUpdateUser.jsp", request, response);
			updateJspHandle(user,request,response);
		}else if("update".equals(methodName)) {       //˵���Ǹ����û�
			 updateUserHandle(user,request,response);
		}else if("query".equals(methodName)) {        //�����ʾ���ǲ�ѯ
			 queryHandle(user,request,response);
		}
	}
	
	
	
	/**
	 * @throws IOException 
	 * @throws ServletException    
	 * @Title: updateUserHandle   
	 * @Description: TODO  
	 * @param: @param user
	 * @param: @param request
	 * @param: @param response      
	 * @return: void      
	 * @throws   
	 */  
	private void updateUserHandle(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����ҵ���߼����������û�
		 try {
			userService.updateUserById(user);
			compairUserInfo(user,request,response);
			//��ת����ѯҳ��
			jumpPage("/users?method=userFindAll", request, response);
		} catch (SQLException e) {
			System.out.println("�����û�ʧ��....");
			try {
				jumpPage("/users?method=updateJsp", request, response);
			} catch (Exception e1) {
				System.out.println("�����û�ҳ��ʧ��:"+e1.getMessage());
			} 
			
		}
		
	}





	/**   
	 * @Title: compairUserInfo   
	 * @Description: TODO  
	 * @param: @param user
	 * @param: @param request
	 * @param: @param response      
	 * @return: void      
	 * @throws   
	 */  
	private void compairUserInfo(User user, HttpServletRequest request, HttpServletResponse response) {
		try {
			//��Ҫͨ��idȥ��ѯ�û��ҵ��û���������
				User userNew;
				userNew = userService.findUserById(user);
			   //���Ĺ�����û���Ϣ
			//�����ȡsession�е��û���Ϣ
			User userOld=(User) request.getSession().getAttribute("user");
			if(user.getuId().intValue()==userOld.getuId().intValue()){
				if(!(userNew.getuLoginName().equals(userOld.getuLoginName()))||!(userNew.getuPwd().equals(userOld.getuPwd()))){
					//��ʾӦ�ô��½��е�¼������û���Ϣ
					request.getSession().removeAttribute("user");
					//��ת����¼ҳ����ȥ
					jumpPage("/WEB-INF/jsp/loginForm.jsp", request, response);
				}
				
			}
			} catch (Exception e) {
				System.out.println("�Ƚ��û���Ϣʧ��:"+e.getMessage());
			} 
		
	}





	/**   
	 * @Title: queryHandle   
	 * @Description: TODO  
	 * @param: @param user
	 * @param: @param request
	 * @param: @param response      
	 * @return: void      
	 * @throws   
	 */  
	private void queryHandle(User user, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<User> users=userService.findUserByUserNameOrStateLike(user);
			//�����ݷŵ����������
			request.setAttribute("users", users);
		} catch (Exception e) {
			System.out.println("ģ�������û�ʧ��");
		}finally{
			//����ҳ����ת
			try {
				jumpPage("/WEB-INF/jsp/user/user.jsp", request, response);
			} catch (Exception e) {
				 System.out.println("��תҳ��ʧ��:"+e.getMessage());
			}
		}
	}





	/**   
	 * @Title: updateJspHandle   
	 * @Description: TODO  
	 * @param: @param user
	 * @param: @param request
	 * @param: @param response      
	 * @return: void      
	 * @throws   
	 */  
	private void updateJspHandle(User user, HttpServletRequest request, HttpServletResponse response) {
		//ͨ��id��ѯ�û�
		try {
			User user2=userService.findUserById(user);
			//�����ݷŵ����������ȥ
			request.setAttribute("user",user2);
			//������ת���޸����ݵ�ҳ��ȥ
			jumpPage("/WEB-INF/jsp/user/showUpdateUser.jsp", request, response);
		} catch (Exception e) {
			System.out.println("ͨ��id�����û�ʧ��...");
			try {
				jumpPage("/users?method=userFindAll", request, response);
			} catch (Exception e1) {
				System.out.println("ͨ��id�����û�ʧ��:"+e1.getMessage());
			} 
		}
		
	}





	/**   
	 * @Title: deleteHandle   
	 * @Description: TODO  
	 * @param: @param user
	 * @param: @param request
	 * @param: @param response      
	 * @return: void      
	 * @throws   
	 */  
	private void deleteHandle(User user, HttpServletRequest request, HttpServletResponse response) {
		//��ȡҪɾ�����ݵ�id
				System.out.println("Ҫɾ�����ݵ�id��:"+user.getuId());
				try {
					//����ҵ���߼�,�ж�ɾ�����ݵĺϷ���
					boolean b=judgeDeleteValidity(user,request);
					if(b){
						//��ʾ�û��Ϸ�,��ת���Ǹ���ѯҳ��
						userService.deleteUserById(user);
					} 
				}catch (SQLException e) {
					System.out.println("ɾ�����ݳ���������....");
				}finally{
					//��תҳ��
					try {
						jumpPage("/users?method=userFindAll", request, response);
					} catch (ServletException | IOException e) {
						System.out.print("��תʧ��");
					}
					
				}
		
	}





	/**   
	 * @Title: judgeDeleteValidity   
	 * @Description: TODO  
	 * @param: @param user
	 * @param: @param request
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */  
	private boolean judgeDeleteValidity(User user, HttpServletRequest request) {
		//�жϵ�ǰɾ�Ĺ���Ա(�û�)�ǲ����Լ�-----user2��ʾ��ǰ�ڲ����Ĺ���Ա
		User user2=(User)request.getSession().getAttribute("user");
		//�����ʾ���в���
		if(user.getuId().intValue()==user2.getuId().intValue()){
			return false;
		}
		
		return true;
	}





	/**
	 * @throws IOException 
	 * @throws ServletException    
	 * @Title: addUserHandle   
	 * @Description: TODO  
	 * @param: @param user
	 * @param: @param request
	 * @param: @param response      
	 * @return: void      
	 * @throws   
	 */  
	private void addUserHandle(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����service
		try {
					
					//��һ��:���ʱ��
					user.setuCreateTime(new Date());
					
					userService.insertUser(user);
					//�ڶ���:��ת��ҳ��(��ѯ���е�ҳ��)
					jumpPage("/users?method=userFindAll", request, response);
				} catch (Exception e) {
				    System.out.println("����û�ʧ��....");
				    //��ת������û���ҳ��
				    jumpPage("/WEB-INF/jsp/user/showAddUser.jsp", request, response);
				}
		
	}





	/**
	 * @throws IOException 
	 * @throws ServletException    
	 * @Title: findUserAllHandle   
	 * @Description: TODO  
	 * @param: @param request
	 * @param: @param response      
	 * @return: void      
	 * @throws   
	 */  
	private void findUserAllHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//��ѯ���õ��û�
			List<User> users=userService.findUserAll();
			//�����ݷŵ������
			request.setAttribute("users", users);
		} catch (SQLException e) {
			System.out.println("��ѯ�����û���������");
		}
			//ͨ��������ʾҳ��
			jumpPage("/WEB-INF/jsp/user/user.jsp", request, response);
		
	}





	/**
	 * @throws IOException 
	 * @throws ServletException    
	 * @Title: cancerLoginHadle   
	 * @Description: TODO  
	 * @param: @param request
	 * @param: @param response      
	 * @return: void      
	 * @throws   
	 */  
	private void cancerLoginHadle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��һ��:��session���-----��������****
		request.getSession().removeAttribute("user");
		//�ڶ���:��ת����½ҳ��
		jumpPage("/WEB-INF/jsp/loginForm.jsp", request, response);
		
	}





	private void login(User user, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
        //����ҵ���߼�
		try {
			//�ӷ�������ѯ�����Ķ���
			User user2=userService.findUserByUserLoginNameAndPwd(user);
           //˵���ɹ���...
			//�����ݷŵ�session��������������ȥ
			request.getSession().setAttribute("user",user2);
			//ʵ��ҳ�����ת
			jumpPage("/WEB-INF/jsp/main.jsp",request,response);
		} catch (Exception e) {
			e.printStackTrace();
			//˵��ʧ�ܵ�...
			//��ת����½ҳ��ȥ
			jumpPage("/WEB-INF/jsp/loginForm.jsp", request, response);
		}
	}
	
		
	/**
	 * @throws IOException 
	 * @throws ServletException  ��½����
	 * @Title: loginHandle   
	 * @Description: TODO  
	 * @param: @param request
	 * @param: @param response
	 * @param: @param user      
	 * @return: void      
	 * @throws   
	 */  
	private void loginHandle(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
		try {
			login(user,request,response);
		} catch (IOException e) {
			System.out.println("��½����bug...");
		}
	}
	
	
	
	
	
	
	
	/**
	 * @throws IOException 
	 * @throws ServletException  ʵ��ҳ�����ת (ת��)
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
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
