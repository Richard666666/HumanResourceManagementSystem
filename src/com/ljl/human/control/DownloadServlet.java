/**  
 *
 * @Title:  DownloadServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月5日 下午12:42:16   
 * @version V1.0 
 * 
 */
package com.ljl.human.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ljl.human.pojo.Department;
import com.ljl.human.pojo.Download;
import com.ljl.human.pojo.User;
import com.ljl.human.service.IDownloadService;
import com.ljl.human.service.IUserService;
import com.ljl.human.utils.LoginUserUtils;
import com.ljl.human.utils.ObjectUtils;
import com.ljl.human.utils.ObjectWrapperUtils;

/**  
 *
 * @Title:  DownloadServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月5日 下午12:42:16   
 * @version V1.0 
 * 
 */
@MultipartConfig
public class DownloadServlet extends HttpServlet{
	//在这里维护DownloadService对象
		private IDownloadService downloadService=null;
		private IUserService userService=null;
		public DownloadServlet() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
			downloadService=(IDownloadService) ObjectUtils.getObject("downloadService");
			userService=(IUserService) ObjectUtils.getObject("userService");
		}
		
	
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//获取请求的方法
			String methodName=request.getParameter("method");
			//请求参数的自动封装
			Download download=null;
			try {
				download=ObjectWrapperUtils.getObject(request, Download.class);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
				System.out.println("封装对象失败");
			}
			
			
			
			
			
			try {
				//判断请求的方法是什么?
				if("uploadFileJsp".equals(methodName)) {   //这个表示的是要跳转到上传文件的这个页面上去
					jumpPage("/WEB-INF/jsp/document/showAddDocument.jsp", request, response);
					
					
				}else if("upload".equals(methodName)) {
					//从新对数据进行封装
					download=wrapperObject(request);
					//调用业务逻辑进行插入
					downloadService.insertDownload(download);
					//跳转到查询所有的下载地址的页面
					jumpPage("/download?method=findDownloadAll", request, response);
					
				
					
					
					
				}else if("findDownloadAll".equals(methodName)) {  //这里就是查询所有的下载文件的地方
					 //查询所有的
					List<Download> downloads=downloadService.findDownloadAll();
					//通过download找到自己的用户
					downloads=getDownloadAll(downloads);
					//将数据放到域对象
					request.setAttribute("downloads",downloads);
					
					jumpPage("/WEB-INF/jsp/document/document.jsp", request, response);
					
					
				}else if("down".equals(methodName)) {   //这里说民是下载文件
					//把路径取出来
					String filePath=request.getParameter("filePath");
					//接下来构建下载路径
					String fileDesPath=request.getRealPath("/")+filePath;
					//通过路径获取这个文件的名字
					String fileName=fileDesPath.substring(fileDesPath.lastIndexOf("\\")+1);
					fileDown(response, fileDesPath, fileName);
					
					
					
				}else if("updateJsp".equals(methodName)){
					
					updateJspHandle(download,request,response);
					
					
				}else if("update".equals(methodName)){
					
					download.setDoCreateTime(new Date());
					User user = (User) request.getSession().getAttribute("user");
					download.setuId(user.getuId());
					try {
						downloadService.updateDownloadById(download);
						 wrapperObject(request);
						jumpPage("/download?method=findDownloadAll", request, response);
					} catch (Exception e) {
						System.out.println("更新文档异常" + e.getMessage());
					}
				}else if("delete".equals(methodName)) {
					deleteHandle(download,request,response);
				}else if("query".equals(methodName)) {        //表示的是查询
					 queryHandle(download,request,response);
				}

				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("请求参数封装出现了问题"+e.getMessage());
			} 
			
		}
	
	


		/**   
		 * @Title: queryHandle   
		 * @Description: TODO  
		 * @param: @param download
		 * @param: @param request
		 * @param: @param response      
		 * @return: void      
		 * @throws   
		 */  
		private void queryHandle(Download download, HttpServletRequest request, HttpServletResponse response) {
			try {
				List<Download> downloads=downloadService.findDownloadByTitle(download);
				//将数据放到域对象里面
				request.setAttribute("downloads", downloads);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("模糊搜索用户失败");
			}finally{
				//进行页面跳转
				try {
					jumpPage("/WEB-INF/jsp/document/document.jsp", request, response);
				} catch (Exception e) {
					 System.out.println("跳转页面失败:"+e.getMessage());
				}
			}
			
		}


		/**   
		 * @Title: deleteHandle   
		 * @Description: TODO  
		 * @param: @param download
		 * @param: @param request
		 * @param: @param response      
		 * @return: void      
		 * @throws   
		 */  
		private void deleteHandle(Download download, HttpServletRequest request, HttpServletResponse response) {
			//获取要删除数据的id
			System.out.println("要删除数据的id是:"+download.getDoId());
			try {
				//调用业务逻辑,删除数据
				
					downloadService.deleteDownloadById(download);
				
			}catch (SQLException e) {
				e.printStackTrace();
				System.out.println("删除数据出现问题了....");
			}finally{
				//跳转页面
				try {
					jumpPage("/download?method=findDownloadAll", request, response);
				} catch (ServletException | IOException e) {
					System.out.print("跳转失败");
				}
				
			}
			
		}


		/**   
		 * @Title: updateJspHandle   
		 * @Description: TODO  
		 * @param: @param download1
		 * @param: @param request
		 * @param: @param response      
		 * @return: void      
		 * @throws   
		 */  
		private void updateJspHandle(Download download, HttpServletRequest request, HttpServletResponse response) {
			//通过id查询用户
			try {
				Download download2=downloadService.findDownloadById(download);
				//将数据放到域对象里面去
				request.setAttribute("download",download2);
				//下面跳转到修改数据的页面去
				jumpPage("/WEB-INF/jsp/document/showUpdateDocument.jsp", request, response);
			} catch (Exception e) {
				System.out.println("通过id查找用户失败...");
				try {
					jumpPage("/download?method=downloadFindAll", request, response);
				} catch (Exception e1) {
					System.out.println("通过id查找用户失败:"+e1.getMessage());
				} 
			}
			
		}


		/**文件下载
		 * @throws IOException    
		 * @Title: fileDown   
		 * @Description: TODO  
		 * @param: @param response
		 * @param: @param fileDesPath
		 * @param: @param fileName      
		 * @return: void      
		 * @throws   
		 */  
		private void fileDown(HttpServletResponse response, String fileDesPath, String fileName) throws IOException {
			//下面就调用方法进行下载了
			OutputStream out=response.getOutputStream();
			//构建元文件的地址
			File desFile=new File(fileDesPath);
			InputStream in=new FileInputStream(desFile);
			//设置那个请求的头
			response.setHeader("content-disposition","attachment;filename="+new String(fileName.getBytes("UTF-8"),"ISO8859-1"));
			int length;
			byte[] buf=new byte[1024];
			while((length=in.read(buf))!=-1) {
				out.write(buf, 0, length);
			}
			//关闭这个流
			in.close();
			out.close();
			
		}


		/**获得全部的文件
		 * @throws SQLException    
		 * @Title: getDownloadAll   
		 * @Description: TODO  
		 * @param: @param downloads
		 * @param: @return      
		 * @return: List<Download>      
		 * @throws   
		 */  
		private List<Download> getDownloadAll(List<Download> downloads) throws SQLException {
			 List<Download> downloads2=new ArrayList<Download>();
		    	//这里就需要通过那个id来获取名字
		    	for (int i = 0; i < downloads.size(); i++) {
		    		//这里就可以找到id了
		    		int uId=downloads.get(i).getuId();
		    		User user=userService.findUserById(uId);
		    		downloads.get(i).setUser(user);
		    		downloads2.add(downloads.get(i));
				}
				return downloads2;
		}


		/**封装对象
		 * @throws Exception    
		 * @Title: wrapperObject   
		 * @Description: TODO  
		 * @param: @param request
		 * @param: @return      
		 * @return: Download      
		 * @throws   
		 */  
		
		private Download wrapperObject(HttpServletRequest request) throws Exception {
			//解决请求的编码问题
			Part part=request.getPart("file");
			
			String doTitle=request.getParameter("doTitle");
			String doDes=request.getParameter("doDes");
			
			
			String header=part.getHeader("content-disposition");
			String filenameAll=header.substring(header.indexOf("filename"));
			//最终获取文件的名字
			String fileNameErro=filenameAll.substring(filenameAll.lastIndexOf("\\")+1,filenameAll.lastIndexOf("\""));
			String filename=fileNameErro.substring(fileNameErro.lastIndexOf("\"")+1);
			//接下来通过流写出去
			//part.write();
			String path=this.getServletContext().getRealPath("\\upload")+File.separator+filename;
	        //将文件写到目的地
			part.write(path);
			//这里应该将数据插入到数据库
			return wrapperDownload(request,doTitle, doDes, filename);
		}


		/**   
		 * @Title: wrapperDownload   
		 * @Description: TODO  
		 * @param: @param request
		 * @param: @param title
		 * @param: @param remark
		 * @param: @param filename
		 * @param: @return      
		 * @return: Download      
		 * @throws   
		 */  
		private Download wrapperDownload(HttpServletRequest request, String doTitle, String doDes, String filename) {
			Download download=new Download();
			download.setDoTitle(doTitle);
			download.setDoDes(doDes);
			download.setDoCreateTime(new Date());
			download.setFilePath("/upload"+File.separator+filename);
			download.setuId(LoginUserUtils.getLoginUserId(request));
			return download;
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
			request.getRequestDispatcher(path).forward(request, response);
			
		}


		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}



}
	
	
	


