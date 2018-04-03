/**  
 *
 * @Title:  DownloadDao.java   
 * @Package com.ljl.human.Dao.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月6日 下午8:57:01   
 * @version V1.0 
 * 
 */
package com.ljl.human.Dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ljl.human.Dao.IDownloadDao;
import com.ljl.human.pojo.Department;
import com.ljl.human.pojo.Download;
import com.ljl.human.utils.JdbcUtils;


/**  
 *
 * @Title:  DownloadDao.java   
 * @Package com.ljl.human.Dao.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月6日 下午8:57:01   
 * @version V1.0 
 * 
 */
public class DownloadDao implements IDownloadDao{

	/**  
	 * <p>Title: insertDownload</p>   
	 * <p>Description: </p>   
	 * @param download   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IDownloadDao#insertDownload(com.ljl.human.pojo.Download)   
	 */  
	@Override
	public void insertDownload(Download download) throws SQLException {
		
	
		String sql="insert into t_download(doDes,doTitle,doCreateTime,uId,filePath) values(?,?,?,?,?)";
		JdbcUtils.getQueryRunner().update(sql,download.getDoDes(),download.getDoTitle(),download.getDoCreateTime(),download.getuId(),download.getFilePath());
	
	
	}

	/**   
	 * <p>Title: deleteDownloadById</p>   
	 * <p>Description: </p>   
	 * @param download   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IDownloadDao#deleteDownloadById(com.ljl.human.pojo.Download)   
	 */  
	@Override
	public void deleteDownloadById(Download download) throws SQLException {
		String sql="delete from t_download where doId=?";
		JdbcUtils.getQueryRunner().update(sql, download.getDoId());
		
	}

	/**   
	 * <p>Title: updateDownloadById</p>   
	 * <p>Description: </p>   
	 * @param download   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IDownloadDao#updateDownloadById(com.ljl.human.pojo.Download)   
	 */  
	@Override
	public void updateDownloadById(Download download) throws SQLException {
		String sql="update t_download set doDes=?,doTitle=? where doId=?";
		JdbcUtils.getQueryRunner().update(sql, download.getDoDes(),download.getDoTitle(),download.getDoId());
	}

	/**   
	 * <p>Title: findDownloadByTitleLike</p>   
	 * <p>Description: </p>   
	 * @param download
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IDownloadDao#findDownloadByTitleLike(com.ljl.human.pojo.Download)   
	 */  
	@Override
	public List<Download> findDownloadByTitleLike(Download download) throws SQLException {
		String sql="select * from t_download where doTitle like ?";
		List<Download> downloads=JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Download>(Download.class),"%"+download.getDoTitle()+"%");                            
		return downloads;
	}

	/**   
	 * <p>Title: findDownloadAll</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IDownloadDao#findDownloadAll()   
	 */  
	@Override
	public List<Download> findDownloadAll() throws SQLException {
		String sql="select * from t_download ";
		return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Download>(Download.class));
	}

	/**   
	 * <p>Title: findDownloadById</p>   
	 * <p>Description: </p>   
	 * @param download1
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IDownloadDao#findDownloadById(com.ljl.human.pojo.Download)   
	 */  
	@Override
	public Download findDownloadById(Download download) throws SQLException {
		String sql="select * from t_download where doId=?";
		return JdbcUtils.getQueryRunner().query(sql,new BeanHandler<Download>(Download.class),download.getDoId());
	}

	/**   
	 * <p>Title: findDepartmentByTitle</p>   
	 * <p>Description: </p>   
	 * @param download
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IDownloadDao#findDepartmentByTitle(com.ljl.human.pojo.Download)   
	 */  
	@Override
	public List<Download> findDepartmentByTitle(Download download) throws SQLException {
		StringBuilder sql=new StringBuilder("select * from t_download where 1=1");
		if(download.getDoTitle()!=null&&!(download.getDoTitle().equals(""))){
			sql.append(" and doTitle='"+download.getDoTitle()+"'");
		}
	
		return JdbcUtils.getQueryRunner().query(sql.toString(),new BeanListHandler<Download>(Download.class));
	}
	
	
	
	

}
