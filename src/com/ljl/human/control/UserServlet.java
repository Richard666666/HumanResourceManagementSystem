/**  
 *
 * @Title:  UserServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月9日 下午2:16:02   
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
 *用户的servlet
 * @Title:  UserServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月9日 下午2:16:02   
 * @version V1.0 
 * 
 */
public class UserServlet extends HttpServlet {
	//维护用户的业务逻辑对象
	private IUserService userService=null;
	//通过用构造器来节约空间？-----------这里是个问题----通过objectUtils拿对象
	public UserServlet() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		userService=(IUserService) ObjectUtils.getObject("userService");
		
	}

	
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//第一步:获取请求的方法
			String methodName=request.getParameter("method");
			//第二步:实现数据的自动封装
			User user=null;
			try {
				user=ObjectWrapperUtils.getObject(request, User.class);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
				System.out.println("封装对象失败");
			}
			
				judgement(methodName, request, response, user);
	}
	
	
	
	
	
				
	
	//第三步:根据不同的请求方法做出相应的请求处理---
	private void judgement(String methodName, HttpServletRequest request, HttpServletResponse response,User user) throws ServletException, IOException{
		if("login".equals(methodName)) {   //说明用户是想实现登陆
			loginHandle(request, response, user);
		}else if("cancerLogin".equals(methodName)) {  //这个就是用来注销登陆的
			cancerLoginHadle(request, response);
		}else if("userFindAll".equals(methodName)) {  //说明应该查询所有的用户(完成)
			findUserAllHandle(request, response);
		}else if("addUserJsp".equals(methodName)) {  //说明是添加用户的页面(完成)
			jumpPage("/WEB-INF/jsp/user/showAddUser.jsp", request, response);
		}else if("addUser".equals(methodName)) {    //说明是添加用户
			addUserHandle(user,request,response);
		}else if("delete".equals(methodName)) {
			deleteHandle(user,request,response);
		}else if("updateJsp".equals(methodName)) {    //说明这个只是通过Servlet往JSP页面进行跳转而已
			//jumpPage("/WEB-INF/jsp/user/showUpdateUser.jsp", request, response);
			updateJspHandle(user,request,response);
		}else if("update".equals(methodName)) {       //说明是更改用户
			 updateUserHandle(user,request,response);
		}else if("query".equals(methodName)) {        //这个表示的是查询
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
		//调用业务逻辑类来更改用户
		 try {
			userService.updateUserById(user);
			compairUserInfo(user,request,response);
			//跳转到查询页面
			jumpPage("/users?method=userFindAll", request, response);
		} catch (SQLException e) {
			System.out.println("更改用户失败....");
			try {
				jumpPage("/users?method=updateJsp", request, response);
			} catch (Exception e1) {
				System.out.println("更改用户页面失败:"+e1.getMessage());
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
			//我要通过id去查询用户找到用户名和密码
				User userNew;
				userNew = userService.findUserById(user);
			   //更改过后的用户信息
			//这里获取session中的用户信息
			User userOld=(User) request.getSession().getAttribute("user");
			if(user.getuId().intValue()==userOld.getuId().intValue()){
				if(!(userNew.getuLoginName().equals(userOld.getuLoginName()))||!(userNew.getuPwd().equals(userOld.getuPwd()))){
					//表示应该从新进行登录，清空用户信息
					request.getSession().removeAttribute("user");
					//跳转到登录页面上去
					jumpPage("/WEB-INF/jsp/loginForm.jsp", request, response);
				}
				
			}
			} catch (Exception e) {
				System.out.println("比较用户信息失败:"+e.getMessage());
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
			//将数据放到域对象里面
			request.setAttribute("users", users);
		} catch (Exception e) {
			System.out.println("模糊搜索用户失败");
		}finally{
			//进行页面跳转
			try {
				jumpPage("/WEB-INF/jsp/user/user.jsp", request, response);
			} catch (Exception e) {
				 System.out.println("跳转页面失败:"+e.getMessage());
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
		//通过id查询用户
		try {
			User user2=userService.findUserById(user);
			//将数据放到域对象里面去
			request.setAttribute("user",user2);
			//下面跳转到修改数据的页面去
			jumpPage("/WEB-INF/jsp/user/showUpdateUser.jsp", request, response);
		} catch (Exception e) {
			System.out.println("通过id查找用户失败...");
			try {
				jumpPage("/users?method=userFindAll", request, response);
			} catch (Exception e1) {
				System.out.println("通过id查找用户失败:"+e1.getMessage());
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
		//获取要删除数据的id
				System.out.println("要删除数据的id是:"+user.getuId());
				try {
					//调用业务逻辑,判断删除数据的合法性
					boolean b=judgeDeleteValidity(user,request);
					if(b){
						//表示用户合法,跳转到那个查询页面
						userService.deleteUserById(user);
					} 
				}catch (SQLException e) {
					System.out.println("删除数据出现问题了....");
				}finally{
					//跳转页面
					try {
						jumpPage("/users?method=userFindAll", request, response);
					} catch (ServletException | IOException e) {
						System.out.print("跳转失败");
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
		//判断当前删的管理员(用户)是不是自己-----user2表示当前在操作的管理员
		User user2=(User)request.getSession().getAttribute("user");
		//这个表示进行拆箱
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
		//调用service
		try {
					
					//第一步:添加时间
					user.setuCreateTime(new Date());
					
					userService.insertUser(user);
					//第二步:跳转到页面(查询所有的页面)
					jumpPage("/users?method=userFindAll", request, response);
				} catch (Exception e) {
				    System.out.println("添加用户失败....");
				    //跳转到添加用户的页面
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
			//查询所用的用户
			List<User> users=userService.findUserAll();
			//将数据放到域对象
			request.setAttribute("users", users);
		} catch (SQLException e) {
			System.out.println("查询所用用户出现问题");
		}
			//通过调整显示页面
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
		//第一步:将session清空-----这里的理解****
		request.getSession().removeAttribute("user");
		//第二步:跳转到登陆页面
		jumpPage("/WEB-INF/jsp/loginForm.jsp", request, response);
		
	}





	private void login(User user, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
        //调用业务逻辑
		try {
			//从服务器查询回来的对象
			User user2=userService.findUserByUserLoginNameAndPwd(user);
           //说明成功的...
			//将数据放到session域对象域对象里面去
			request.getSession().setAttribute("user",user2);
			//实现页面的跳转
			jumpPage("/WEB-INF/jsp/main.jsp",request,response);
		} catch (Exception e) {
			e.printStackTrace();
			//说明失败的...
			//跳转到登陆页面去
			jumpPage("/WEB-INF/jsp/loginForm.jsp", request, response);
		}
	}
	
		
	/**
	 * @throws IOException 
	 * @throws ServletException  登陆界面
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
			System.out.println("登陆遇到bug...");
		}
	}
	
	
	
	
	
	
	
	/**
	 * @throws IOException 
	 * @throws ServletException  实现页面的跳转 (转发)
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
