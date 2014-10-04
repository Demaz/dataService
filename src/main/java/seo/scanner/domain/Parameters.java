package seo.scanner.domain;

public class Parameters {
	
	private Integer userAgentUid;
	private String planification;
	private Integer projetListUrlUid;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	private Integer uid;
	
	public Integer getProjetListUrlUid() {
		return projetListUrlUid;
	}
	public void setProjetListUrlUid(Integer projetListUrlUid) {
		this.projetListUrlUid = projetListUrlUid;
	}
	public Integer getUserAgentUid() {
		return userAgentUid;
	}
	public void setUserAgentUid(Integer userAgentUid) {
		this.userAgentUid = userAgentUid;
	}
	public String getPlanification() {
		return planification;
	}
	public void setPlanification(String planification) {
		this.planification = planification;
	}

}
