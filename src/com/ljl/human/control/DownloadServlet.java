/**  
 *
 * @Title:  DownloadServlet.java   
 * @Package com.ljl.human.control   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��5�� ����12:42:16   
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
 * @author: ���  
 * @date:   2018��1��5�� ����12:42:16   
 * @version V1.0 
 * 
 */
@MultipartConfig
public class DownloadServlet extends HttpServlet{
	//������ά��DownloadService����
		private IDownloadService downloadService=null;
		private IUserService userService=null;
		public DownloadServlet() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
			downloadService=(IDownloadService) ObjectUtils.getObject("downloadService");
			userService=(IUserService) ObjectUtils.getObject("userService");
		}
		
	
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//��ȡ����ķ���
			String methodName=request.getParameter("method");
			//����������Զ���װ
			Download download=null;
			try {
				download=ObjectWrapperUtils.getObject(request, Download.class);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
				System.out.println("��װ����ʧ��");
			}
			
			
			
			
			
			try {
				//�ж�����ķ�����ʲô?
				if("uploadFileJsp".equals(methodName)) {   //�����ʾ����Ҫ��ת���ϴ��ļ������ҳ����ȥ
					jumpPage("/WEB-INF/jsp/document/showAddDocument.jsp", request, response);
					
					
				}else if("upload".equals(methodName)) {
					//���¶����ݽ��з�װ
					download=wrapperObject(request);
					//����ҵ���߼����в���
					downloadService.insertDownload(download);
					//��ת����ѯ���е����ص�ַ��ҳ��
					jumpPage("/download?method=findDownloadAll", request, response);
					
				
					
					
					
				}else if("findDownloadAll".equals(methodName)) {  //������ǲ�ѯ���е������ļ��ĵط�
					 //��ѯ���е�
					List<Download> downloads=downloadService.findDownloadAll();
					//ͨ��download�ҵ��Լ����û�
					downloads=getDownloadAll(downloads);
					//�����ݷŵ������
					request.setAttribute("downloads",downloads);
					
					jumpPage("/WEB-INF/jsp/document/document.jsp", request, response);
					
					
				}else if("down".equals(methodName)) {   //����˵���������ļ�
					//��·��ȡ����
					String filePath=request.getParameter("filePath");
					//��������������·��
					String fileDesPath=request.getRealPath("/")+filePath;
					//ͨ��·����ȡ����ļ�������
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
						System.out.println("�����ĵ��쳣" + e.getMessage());
					}
				}else if("delete".equals(methodName)) {
					deleteHandle(download,request,response);
				}else if("query".equals(methodName)) {        //��ʾ���ǲ�ѯ
					 queryHandle(download,request,response);
				}

				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("���������װ����������"+e.getMessage());
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
				//�����ݷŵ����������
				request.setAttribute("downloads", downloads);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ģ�������û�ʧ��");
			}finally{
				//����ҳ����ת
				try {
					jumpPage("/WEB-INF/jsp/document/document.jsp", request, response);
				} catch (Exception e) {
					 System.out.println("��תҳ��ʧ��:"+e.getMessage());
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
			//��ȡҪɾ�����ݵ�id
			System.out.println("Ҫɾ�����ݵ�id��:"+download.getDoId());
			try {
				//����ҵ���߼�,ɾ������
				
					downloadService.deleteDownloadById(download);
				
			}catch (SQLException e) {
				e.printStackTrace();
				System.out.println("ɾ�����ݳ���������....");
			}finally{
				//��תҳ��
				try {
					jumpPage("/download?method=findDownloadAll", request, response);
				} catch (ServletException | IOException e) {
					System.out.print("��תʧ��");
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
			//ͨ��id��ѯ�û�
			try {
				Download download2=downloadService.findDownloadById(download);
				//�����ݷŵ����������ȥ
				request.setAttribute("download",download2);
				//������ת���޸����ݵ�ҳ��ȥ
				jumpPage("/WEB-INF/jsp/document/showUpdateDocument.jsp", request, response);
			} catch (Exception e) {
				System.out.println("ͨ��id�����û�ʧ��...");
				try {
					jumpPage("/download?method=downloadFindAll", request, response);
				} catch (Exception e1) {
					System.out.println("ͨ��id�����û�ʧ��:"+e1.getMessage());
				} 
			}
			
		}


		/**�ļ�����
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
			//����͵��÷�������������
			OutputStream out=response.getOutputStream();
			//����Ԫ�ļ��ĵ�ַ
			File desFile=new File(fileDesPath);
			InputStream in=new FileInputStream(desFile);
			//�����Ǹ������ͷ
			response.setHeader("content-disposition","attachment;filename="+new String(fileName.getBytes("UTF-8"),"ISO8859-1"));
			int length;
			byte[] buf=new byte[1024];
			while((length=in.read(buf))!=-1) {
				out.write(buf, 0, length);
			}
			//�ر������
			in.close();
			out.close();
			
		}


		/**���ȫ�����ļ�
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
		    	//�������Ҫͨ���Ǹ�id����ȡ����
		    	for (int i = 0; i < downloads.size(); i++) {
		    		//����Ϳ����ҵ�id��
		    		int uId=downloads.get(i).getuId();
		    		User user=userService.findUserById(uId);
		    		downloads.get(i).setUser(user);
		    		downloads2.add(downloads.get(i));
				}
				return downloads2;
		}


		/**��װ����
		 * @throws Exception    
		 * @Title: wrapperObject   
		 * @Description: TODO  
		 * @param: @param request
		 * @param: @return      
		 * @return: Download      
		 * @throws   
		 */  
		
		private Download wrapperObject(HttpServletRequest request) throws Exception {
			//�������ı�������
			Part part=request.getPart("file");
			
			String doTitle=request.getParameter("doTitle");
			String doDes=request.getParameter("doDes");
			
			
			String header=part.getHeader("content-disposition");
			String filenameAll=header.substring(header.indexOf("filename"));
			//���ջ�ȡ�ļ�������
			String fileNameErro=filenameAll.substring(filenameAll.lastIndexOf("\\")+1,filenameAll.lastIndexOf("\""));
			String filename=fileNameErro.substring(fileNameErro.lastIndexOf("\"")+1);
			//������ͨ����д��ȥ
			//part.write();
			String path=this.getServletContext().getRealPath("\\upload")+File.separator+filename;
	        //���ļ�д��Ŀ�ĵ�
			part.write(path);
			//����Ӧ�ý����ݲ��뵽���ݿ�
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
	
	
	


