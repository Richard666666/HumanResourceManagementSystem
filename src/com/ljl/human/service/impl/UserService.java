/**  
 *
 * @Title:  UserService.java   
 * @Package com.ljl.human.service.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月8日 下午8:40:37   
 * @version V1.0 
 * 
 */
package com.ljl.human.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.omg.CORBA.UserException;

import com.ljl.human.Dao.IUserDao;
import com.ljl.human.pojo.User;
import com.ljl.human.service.IUserService;
import com.ljl.human.utils.ObjectUtils;

/**  
 *
 * @Title:  UserService.java   
 * @Package com.ljl.human.service.impl   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月8日 下午8:40:37   
 * @version V1.0 
 * 
 */
public class UserService implements IUserService{
	private IUserDao userDao=null;
	public UserService() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		userDao=(IUserDao) ObjectUtils.getObject("userDao");
	}

	/**   
	 * <p>Title: insertUser</p>   
	 * <p>Description: </p>   
	 * @param user
	 * @throws SQLException   
	 * @see com.ljl.human.service.IUserService#insertUser(com.ljl.human.pojo.User)   
	 */  
	@Override
	public void insertUser(User user) throws SQLException {
		if(user.getuCreateTime()==null||user.getuLoginName().equals("")||user.getuName().equals("")||user.getuPwd().equals("")||user.getuState()==null){
			throw new RuntimeException("输入的参数不够");
		}
		userDao.insertUser(user);
		
	}

	/**   
	 * <p>Title: deleteUserById</p>   
	 * <p>Description: </p>   
	 * @param user
	 * @throws SQLException   
	 * @see com.ljl.human.service.IUserService#deleteUserById(com.ljl.human.pojo.User)   
	 */  
	@Override
	public void deleteUserById(User user) throws SQLException {
		if(user.getuId()==null){
			throw new RuntimeException("输入的id不能为空");
		}
		userDao.deleteUserById(user);
		
	}

	/**   
	 * <p>Title: updateUserById</p>   
	 * <p>Description: </p>   
	 * @param user
	 * @throws SQLException   
	 * @see com.ljl.human.service.IUserService#updateUserById(com.ljl.human.pojo.User)   
	 */  
	@Override
	public void updateUserById(User user) throws SQLException {
		if(user.getuId()==null){
			throw new RuntimeException("输入的id不能为空");
		}
		userDao.updateUserById(user);
		
	}

	/**   
	 * <p>Title: findUserByUserLoginNameAndPwd</p>   
	 * <p>Description: </p>   
	 * @param user
	 * @return
	 * @throws SQLException   
	 * @see com.ljl.human.service.IUserService#findUserByUserLoginNameAndPwd(com.ljl.human.pojo.User)   
	 */  
	@Override
	public User findUserByUserLoginNameAndPwd(User user) throws SQLException {
		if(user.getuLoginName().equals("")||user.getuPwd().equals("")){
			System.out.println("登陆参数少了");
			}
		User user2=userDao.findUserByUserLoginNameAndPwd(user);
		if(null==user2){
			System.out.println("用户和密码不对");
		}
		
		return user2;
	}

	/**   
	 * <p>Title: findUserAll</p>   
	 * <p>Description: </p>   
	 * @return
	 * @throws SQLException   
	 * @see com.ljl.human.service.IUserService#findUserAll()   
	 */  
	@Override
	public List<User> findUserAll() throws SQLException {
		return userDao.findUserAll();
	}

	/**   
	 * <p>Title: findUserByUserNameOrStateLike</p>   
	 * <p>Description: </p>   
	 * @param user
	 * @return
	 * @throws Exception 
	 * @see com.ljl.human.service.IUserService#findUserByUserNameOrStateLike(com.ljl.human.pojo.User)   
	 */  
	@Override
	public List<User> findUserByUserNameOrStateLike(User user) throws Exception {
		return userDao.findUserByUserNameOrStateLike(user);
	}

	/**   
	 * <p>Title: findUserById</p>   
	 * <p>Description: </p>   
	 * @param user
	 * @return
	 * @throws SQLException   
	 * @see com.ljl.human.service.IUserService#findUserById(com.ljl.human.pojo.User)   
	 */  
	@Override
	public User findUserById(User user) throws SQLException {
		if(user.getuId()==null){
			System.out.println("id不能为空");
		}
		return userDao.findUserById(user);
	}

	/**   
	 * <p>Title: findUserById</p>   
	 * <p>Description: </p>   
	 * @param uId
	 * @return
	 * @throws SQLException   
	 * @see com.ljl.human.service.IUserService#findUserById(int)   
	 */  
	@Override
	public User findUserById(int uId) throws SQLException {
		
		return userDao.findUserById(uId);
	}
	

}
