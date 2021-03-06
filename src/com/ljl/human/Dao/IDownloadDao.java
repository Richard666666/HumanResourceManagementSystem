/**  
 *
 * @Title:  IDownloadDao.java   
 * @Package com.ljl.human.Dao   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月6日 下午8:50:23   
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
 * @author: 李建良  
 * @date:   2018年1月6日 下午8:50:23   
 * @version V1.0 
 * 
 */
public interface IDownloadDao {
	/**
	 * @throws SQLException 
	 * 增加下载中心----------------增
	 * @Title: insertUser   
	 * @Description: TODO  
	 * @param: @param user      
	 * @return: void      
	 * @throws
	 */
	void insertDownload(Download download) throws SQLException;
	
	/**
	 * @throws SQLException 
	 * 通过id删除下载中心-----------删
	 * @Title: deleteDownloadById   
	 * @Description: TODO  
	 * @param: @param download      
	 * @return: void      
	 * @throws
	 */
	void deleteDownloadById(Download download) throws SQLException;
	
	/**
	 * @throws SQLException 
	 * 通过id修改标题-------------改
	 * @Title: updateDownloadById   
	 * @Description: TODO  
	 * @param: @param download      
	 * @return: void      
	 * @throws
	 */
	void updateDownloadById(Download download) throws SQLException;
	
	
	/**
	 * @throws SQLException 
	 * 通过下载中心标题进行模糊查找-----查
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
	 * 查找所有-----------------查
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
