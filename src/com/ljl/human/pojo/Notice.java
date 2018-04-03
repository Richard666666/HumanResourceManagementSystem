package com.ljl.human.pojo;

import java.util.Date;
/**
 * 公告类的实例
 *
 * @Title:  Notice.java   
 * @Package com.ljl.human.pojo   
 * @Description:    TODO
 * @author: 李建良  
 * @date:   2018年1月6日 下午8:24:08   
 * @version V1.0 
 *
 */
public class Notice {
	private Integer nId;
	private String nName;
	private String nContent;
	private Date nCreateTime;
	private Integer uId;
	public Integer getnId() {
		return nId;
	}
	public void setnId(Integer nId) {
		this.nId = nId;
	}
	public String getnName() {
		return nName;
	}
	public void setnName(String nName) {
		this.nName = nName;
	}
	public String getnContent() {
		return nContent;
	}
	public void setnContent(String nContent) {
		this.nContent = nContent;
	}
	public Date getnCreateTime() {
		return nCreateTime;
	}
	public void setnCreateTime(Date nCreateTime) {
		this.nCreateTime = nCreateTime;
	}
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public Notice() {
		
	}
	public Notice(Integer nId, String nName, String nContent, Date nCreateTime, Integer uId) {
		super();
		this.nId = nId;
		this.nName = nName;
		this.nContent = nContent;
		this.nCreateTime = nCreateTime;
		this.uId = uId;
	}
	@Override
	public String toString() {
		return "Notice [nId=" + nId + ", nName=" + nName + ", nContent=" + nContent + ", nCreateTime=" + nCreateTime
				+ ", uId=" + uId + "]";
	}
	
}
