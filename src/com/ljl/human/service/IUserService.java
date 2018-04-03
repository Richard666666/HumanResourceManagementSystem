
package com.ljl.human.service;

import java.sql.SQLException;
import java.util.List;

import com.ljl.human.pojo.User;

/**  
 *
 * @Title:  IUserService.java   
 * @Package com.ljl.human.service   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月8日 下午8:28:40   
 * @version V1.0 
 * 
 */
public interface IUserService {
	/**
	 * @throws SQLException 
	 * 增加用户-----------------------增
	 * @Title: insertUser   
	 * @Description: TODO  
	 * @param: @param user      
	 * @return: void      
	 * @throws
	 */
	void insertUser(User user) throws SQLException;
	

		
	/**
	 * @throws SQLException 
	 * 通过用户id删除用户-----------------删
	 * @Title: deleteUserById   
	 * @Description: TODO  
	 * @param: @param user      
	 * @return: void      
	 * @throws
	 */
	
	
	
	void deleteUserById(User user) throws SQLException;
	/**
	 * @throws SQLException 
	 * 通过id来修改用户------------------改
	 * @Title: updateUser   
	 * @Description: TODO  
	 * @param: @param user      
	 * @return: void      
	 * @throws
	 */
	
	void updateUserById(User user) throws SQLException;
	
	
	/**
	 * @throws SQLException 
	 * 通过用户的用户名和密码进行登陆页面-------查
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
	 * 查找所用的用户---------------------查
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
	 * 通过用户名或者状态进行模糊查找----------查
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
