package com.ljl.human.pojo;

import java.util.Date;
/**
 * 
 * 下载中心的实例
 *
 * @Title:  Download.java   
 * @Package com.ljl.human.pojo   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月6日 下午8:22:45   
 * @version V1.0 
 *
 */

public class Download {
	private Integer doId;
	private String doDes;
	private String doTitle;
	private Date doCreateTime;
	private Integer uId;
	private String filePath;
	private User user;
	
	
	
	public Integer getDoId() {
		return doId;
	}
	public void setDoId(Integer doId) {
		this.doId = doId;
	}
	public String getDoDes() {
		return doDes;
	}
	public void setDoDes(String doDes) {
		this.doDes = doDes;
	}
	public String getDoTitle() {
		return doTitle;
	}
	public void setDoTitle(String doTitle) {
		this.doTitle = doTitle;
	}
	public Date getDoCreateTime() {
		return doCreateTime;
	}
	public void setDoCreateTime(Date doCreateTime) {
		this.doCreateTime = doCreateTime;
	}
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Download() {
		
	}
	public Download(Integer doId, String doDes, String doTitle, Date doCreateTime, Integer uId, String filePath,
			User user) {
		super();
		this.doId = doId;
		this.doDes = doDes;
		this.doTitle = doTitle;
		this.doCreateTime = doCreateTime;
		this.uId = uId;
		this.filePath = filePath;
		this.user = user;
	}
	@Override
	public String toString() {
		return "Download [doId=" + doId + ", doDes=" + doDes + ", doTitle=" + doTitle + ", doCreateTime=" + doCreateTime
				+ ", uId=" + uId + ", filePath=" + filePath + ", user=" + user + "]";
	}
	
	
	
	
}
