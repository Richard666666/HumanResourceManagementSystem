
package com.ljl.human.service;

import java.sql.SQLException;
import java.util.List;

import com.ljl.human.pojo.Job;

/**  
 *
 * @Title:  IJobService.java   
 * @Package com.ljl.human.service   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��8�� ����8:27:29   
 * @version V1.0 
 * 
 */
public interface IJobService {
	/**
	 * @throws SQLException 
	 * ����ְλ---------------��
	 * @Title: insertUser   
	 * @Description: TODO  
	 * @param: @param user      
	 * @return: void      
	 * @throws
	 */
	void insertJob(Job job) throws SQLException;
	
	
	/**
	 * @throws SQLException 
	 * ͨ��idɾ��ְλ----------ɾ
	 * @Title: deleteJobById   
	 * @Description: TODO  
	 * @param: @param job      
	 * @return: void      
	 * @throws
	 */
	void deleteJobById(Job job) throws SQLException;
	
	/**
	 * @throws SQLException 
	 * ͨ��id�޸�ְλ---------��
	 * @Title: updateJobById   
	 * @Description: TODO  
	 * @param: @param job      
	 * @return: void      
	 * @throws
	 */
	void updateJobById(Job job) throws SQLException;
	
	/**
	 * @throws SQLException 
	 * ͨ��ְλ��ģ������--------��
	 * @Title: findJobByNameLike   
	 * @Description: TODO  
	 * @param: @param job
	 * @param: @return      
	 * @return: List<Job>      
	 * @throws
	 */
	List<Job> findJobByNameLike(Job job) throws SQLException;
	
	/**
	 * @throws SQLException 
	 * ����ȫ����ְλ----------��
	 * @Title: findJobAll   
	 * @Description: TODO  
	 * @param: @return      
	 * @return: List<Job>      
	 * @throws
	 */
	List<Job> findJobAll() throws SQLException;





	/**
	 * @throws SQLException    
	 * @Title: findNameById   
	 * @Description: TODO  
	 * @param: @param job
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	String findNameById(int jId) throws SQLException;


	/**
	 * @throws SQLException    
	 * @Title: findJobById   
	 * @Description: TODO  
	 * @param: @param job
	 * @param: @return      
	 * @return: Job      
	 * @throws   
	 */  
	Job findJobById(Job job) throws SQLException;


	/**
	 * @throws SQLException    
	 * @Title: findJobByName   
	 * @Description: TODO  
	 * @param: @param job
	 * @param: @return      
	 * @return: List<Job>      
	 * @throws   
	 */  
	List<Job> findJobByName(Job job) throws SQLException;




}
