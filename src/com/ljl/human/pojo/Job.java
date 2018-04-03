package com.ljl.human.pojo;

import java.util.List;

/**
 * 
 *工作类的实例
 * @Title:  Job.java   
 * @Package com.ljl.human.pojo   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月6日 下午8:23:44   
 * @version V1.0 
 *
 */
public class Job {
	private Integer jId;
	private String jName;
	private String jDes;
	public Integer getjId() {
		return jId;
	}
	public void setjId(Integer jId) {
		this.jId = jId;
	}
	public String getjName() {
		return jName;
	}
	public void setjName(String jName) {
		this.jName = jName;
	}
	public String getjDes() {
		return jDes;
	}
	public void setjDes(String jDes) {
		this.jDes = jDes;
	}
	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Job(Integer jId, String jName, String jDes) {
		super();
		this.jId = jId;
		this.jName = jName;
		this.jDes = jDes;
	}
	@Override
	public String toString() {
		return "Job [jId=" + jId + ", jName=" + jName + ", jDes=" + jDes + "]";
	}
	
	
	

}
