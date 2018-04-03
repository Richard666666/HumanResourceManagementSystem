/**  
 *
 * @Title:  JobService.java   
 * @Package com.ljl.human.service.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月8日 下午8:39:14   
 * @version V1.0 
 * 
 */
package com.ljl.human.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ljl.human.Dao.IJobDao;
import com.ljl.human.Dao.impl.JobDao;
import com.ljl.human.pojo.Job;
import com.ljl.human.service.IJobService;
import com.ljl.human.utils.ObjectUtils;

/**  
 *
 * @Title:  JobService.java   
 * @Package com.ljl.human.service.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月8日 下午8:39:14   
 * @version V1.0 
 * 
 */
public class JobService implements IJobService{
	private IJobDao jobDao=null;
	public JobService() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		jobDao=(IJobDao) ObjectUtils.getObject("jobDao");
	}
	/**   
	 * <p>Title: insertJob</p>   
	 * <p>Description: </p>   
	 * @param job
	 * @throws SQLException   
	 * @see com.ljl.human.service.IJobService#insertJob(com.ljl.human.pojo.Job)   
	 */  
	@Override
	public void insertJob(Job job) throws SQLException {
		if(job.getjDes().equals("")||job.getjName().equals("")){
			throw new RuntimeException("职位的参数不够");
		}
		jobDao.insertJob(job);
	}
	/**   
	 * <p>Title: deleteJobById</p>   
	 * <p>Description: </p>   
	 * @param job
	 * @throws SQLException   
	 * @see com.ljl.human.service.IJobService#deleteJobById(com.ljl.human.pojo.Job)   
	 */  
	@Override
	public void deleteJobById(Job job) throws SQLException {
		if(job.getjId()==null){
			throw new RuntimeException("职位的id为空");
		}
		jobDao.deleteJobById(job);
	}
	/**   
	 * <p>Title: updateJobById</p>   
	 * <p>Description: </p>   
	 * @param job
	 * @throws SQLException   
	 * @see com.ljl.human.service.IJobService#updateJobById(com.ljl.human.pojo.Job)   
	 */  
	@Override
	public void updateJobById(Job job) throws SQLException {
		if(job.getjId()==null){
			
			throw new RuntimeException("职位的id为空");
		}
		jobDao.updateJobById(job);
	}
	/**   
	 * <p>Title: findJobByNameLike</p>   
	 * <p>Description: </p>   
	 * @param job
	 * @return
	 * @throws SQLException   
	 * @see com.ljl.human.service.IJobService#findJobByNameLike(com.ljl.human.pojo.Job)   
	 */  
	@Override
	public List<Job> findJobByNameLike(Job job) throws SQLException {
		return jobDao.findJobByNameLike(job);
	}
	/**   
	 * <p>Title: findJobAll</p>   
	 * <p>Description: </p>   
	 * @return
	 * @throws SQLException   
	 * @see com.ljl.human.service.IJobService#findJobAll()   
	 */  
	@Override
	public List<Job> findJobAll() throws SQLException {
		return jobDao.findJobAll();
	}
	/**   
	 * <p>Title: findNameById</p>   
	 * <p>Description: </p>   
	 * @param jId
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.service.IJobService#findNameById(int)   
	 */  
	@Override
	public String findNameById(int jId) throws SQLException {
		
		return jobDao.findNameById(jId);
	}
	/**   
	 * <p>Title: findJobById</p>   
	 * <p>Description: </p>   
	 * @param job
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.service.IJobService#findJobById(com.ljl.human.pojo.Job)   
	 */  
	@Override
	public Job findJobById(Job job) throws SQLException {
		if(job.getjId()==null){
			System.out.println("id不能为空");
		}
		return jobDao.findJobById(job);
	}
	/**   
	 * <p>Title: findJobByName</p>   
	 * <p>Description: </p>   
	 * @param job
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.service.IJobService#findJobByName(com.ljl.human.pojo.Job)   
	 */  
	@Override
	public List<Job> findJobByName(Job job) throws SQLException {
		return  jobDao.findJobByName(job);
		
	}

}
