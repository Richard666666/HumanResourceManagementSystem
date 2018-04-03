/**  
 *
 * @Title:  IDownloadDao.java   
 * @Package com.ljl.human.Dao   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��6�� ����8:50:23   
 * @version V1.0 
 * 
 */
package com.ljl.human.Dao;

import java.sql.SQLException;
import java.util.List;

import com.ljl.human.pojo.Download;
import com.ljl.human.pojo.User;

/**  
 *
 * @Title:  IDownloadDao.java   
 * @Package com.ljl.human.Dao   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��6�� ����8:50:23   
 * @version V1.0 
 * 
 */
public interface IDownloadDao {
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
	 * @Title: findDepartmentByTitle   
	 * @Description: TODO  
	 * @param: @param download
	 * @param: @return      
	 * @return: List<Download>      
	 * @throws   
	 */  
	List<Download> findDepartmentByTitle(Download download) throws SQLException;
	
}
