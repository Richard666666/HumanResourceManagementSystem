/**  
 *
 * @Title:  UserDao.java   
 * @Package com.ljl.human.Dao.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月6日 下午8:31:46   
 * @version V1.0 
 * 
 */
package com.ljl.human.Dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ljl.human.Dao.IUserDao;
import com.ljl.human.pojo.User;
import com.ljl.human.utils.JdbcUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**  
 *
 * @Title:  UserDao.java   
 * @Package com.ljl.human.Dao.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月6日 下午8:31:46   
 * @version V1.0 
 * 
 */
public class UserDao  implements IUserDao {

	/**   
	 * <p>Title: insertUser</p>   
	 * <p>Description: </p>   
	 * @param user   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IUserDao#insertUser(com.ljl.human.pojo.User)   
	 */  
	@Override
	public void insertUser(User user) throws SQLException {
		String sql="insert into t_user(uName,uPwd,uLoginName,uState,uCreateTime) values(?,?,?,?,?)";
		JdbcUtils.getQueryRunner().update(sql,user.getuName(),user.getuPwd(),user.getuLoginName(),user.getuState(),user.getuCreateTime());
	}
	/**   
	 * <p>Title: deleteUserById</p>   
	 * <p>Description: </p>   
	 * @param user   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IUserDao#deleteUserById(com.ljl.human.pojo.User)   
	 */  
	@Override
	public void deleteUserById(User user) throws SQLException {
		String sql="delete from t_user where uId=?";
		JdbcUtils.getQueryRunner().update(sql,user.getuId());
	}

	/**   
	 * <p>Title: updateUserById</p>   
	 * <p>Description: </p>   
	 * @param user   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IUserDao#updateUserById(com.ljl.human.pojo.User)   
	 */  
	@Override
	public void updateUserById(User user) throws SQLException {
		String sql="update t_user set uName=?,uPwd=?,uLoginName=?,ustate=?,uCreateTime=? where uId=?";
		JdbcUtils.getQueryRunner().update(sql, user.getuName(),user.getuPwd(),user.getuLoginName(),user.getuState(),user.getuCreateTime(),user.getuId());
	}

	/**   
	 * <p>Title: findUserByUserNameAndPwd</p>   
	 * <p>Description: </p>   
	 * @param user
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IUserDao#findUserByUserNameAndPwd(com.ljl.human.pojo.User)   
	 */  
	@Override
	public User findUserByUserLoginNameAndPwd(User user) throws SQLException {
		String sql="select * from t_user where uLoginName=? and uPwd=?";
		return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<User>(User.class),user.getuLoginName(),user.getuPwd());
	}

	/**   
	 * <p>Title: findUserAll</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IUserDao#findUserAll()   
	 */  
	@Override
	public List<User> findUserAll() throws SQLException {
		String sql="select * from t_user";
		return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<User>(User.class));
	}

	/**   
	 * <p>Title: findUserByUserNameOrStatusLike</p>   
	 * <p>Description: </p>   
	 * @param user
	 * @return   
	 * @throws SQLException 
	 * @see com.ljl.human.Dao.IUserDao#findUserByUserNameOrStatusLike(com.ljl.human.pojo.User)   
	 */  
	@Override
	public List<User> findUserByUserNameOrStateLike(User user) throws Exception {
		StringBuilder sql=new StringBuilder("select * from t_user where 1=1");
		if(user.getuName()!=null&&!(user.getuName().equals(""))){
			sql.append(" and uName='"+user.getuName()+"'");
		}
		if(user.getuState()!=null&&user.getuState()!=0){
			sql.append(" and uState="+user.getuState());
		}
		return JdbcUtils.getQueryRunner().query(sql.toString(),new BeanListHandler<User>(User.class));
	}
	/**   
	 * <p>Title: findUserById</p>   
	 * <p>Description: </p>   
	 * @param user
	 * @return
	 * @throws SQLException   
	 * @see com.ljl.human.Dao.IUserDao#findUserById(com.ljl.human.pojo.User)   
	 */  
	@Override
	public User findUserById(User user) throws SQLException {
		String sql="select * from t_user where uId=?";
		return JdbcUtils.getQueryRunner().query(sql,new BeanHandler<User>(User.class),user.getuId());
	}
	/**   
	 * <p>Title: findUserById</p>   
	 * <p>Description: </p>   
	 * @param uId
	 * @return
	 * @throws SQLException   
	 * @see com.ljl.human.Dao.IUserDao#findUserById(int)   
	 */  
	@Override
	public User findUserById(int uId) throws SQLException {
		String sql="select * from t_user where uId=?";
		return JdbcUtils.getQueryRunner().query(sql,new BeanHandler<User>(User.class),uId);
	}
}
