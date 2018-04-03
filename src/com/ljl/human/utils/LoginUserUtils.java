/**  
 *
 * @Title:  LoginUserUtils.java   
 * @Package com.ljl.human.utils   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月5日 下午1:08:07   
 * @version V1.0 
 * 
 */
package com.ljl.human.utils;

import javax.servlet.http.HttpServletRequest;

import com.ljl.human.pojo.User;

/**  
 *
 * @Title:  LoginUserUtils.java   
 * @Package com.ljl.human.utils   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月5日 下午1:08:07   
 * @version V1.0 
 * 
 */
public class LoginUserUtils {
	public static Integer getLoginUserId(HttpServletRequest request){
		User loginUser=(User) request.getSession().getAttribute("user");
		return loginUser.getuId();
	}

}
