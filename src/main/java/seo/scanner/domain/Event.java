package seo.scanner.domain;

import java.util.Date;

public class Event {
	
	private Date createDate;
	private Date endDate;
	private Boolean isInProgress;
	private Integer projetListUrlUid;
	private Date startDate;
	private Integer uid;
	
	public Date getCreateDate() {
		return createDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public Boolean getIsInProgress() {
		return isInProgress;
	}
	public Integer getProjetListUrlUid() {
		return projetListUrlUid;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Integer getUid() {
		return uid;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setIsInProgress(Boolean isInProgress) {
		this.isInProgress = isInProgress;
	}
	public void setProjetListUrlUid(Integer projetListUrlUid) {
		this.projetListUrlUid = projetListUrlUid;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}

}
