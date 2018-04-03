
package com.ljl.human.service;

import java.sql.SQLException;
import java.util.List;

import com.ljl.human.pojo.Download;

/**  
 *
 * @Title:  IDownloadService.java   
 * @Package com.ljl.human.service   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��8�� ����8:26:11   
 * @version V1.0 
 * 
 */
public interface IDownloadService {
	/**
	 * @throws SQLException 
	 * ������������----------------��
	 * @Title: insertUser   
	 * @Description: TODO  
	 * @param: @param user      
	 * @return: void      
	 * @throws
	 */
	void insertDownload(Download download) throws SQLException;
	
	/**
	 * @throws SQLException 
	 * ͨ��idɾ����������-----------ɾ
	 * @Title: deleteDownloadById   
	 * @Description: TODO  
	 * @param: @param download      
	 * @return: void      
	 * @throws
	 */
	void deleteDownloadById(Download download) throws SQLException;
	
	/**
	 * @throws SQLException 
	 * ͨ��id�޸ı���-------------��
	 * @Title: updateDownloadById   
	 * @Description: TODO  
	 * @param: @param download      
	 * @return: void      
	 * @throws
	 */
	void updateDownloadById(Download download) throws SQLException;
	
	
	/**
	 * @throws SQLException 
	 * ͨ���������ı������ģ������-----��
	 * @Title: findDownloadByTitleLike   
	 * @Description: TODO  
	 * @param: @param download
	 * @param: @return      
	 * @return: List<Download>      
	 * @throws
	 */
	List<Download> findDownloadByTitleLike(Download download) throws SQLException;
		
	/**
	 * @throws SQLException 
	 * ��������-----------------��
	 * @Title: findDownloadAll   
	 * @Description: TODO  
	 * @param: @return      
	 * @return: List<Download>      
	 * @throws
	 */
	List<Download> findDownloadAll() throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: findDownloadById   
	 * @Description: TODO  
	 * @param: @param download1
	 * @param: @return      
	 * @return: Download      
	 * @throws   
	 */  
	Download findDownloadById(Download download) throws SQLException;

	/**
	 * @throws SQLException    
	 * @Title: findDownloadByTitle   
	 * @Description: TODO  
	 * @param: @param download
	 * @param: @return      
	 * @return: List<Download>      
	 * @throws   
	 */  
	List<Download> findDownloadByTitle(Download download) throws SQLException;



}
