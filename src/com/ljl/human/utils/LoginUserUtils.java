/**  
 *
 * @Title:  LoginUserUtils.java   
 * @Package com.ljl.human.utils   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��5�� ����1:08:07   
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
 * @author: ���  
 * @date:   2018��1��5�� ����1:08:07   
 * @version V1.0 
 * 
 */
public class LoginUserUtils {
	public static Integer getLoginUserId(HttpServletRequest request){
		User loginUser=(User) request.getSession().getAttribute("user");
		return loginUser.getuId();
	}

}
