/**  
 *
 * @Title:  INoticeDao.java   
 * @Package com.ljl.human.Dao   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��6�� ����8:52:03   
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
 * @author: ���  
 * @date:   2018��1��6�� ����8:52:03   
 * @version V1.0 
 * 
 */
public interface INoticeDao {
	/**
	 * @throws SQLException 
	 * ���ӹ�����Ϣ--------------��
	 * @Title: insertUser   
	 * @Description: TODO  
	 * @param: @param user      
	 * @return: void      
	 * @throws
	 */
	void insertNotice(Notice notice) throws SQLException;
	
	
	/**
	 * @throws SQLException 
	 * ͨ��id�޸Ĺ���------------��
	 * @Title: updateNoticeById   
	 * @Description: TODO  
	 * @param: @param notice      
	 * @return: void      
	 * @throws
	 */
	void updateNoticeById(Notice notice) throws SQLException;
	
	/**
	 * @throws SQLException 
	 * �������õĹ�����Ϣ----------��
	 * @Title: findNoticeAll   
	 * @Description: TODO  
	 * @param: @return      
	 * @return: List<Notice>      
	 * @throws
	 */
	List<Notice> findNoticeAll() throws SQLException;
	
	/**
	 * @throws SQLException 
	 * ͨ�������������ݽ���ģ������----��
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
	 * ͨ������id���ҹ���----------��
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
