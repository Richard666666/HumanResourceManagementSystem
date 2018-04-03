/**  
 *
 * @Title:  IUserDao.java   
 * @Package com.ljl.human.Dao   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��6�� ����8:31:13   
 * @version V1.0 
 * 
 */
package com.ljl.human.Dao;

import java.sql.SQLException;
import java.util.List;

import com.ljl.human.pojo.User;

/**  
 *�û�(����Ա)�Ľӿ�
 * @Title:  IUserDao.java   
 * @Package com.ljl.human.Dao   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��6�� ����8:31:13   
 * @version V1.0 
 * 
 */
public interface IUserDao {
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
	
	List<User> findUserByUserNameOrStateLike(User user) throws Exception;



	/**   
	 * @Title: findUserById   
	 * @Description: TODO  
	 * @param: @param user
	 * @param: @return      
	 * @return: User      
	 * @throws   
	 */  
	User findUserById(int uId)  throws SQLException;



	/**
	 * @throws SQLException    
	 * @Title: findUserById   
	 * @Description: TODO  
	 * @param: @param user
	 * @param: @return      
	 * @return: User      
	 * @throws   
	 */  
	User findUserById(User user) throws SQLException;
	

}
