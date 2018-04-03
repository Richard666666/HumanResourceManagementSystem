/**  
 *
 * @Title:  INoticeDao.java   
 * @Package com.ljl.human.Dao   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月6日 下午8:52:03   
 * @version V1.0 
 * 
 */
package com.ljl.human.Dao;

import java.sql.SQLException;
import java.util.List;

import com.ljl.human.pojo.Notice;
import com.ljl.human.pojo.User;

/**  
 *
 * @Title:  INoticeDao.java   
 * @Package com.ljl.human.Dao   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月6日 下午8:52:03   
 * @version V1.0 
 * 
 */
public interface INoticeDao {
	/**
	 * @throws SQLException 
	 * 增加公告信息--------------增
	 * @Title: insertUser   
	 * @Description: TODO  
	 * @param: @param user      
	 * @return: void      
	 * @throws
	 */
	void insertNotice(Notice notice) throws SQLException;
	
	
	/**
	 * @throws SQLException 
	 * 通过id修改公告------------改
	 * @Title: updateNoticeById   
	 * @Description: TODO  
	 * @param: @param notice      
	 * @return: void      
	 * @throws
	 */
	void updateNoticeById(Notice notice) throws SQLException;
	
	/**
	 * @throws SQLException 
	 * 查找所用的公告信息----------查
	 * @Title: findNoticeAll   
	 * @Description: TODO  
	 * @param: @return      
	 * @return: List<Notice>      
	 * @throws
	 */
	List<Notice> findNoticeAll() throws SQLException;
	
	/**
	 * @throws SQLException 
	 * 通过公告名和内容进行模糊查找----查
	 * @Title: findNoticeByNameAndContentLike   
	 * @Description: TODO  
	 * @param: @param notice
	 * @param: @return      
	 * @return: List<Notice>      
	 * @throws
	 */
	List<Notice> findNoticeByNameAndContentLike(Notice notice) throws SQLException;
	
	
	/**
	 * @throws SQLException 
	 * 通过公告id查找公告----------查
	 * @Title: findNoticeById   
	 * @Description: TODO  
	 * @param: @param notice
	 * @param: @return      
	 * @return: Notice      
	 * @throws
	 */
	Notice findNoticeById(Notice notice) throws SQLException;


	/**
	 * @throws SQLException    
	 * @Title: deleteNoticeById   
	 * @Description: TODO  
	 * @param: @param notice      
	 * @return: void      
	 * @throws   
	 */  
	void deleteNoticeById(Notice notice) throws SQLException;


	/**
	 * @throws SQLException    
	 * @Title: findNoticeByName   
	 * @Description: TODO  
	 * @param: @param notice
	 * @param: @return      
	 * @return: List<Notice>      
	 * @throws   
	 */  
	List<Notice> findNoticeByName(Notice notice) throws SQLException;
	

}
