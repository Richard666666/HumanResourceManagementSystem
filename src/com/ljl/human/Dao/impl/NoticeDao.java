/**  
 *
 * @Title:  NoticeDao.java   
 * @Package com.ljl.human.Dao.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月7日 下午8:30:48   
 * @version V1.0 
 * 
 */
package com.ljl.human.Dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ljl.human.Dao.INoticeDao;
import com.ljl.human.pojo.Department;
import com.ljl.human.pojo.Job;
import com.ljl.human.pojo.Notice;
import com.ljl.human.utils.JdbcUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**  
 *
 * @Title:  NoticeDao.java   
 * @Package com.ljl.human.Dao.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月7日 下午8:30:48   
 * @version V1.0 
 * 
 */
public class NoticeDao implements INoticeDao {

	/**   
	 * <p>Title: insertNotice</p>   
	 * <p>Description: </p>   
	 * @param notice   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.INoticeDao#insertNotice(com.ljl.human.pojo.Notice)   
	 */  
	@Override
	public void insertNotice(Notice notice) throws SQLException {
		String sql="insert into t_notice(nName,nContent,nCreateTime,uId) values(?,?,?,?)";
		JdbcUtils.getQueryRunner().update(sql, notice.getnName(),notice.getnContent(),notice.getnCreateTime(),notice.getuId());
	}

	/**   
	 * <p>Title: updateNoticeById</p>   
	 * <p>Description: </p>   
	 * @param notice   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.INoticeDao#updateNoticeById(com.ljl.human.pojo.Notice)   
	 */  
	@Override
	public void updateNoticeById(Notice notice) throws SQLException {
		String sql="update t_notice set nName=?,nContent=?,nCreateTime=?,uId=? where nId=? ";
		JdbcUtils.getQueryRunner().update(sql,notice.getnName(),notice.getnContent(),notice.getnCreateTime(),notice.getuId(),notice.getnId());
	}
	/**   
	 * <p>Title: findNoticeAll</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.INoticeDao#findNoticeAll()   
	 */  
	@Override
	public List<Notice> findNoticeAll() throws SQLException {
		String sql="select * from t_notice";
		return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Notice>(Notice.class));
	}

	/**   
	 * <p>Title: findNoticeByNameAndContentLike</p>   
	 * <p>Description: </p>   
	 * @param notice
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.INoticeDao#findNoticeByNameAndContentLike(com.ljl.human.pojo.Notice)   
	 */  
	@Override
	public List<Notice> findNoticeByNameAndContentLike(Notice notice) throws SQLException {
		String sql="select * from t_notice where nName=?,nContent=?";
		return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Notice>(Notice.class),notice.getnName(),notice.getnContent());
	}

	/**   
	 * <p>Title: findNoticeById</p>   
	 * <p>Description: </p>   
	 * @param notice
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.INoticeDao#findNoticeById(com.ljl.human.pojo.Notice)   
	 */  
	@Override
	public Notice findNoticeById(Notice notice) throws SQLException {
		String sql="select * from t_notice where nId=?";
		return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Notice>(Notice.class),notice.getnId());
	}

	/**   
	 * <p>Title: deleteNoticeById</p>   
	 * <p>Description: </p>   
	 * @param notice   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.INoticeDao#deleteNoticeById(com.ljl.human.pojo.Notice)   
	 */  
	@Override
	public void deleteNoticeById(Notice notice) throws SQLException {
		String sql="delete from t_notice where nId=?";
		JdbcUtils.getQueryRunner().update(sql, notice.getnId());
		
	}

	/**   
	 * <p>Title: findNoticeByName</p>   
	 * <p>Description: </p>   
	 * @param notice
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.INoticeDao#findNoticeByName(com.ljl.human.pojo.Notice)   
	 */  
	@Override
	public List<Notice> findNoticeByName(Notice notice) throws SQLException {
		StringBuilder sql=new StringBuilder("select * from t_notice where 1=1");
		if(notice.getnName()!=null&&!(notice.getnName().equals(""))){
			sql.append(" and nName='"+notice.getnName()+"'");
		}
		if(notice.getnContent()!=null&&!(notice.getnContent().equals(""))){
			sql.append(" and nContent='"+notice.getnContent()+"'");
		}
	
		return JdbcUtils.getQueryRunner().query(sql.toString(),new BeanListHandler<Notice>(Notice.class));
	}


}
