/**  
 *
 * @Title:  JobDao.java   
 * @Package com.ljl.human.Dao.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月7日 下午9:09:00   
 * @version V1.0 
 * 
 */
package com.ljl.human.Dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ljl.human.Dao.IJobDao;
import com.ljl.human.common.MyStringResultHandle;
import com.ljl.human.pojo.Department;
import com.ljl.human.pojo.Job;
import com.ljl.human.utils.JdbcUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**  
 *
 * @Title:  JobDao.java   
 * @Package com.ljl.human.Dao.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月7日 下午9:09:00   
 * @version V1.0 
 * 
 */
public class JobDao implements IJobDao {

	/**   
	 * <p>Title: insertJob</p>   
	 * <p>Description: </p>   
	 * @param job   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IJobDao#insertJob(com.ljl.human.pojo.Job)   
	 */  
	@Override
	public void insertJob(Job job) throws SQLException {
		String sql="insert into t_job(jName,jDes) values(?,?)";
		JdbcUtils.getQueryRunner().update(sql,job.getjName(),job.getjDes());
	}

	/**   
	 * <p>Title: deleteJobById</p>   
	 * <p>Description: </p>   
	 * @param job   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IJobDao#deleteJobById(com.ljl.human.pojo.Job)   
	 */  
	@Override
	public void deleteJobById(Job job) throws SQLException {
		String sql="delete from t_job where jId=?";
		JdbcUtils.getQueryRunner().update(sql,job.getjId());
	}

	/**   
	 * <p>Title: updateJobById</p>   
	 * <p>Description: </p>   
	 * @param job   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IJobDao#updateJobById(com.ljl.human.pojo.Job)   
	 */  
	@Override
	public void updateJobById(Job job) throws SQLException {
		String sql="update t_job set jName=?,jDes=? where jId=?";
		JdbcUtils.getQueryRunner().update(sql,job.getjName(),job.getjDes(),job.getjId());
		
	}

	/**   
	 * <p>Title: findJobByNameLike</p>   
	 * <p>Description: </p>   
	 * @param job
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IJobDao#findJobByNameLike(com.ljl.human.pojo.Job)   
	 */  
	@Override
	public List<Job> findJobByNameLike(Job job) throws SQLException {
		String sql="select * from t_job where jName like ?";
		return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Job>(Job.class),job.getjName());
	}

	/**   
	 * <p>Title: findJobAll</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IJobDao#findJobAll()   
	 */  
	@Override
	public List<Job> findJobAll() throws SQLException {
		String sql="select * from t_job";
		return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Job>(Job.class));
	}

	/**   
	 * <p>Title: findNameById</p>   
	 * <p>Description: </p>   
	 * @param jId
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IJobDao#findNameById(int)   
	 */  
	@Override
	public String findNameById(int jId) throws SQLException {
		String sql="select JName from t_job where jId=?";
		return JdbcUtils.getQueryRunner().query(sql, new MyStringResultHandle<String>(),jId );
	}

	/**   
	 * <p>Title: findJobById</p>   
	 * <p>Description: </p>   
	 * @param job
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IJobDao#findJobById(com.ljl.human.pojo.Job)   
	 */  
	@Override
	public Job findJobById(Job job) throws SQLException {
		String sql="select * from t_job where jId=?";
		return  JdbcUtils.getQueryRunner().query(sql,new BeanHandler<Job>(Job.class),job.getjId());
	}

	/**   
	 * <p>Title: findJobByName</p>   
	 * <p>Description: </p>   
	 * @param job
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IJobDao#findJobByName(com.ljl.human.pojo.Job)   
	 */  
	@Override
	public List<Job> findJobByName(Job job) throws SQLException {
		StringBuilder sql=new StringBuilder("select * from t_job where 1=1");
		if(job.getjName()!=null&&!(job.getjName().equals(""))){
			sql.append(" and jName='"+job.getjName()+"'");
		}
	
		return JdbcUtils.getQueryRunner().query(sql.toString(),new BeanListHandler<Job>(Job.class));
	}

}
