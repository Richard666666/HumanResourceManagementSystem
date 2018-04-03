/**  
 *
 * @Title:  NoticeServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月9日 下午4:15:04   
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

import com.ljl.human.Dao.INoticeDao;
import com.ljl.human.pojo.Department;
import com.ljl.human.pojo.Job;
import com.ljl.human.pojo.Notice;
import com.ljl.human.pojo.User;
import com.ljl.human.service.IJobService;
import com.ljl.human.service.INoticeService;
import com.ljl.human.utils.ObjectUtils;
import com.ljl.human.utils.ObjectWrapperUtils;

/**  
 *
 * @Title:  NoticeServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月9日 下午4:15:04   
 * @version V1.0 
 * 
 */
public class NoticeServlet extends HttpServlet {
	//维护用户的业务逻辑对象
	private INoticeService noticeService=null;
	//通过用构造器来节约空间？-----------这里是个问题----通过objectUtils拿对象
	public NoticeServlet() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		noticeService=(INoticeService) ObjectUtils.getObject("noticeService");
		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//第一步:获取请求的方法
		String methodName=request.getParameter("method");
		
		//第二步:实现数据的自动封装
		Notice notice=null;
		try {
			notice=ObjectWrapperUtils.getObject(request, Notice.class);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.out.println("封装对象失败");
		}
			//判断
			judgement(methodName, request, response, notice);
	
	}
	/**
	 * @throws IOException 
	 * @throws ServletException    
	 * @Title: judgement   
	 * @Description: TODO  
	 * @param: @param methodName
	 * @param: @param request
	 * @param: @param response
	 * @param: @param notice      
	 * @return: void      
	 * @throws   
	 */  
	private void judgement(String methodName, HttpServletRequest request, HttpServletResponse response, Notice notice) throws ServletException, IOException {
		 if("noticeFindAll".equals(methodName)) {  //查询所有的部门
				findNoticeAllHandle(request, response);
			}else if("addNoticeJsp".equals(methodName)) {  //说明是添加部门的页面(完成)
				jumpPage("/WEB-INF/jsp/notice/showAddNotice.jsp", request, response);
			}else if("insertNotice".equals(methodName)) {    //说明是添加用户
				addNoticeHandle(notice,request,response);
			}else if("delete".equals(methodName)) {
				deleteHandle(notice,request,response);
			}else if("updateJsp".equals(methodName)) {    //说明这个只是通过Servlet往JSP页面进行跳转而已
				//jumpPage("/WEB-INF/jsp/user/showUpdateUser.jsp", request, response);
				updateJspHandle(notice,request,response);
			}else if("update".equals(methodName)) {       //说明是更改用户
				 updateNoticeHandle(notice,request,response);
			}else if("query".equals(methodName)) {        //这个表示的是查询
				 queryHandle(notice,request,response);
			}
		
		
		
	}
	/**   
	 * @Title: queryHandle   
	 * @Description: TODO  
	 * @param: @param notice
	 * @param: @param request
	 * @param: @param response      
	 * @return: void      
	 * @throws   
	 */  
	private void queryHandle(Notice notice, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Notice> notices=noticeService.findNoticeByName(notice);
			//将数据放到域对象里面
			request.setAttribute("notices", notices);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("模糊搜索用户失败");
		}finally{
			//进行页面跳转
			try {
				jumpPage("/WEB-INF/jsp/notice/notice.jsp", request, response);
			} catch (Exception e) {
				 System.out.println("跳转页面失败:"+e.getMessage());
			}
		}
		
	}
	/**
	 * @throws IOException 
	 * @throws ServletException    
	 * @Title: updateNoticeHandle   
	 * @Description: TODO  
	 * @param: @param notice
	 * @param: @param request
	 * @param: @param response      
	 * @return: void      
	 * @throws   
	 */  
	private void updateNoticeHandle(Notice notice, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用业务逻辑类来更改用户
		 try {
			noticeService.updateNoticeById(notice);
		
			//跳转到查询页面
			jumpPage("/notice?method=noticeFindAll", request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("更改用户失败....");
			try {
				jumpPage("/notice?method=updateJsp", request, response);
			} catch (Exception e1) {
				System.out.println("更改用户页面失败:"+e1.getMessage());
			} 
			
		}
		
	}
	/**   
	 * @Title: updateJspHandle   
	 * @Description: TODO  
	 * @param: @param notice
	 * @param: @param request
	 * @param: @param response      
	 * @return: void      
	 * @throws   
	 */  
	private void updateJspHandle(Notice notice, HttpServletRequest request, HttpServletResponse response) {
		//通过id查询公告
		try {
			Notice notice2=noticeService.findNoticeById(notice);
			//将数据放到域对象里面去
			request.setAttribute("notice",notice2);
			//下面跳转到修改数据的页面去
			jumpPage("/WEB-INF/jsp/notice/showUpdateNotice.jsp", request, response);
		} catch (Exception e) {
			System.out.println("通过id查找公告失败...");
			try {
				jumpPage("/notice?method=noticeFindAll", request, response);
			} catch (Exception e1) {
				System.out.println("通过id查找公告失败:"+e1.getMessage());
			} 
		}
		
	}
	/**   
	 * @Title: deleteHandle   
	 * @Description: TODO  
	 * @param: @param notice
	 * @param: @param request
	 * @param: @param response      
	 * @return: void      
	 * @throws   
	 */  
	private void deleteHandle(Notice notice, HttpServletRequest request, HttpServletResponse response) {
		//获取要删除数据的id
		System.out.println("要删除数据的id是:"+notice.getnId());
		try {
			//调用业务逻辑,删除数据
			
				noticeService.deleteNoticeById(notice);
			
		}catch (Exception e) {
			System.out.println("删除数据出现问题了....");
		}finally{
			//跳转页面
			try {
				jumpPage("/notice?method=noticeFindAll", request, response);
			} catch (ServletException | IOException e) {
				System.out.print("跳转失败");
			}
			
		}
		
	}
	/**
	 * @throws IOException 
	 * @throws ServletException    
	 * @Title: addNoticeHandle   
	 * @Description: TODO  
	 * @param: @param notice
	 * @param: @param request
	 * @param: @param response      
	 * @return: void      
	 * @throws   
	 */  
	private void addNoticeHandle(Notice notice, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			notice.setnCreateTime(new Date());
			User user=(User) request.getSession().getAttribute("user");
			notice.setuId(user.getuId());
			//通过service层来增加
			noticeService.insertNotice(notice);
			//第二步:跳转到页面(查询所有的页面)
			jumpPage("/notice?method=noticeFindAll", request, response);
		} catch (Exception e) {
			e.printStackTrace();
		    System.out.println("添加公告失败....");
		    //跳转到添加公告的页面
		    jumpPage("/WEB-INF/jsp/notice/showAddNotice.jsp", request, response);
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
	 * @Title: findNoticeAllHandle   
	 * @Description: TODO  
	 * @param: @param request
	 * @param: @param response      
	 * @return: void      
	 * @throws   
	 */  
	private void findNoticeAllHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//查询所用的用户
			List<Notice> notices=noticeService.findNoticeAll();
			//将数据放到域对象
			request.setAttribute("notices", notices);
		} catch (SQLException e) {
			System.out.println("查询所用部门出现问题");
		}
			//通过调整显示页面
			jumpPage("/WEB-INF/jsp/notice/notice.jsp", request, response);
		
	}
	  
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	

}
