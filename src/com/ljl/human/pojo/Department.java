package com.ljl.human.pojo;
/**
 * ���ŵ�ʵ����
 *
 * @Title:  Department.java   
 * @Package com.ljl.human.pojo   
 * @Description:    TODO
 * @author: ���  
 * @date:   2018��1��6�� ����8:22:07   
 * @version V1.0 
 *
 */

public class Department {
	private Integer dId;
	private String dName;
	private String dDes;
	public Integer getdId() {
		return dId;
	}
	public void setdId(Integer dId) {
		this.dId = dId;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getdDes() {
		return dDes;
	}
	public void setdDes(String dDes) {
		this.dDes = dDes;
	}
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(Integer dId, String dName, String dDes) {
		super();
		this.dId = dId;
		this.dName = dName;
		this.dDes = dDes;
	}
	@Override
	public String toString() {
		return "Department [dId=" + dId + ", dName=" + dName + ", dDes=" + dDes + "]";
	}
	
	

}
