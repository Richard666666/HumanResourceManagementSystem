/**  
 *
 * @Title:  IndexServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��5�� ����2:02:09   
 * @version V1.0 
 * 
 */
package com.ljl.human.control;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**  
 *
 * @Title:  IndexServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��5�� ����2:02:09   
 * @version V1.0 
 * 
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��һ��:��ȡ����
		String method=request.getParameter("method");
		jumpment(method,request,response);
	}
	
	 
	/**
	 * @throws IOException 
	 * @throws ServletException    
	 * @Title: jumpment   
	 * @Description: TODO  
	 * @param: @param method
	 * @param: @param request
	 * @param: @param response      
	 * @return: void      
	 * @throws   
	 */  
	private void jumpment(String method, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if("left".equals(method)) {//˵����Ҫ������ߵ�ҳ��
		    jumpPage("/WEB-INF/jsp/left.jsp", request, response);
		}else if("right".equals(method)) {  //˵��ת�����ұߵ����ҳ��
			jumpPage("/WEB-INF/jsp/right.jsp", request, response);
		}else if("top".equals(method)) {   //˵��Ӧ��ת����������jsp
			jumpPage("/WEB-INF/jsp/top.jsp", request, response);
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


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	

}
