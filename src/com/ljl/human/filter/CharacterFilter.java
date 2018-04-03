/**  
 *
 * @Title:  CharacterFilter.java   
 * @Package com.ljl.human.filter   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��9�� ����8:06:14   
 * @version V1.0 
 * 
 */
package com.ljl.human.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**  
 *�������������������----
 * @Title:  CharacterFilter.java   
 * @Package com.ljl.human.filter   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��9�� ����8:06:14   
 * @version V1.0 
 * 
 */
public class CharacterFilter implements Filter {
	
	
	/**   
	 * <p>Title: init</p>   
	 * <p>Description: </p>   
	 * @param arg0
	 * @throws ServletException   
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)   
	 */  
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}
	/**   
	 * <p>Title: doFilter</p>   
	 * <p>Description: </p>   
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws IOException
	 * @throws ServletException   
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)   
	 */  
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//��һ��:ת����HttpServletrequest
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		//�ڶ���:������󷽷�
		String requestMethodName=req.getMethod();
		//������:�ȸ㶨��Ӧ�ı�������
		response.setContentType("text/html;charset=utf-8");
		//���Ĳ�:ʹ�á����������������
		HttpServletRequest request2=(HttpServletRequest) Proxy.newProxyInstance(HttpServletRequest.class.getClassLoader(), new Class[]{HttpServletRequest.class}, new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//�ж�����ķ�����ʲô��������
				String reqName=method.getName();
				
				if("getParameter".equalsIgnoreCase(reqName)){//˵�������
					if("GET".equalsIgnoreCase(requestMethodName)){
						//ȡ����ǰ�����������ֵ����ת��
						String val=(String) method.invoke(req, args);
						//��ԭʼ��ֵ�Ƚ����ٱ���
						/*val=new String(val.getBytes("ISO-8859-1"), "UTF-8");*/
						return val;
					}else if("POST".equalsIgnoreCase(requestMethodName)){
						req.setCharacterEncoding("UTF-8");
						return method.invoke(req, args);
					}
				}else {
						//���������Ҫ���ض����ôֱ�ӷ���
						return method.invoke(req, args);
					}
					
				
				return null;
			}
		});
		//���ս��з���
		chain.doFilter(request2, resp);	
		
	}
	/**   
	 * <p>Title: destroy</p>   
	 * <p>Description: </p>      
	 * @see javax.servlet.Filter#destroy()   
	 */  
	@Override
	public void destroy() {
		
		
	}





	
	}


