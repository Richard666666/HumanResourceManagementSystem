package com.ljl.human.pojo;

import java.util.Date;
/**
 * 用户类的实例
 *
 * @Title:  User.java   
 * @Package com.ljl.human.pojo   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月6日 下午8:24:24   
 * @version V1.0 
 *
 */
public class User {
	private Integer uId;
	private String uName;
	private String uPwd;
	private String uLoginName;
	private Date uCreateTime;
	private Integer uState;
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuPwd() {
		return uPwd;
	}
	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}
	public String getuLoginName() {
		return uLoginName;
	}
	public void setuLoginName(String uLoginName) {
		this.uLoginName = uLoginName;
	}
	public Date getuCreateTime() {
		return uCreateTime;
	}
	public void setuCreateTime(Date uCreateTime) {
		this.uCreateTime = uCreateTime;
	}
	public Integer getuState() {
		return uState;
	}
	public void setuState(Integer uState) {
		this.uState = uState;
	}
	
	public User() {
		
	}
	public User(Integer uId, String uName, String uPwd, String uLoginName, Date uCreateTime, Integer uState) {
		super();
		this.uId = uId;
		this.uName = uName;
		this.uPwd = uPwd;
		this.uLoginName = uLoginName;
		this.uCreateTime = uCreateTime;
		this.uState = uState;
	}
	@Override
	public String toString() {
		return "User [uId=" + uId + ", uName=" + uName + ", uPwd=" + uPwd + ", uLoginName=" + uLoginName
				+ ", uCreateTime=" + uCreateTime + ", uState=" + uState + "]";
	}
	
	
	

}
