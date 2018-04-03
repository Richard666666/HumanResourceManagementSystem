/**  
 *
 * @Title:  NoticeServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��9�� ����4:15:04   
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
 * @author: ���  
 * @date:   2018��1��9�� ����4:15:04   
 * @version V1.0 
 * 
 */
public class NoticeServlet extends HttpServlet {
	//ά���û���ҵ���߼�����
	private INoticeService noticeService=null;
	//ͨ���ù���������Լ�ռ䣿-----------�����Ǹ�����----ͨ��objectUtils�ö���
	public NoticeServlet() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		noticeService=(INoticeService) ObjectUtils.getObject("noticeService");
		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��һ��:��ȡ����ķ���
		String methodName=request.getParameter("method");
		
		//�ڶ���:ʵ�����ݵ��Զ���װ
		Notice notice=null;
		try {
			notice=ObjectWrapperUtils.getObject(request, Notice.class);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.out.println("��װ����ʧ��");
		}
			//�ж�
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
		 if("noticeFindAll".equals(methodName)) {  //��ѯ���еĲ���
				findNoticeAllHandle(request, response);
			}else if("addNoticeJsp".equals(methodName)) {  //˵������Ӳ��ŵ�ҳ��(���)
				jumpPage("/WEB-INF/jsp/notice/showAddNotice.jsp", request, response);
			}else if("insertNotice".equals(methodName)) {    //˵��������û�
				addNoticeHandle(notice,request,response);
			}else if("delete".equals(methodName)) {
				deleteHandle(notice,request,response);
			}else if("updateJsp".equals(methodName)) {    //˵�����ֻ��ͨ��Servlet��JSPҳ�������ת����
				//jumpPage("/WEB-INF/jsp/user/showUpdateUser.jsp", request, response);
				updateJspHandle(notice,request,response);
			}else if("update".equals(methodName)) {       //˵���Ǹ����û�
				 updateNoticeHandle(notice,request,response);
			}else if("query".equals(methodName)) {        //�����ʾ���ǲ�ѯ
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
			//�����ݷŵ����������
			request.setAttribute("notices", notices);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ģ�������û�ʧ��");
		}finally{
			//����ҳ����ת
			try {
				jumpPage("/WEB-INF/jsp/notice/notice.jsp", request, response);
			} catch (Exception e) {
				 System.out.println("��תҳ��ʧ��:"+e.getMessage());
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
		//����ҵ���߼����������û�
		 try {
			noticeService.updateNoticeById(notice);
		
			//��ת����ѯҳ��
			jumpPage("/notice?method=noticeFindAll", request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�����û�ʧ��....");
			try {
				jumpPage("/notice?method=updateJsp", request, response);
			} catch (Exception e1) {
				System.out.println("�����û�ҳ��ʧ��:"+e1.getMessage());
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
		//ͨ��id��ѯ����
		try {
			Notice notice2=noticeService.findNoticeById(notice);
			//�����ݷŵ����������ȥ
			request.setAttribute("notice",notice2);
			//������ת���޸����ݵ�ҳ��ȥ
			jumpPage("/WEB-INF/jsp/notice/showUpdateNotice.jsp", request, response);
		} catch (Exception e) {
			System.out.println("ͨ��id���ҹ���ʧ��...");
			try {
				jumpPage("/notice?method=noticeFindAll", request, response);
			} catch (Exception e1) {
				System.out.println("ͨ��id���ҹ���ʧ��:"+e1.getMessage());
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
		//��ȡҪɾ�����ݵ�id
		System.out.println("Ҫɾ�����ݵ�id��:"+notice.getnId());
		try {
			//����ҵ���߼�,ɾ������
			
				noticeService.deleteNoticeById(notice);
			
		}catch (Exception e) {
			System.out.println("ɾ�����ݳ���������....");
		}finally{
			//��תҳ��
			try {
				jumpPage("/notice?method=noticeFindAll", request, response);
			} catch (ServletException | IOException e) {
				System.out.print("��תʧ��");
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
			//ͨ��service��������
			noticeService.insertNotice(notice);
			//�ڶ���:��ת��ҳ��(��ѯ���е�ҳ��)
			jumpPage("/notice?method=noticeFindAll", request, response);
		} catch (Exception e) {
			e.printStackTrace();
		    System.out.println("��ӹ���ʧ��....");
		    //��ת����ӹ����ҳ��
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
			//��ѯ���õ��û�
			List<Notice> notices=noticeService.findNoticeAll();
			//�����ݷŵ������
			request.setAttribute("notices", notices);
		} catch (SQLException e) {
			System.out.println("��ѯ���ò��ų�������");
		}
			//ͨ��������ʾҳ��
			jumpPage("/WEB-INF/jsp/notice/notice.jsp", request, response);
		
	}
	  
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	

}
