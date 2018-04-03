package com.ljl.human.pojo;

import java.util.Date;

import com.ljl.human.enumation.Gender;
/**
 * 
 *员工的实例
 * @Title:  Employee.java   
 * @Package com.ljl.human.pojo   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月6日 下午8:23:16   
 * @version V1.0 
 *
 */
public class Employee {
	private Integer eId;
	private String eName;
	private Integer eGender;
	private String eTelNum;
	private String eEmail;
	private Integer jId;
	private String eStu;
	private Integer dId;
	private String eIdCard;
	private Date eCreateTime;
	private String eAddress;
	
	private Job job;
	private Department department;
	
	
	public Integer geteId() {
		return eId;
	}
	public void seteId(Integer eId) {
		this.eId = eId;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public Integer geteGender() {
		return eGender;
	}
	public void seteGender(Integer eGender) {
		this.eGender = eGender;
	}
	public String geteTelNum() {
		return eTelNum;
	}
	public void seteTelNum(String eTelNum) {
		this.eTelNum = eTelNum;
	}
	public String geteEmail() {
		return eEmail;
	}
	public void seteEmail(String eEmail) {
		this.eEmail = eEmail;
	}
	public Integer getjId() {
		return jId;
	}
	public void setjId(Integer jId) {
		this.jId = jId;
	}
	public String geteStu() {
		return eStu;
	}
	public void seteStu(String eStu) {
		this.eStu = eStu;
	}
	public Integer getdId() {
		return dId;
	}
	public void setdId(Integer dId) {
		this.dId = dId;
	}
	public String geteIdCard() {
		return eIdCard;
	}
	public void seteIdCard(String eIdCard) {
		this.eIdCard = eIdCard;
	}
	public Date geteCreateTime() {
		return eCreateTime;
	}
	public void seteCreateTime(Date eCreateTime) {
		this.eCreateTime = eCreateTime;
	}
	public String geteAddress() {
		return eAddress;
	}
	public void seteAddress(String eAddress) {
		this.eAddress = eAddress;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Employee() {
		
	}
	public Employee(Integer eId, String eName, Integer eGender, String eTelNum, String eEmail, Integer jId, String eStu,
			Integer dId, String eIdCard, Date eCreateTime, String eAddress, Job job, Department department) {
		super();
		this.eId = eId;
		this.eName = eName;
		this.eGender = eGender;
		this.eTelNum = eTelNum;
		this.eEmail = eEmail;
		this.jId = jId;
		this.eStu = eStu;
		this.dId = dId;
		this.eIdCard = eIdCard;
		this.eCreateTime = eCreateTime;
		this.eAddress = eAddress;
		this.job = job;
		this.department = department;
	}
	@Override
	public String toString() {
		return "Employee [eId=" + eId + ", eName=" + eName + ", eGender=" + eGender + ", eTelNum=" + eTelNum
				+ ", eEmail=" + eEmail + ", jId=" + jId + ", eStu=" + eStu + ", dId=" + dId + ", eIdCard=" + eIdCard
				+ ", eCreateTime=" + eCreateTime + ", eAddress=" + eAddress + ", job=" + job + ", department="
				+ department + "]";
	}
	
	
	
	
	

	
	
	

}
