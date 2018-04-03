
package com.ljl.human.service;

import java.sql.SQLException;
import java.util.List;

import com.ljl.human.pojo.User;

/**  
 *
 * @Title:  IUserService.java   
 * @Package com.ljl.human.service   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��8�� ����8:28:40   
 * @version V1.0 
 * 
 */
public interface IUserService {
	/**
	 * @throws SQLException 
	 * �����û�-----------------------��
	 * @Title: insertUser   
	 * @Description: TODO  
	 * @param: @param user      
	 * @return: void      
	 * @throws
	 */
	void insertUser(User user) throws SQLException;
	

		
	/**
	 * @throws SQLException 
	 * ͨ���û�idɾ���û�-----------------ɾ
	 * @Title: deleteUserById   
	 * @Description: TODO  
	 * @param: @param user      
	 * @return: void      
	 * @throws
	 */
	
	
	
	void deleteUserById(User user) throws SQLException;
	/**
	 * @throws SQLException 
	 * ͨ��id���޸��û�------------------��
	 * @Title: updateUser   
	 * @Description: TODO  
	 * @param: @param user      
	 * @return: void      
	 * @throws
	 */
	
	void updateUserById(User user) throws SQLException;
	
	
	/**
	 * @throws SQLException 
	 * ͨ���û����û�����������е�½ҳ��-------��
	 * @Title: findUserByUserNameAndPwd   
	 * @Description: TODO  
	 * @param: @param user
	 * @param: @return      
	 * @return: User      
	 * @throws
	 */
	
	User findUserByUserLoginNameAndPwd(User user) throws SQLException;
	
	/**
	 * @throws SQLException 
	 * �������õ��û�---------------------��
	 * @Title: findUserAll   
	 * @Description: TODO  
	 * @param: @return      
	 * @return: List<User>      
	 * @throws
	 */
	List<User> findUserAll() throws SQLException;
	
	/**
	 * @throws Exception 
	 * @throws SQLException 
	 * ͨ���û�������״̬����ģ������----------��
	 * @Title: findUserByUserNameOrStatusLike   
	 * @Description: TODO  
	 * @param: @param user
	 * @param: @return      
	 * @return: List<User>      
	 * @throws
	 */
	
	List<User> findUserByUserNameOrStateLike(User user) throws SQLException, Exception;



	/**   
	 * @Title: findUserById   
	 * @Description: TODO  
	 * @param: @param user
	 * @param: @return      
	 * @return: User      
	 * @throws   
	 */  
	User findUserById(int uId)throws SQLException;



	/**   
	 * @Title: findUserById   
	 * @Description: TODO  
	 * @param: @param user
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: User      
	 * @throws   
	 */  
	User findUserById(User user) throws SQLException;

}
