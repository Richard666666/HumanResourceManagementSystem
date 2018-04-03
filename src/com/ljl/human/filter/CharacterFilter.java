/**  
 *
 * @Title:  CharacterFilter.java   
 * @Package com.ljl.human.filter   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月9日 下午8:06:14   
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
 *处理请求参数乱码问题----
 * @Title:  CharacterFilter.java   
 * @Package com.ljl.human.filter   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月9日 下午8:06:14   
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
		//第一步:转换成HttpServletrequest
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		//第二步:获得请求方法
		String requestMethodName=req.getMethod();
		//第三步:先搞定响应的编码问题
		response.setContentType("text/html;charset=utf-8");
		//第四步:使用《代理》解决编码问题
		HttpServletRequest request2=(HttpServletRequest) Proxy.newProxyInstance(HttpServletRequest.class.getClassLoader(), new Class[]{HttpServletRequest.class}, new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//判断请求的方法是什么？？？？
				String reqName=method.getName();
				
				if("getParameter".equalsIgnoreCase(reqName)){//说明请求的
					if("GET".equalsIgnoreCase(requestMethodName)){
						//取出当前的请求参数的值进行转换
						String val=(String) method.invoke(req, args);
						//将原始的值先解码再编码
						/*val=new String(val.getBytes("ISO-8859-1"), "UTF-8");*/
						return val;
					}else if("POST".equalsIgnoreCase(requestMethodName)){
						req.setCharacterEncoding("UTF-8");
						return method.invoke(req, args);
					}
				}else {
						//如果不是需要拦截额方法那么直接放行
						return method.invoke(req, args);
					}
					
				
				return null;
			}
		});
		//最终进行放行
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


