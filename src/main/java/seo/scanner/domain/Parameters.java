package seo.scanner.domain;

public class Parameters {
	
	private Integer userAgentUid;
	private Integer projetListUrlUid;
	private Integer jourMois;
	private Integer jour;
	private Integer heure;
	private String frequence;
	
	
	public Integer getJourMois() {
		return jourMois;
	}
	public String getFrequence() {
		return frequence;
	}
	public void setFrequence(String frequence) {
		this.frequence = frequence;
	}
	public void setJourMois(Integer jourMois) {
		this.jourMois = jourMois;
	}
	public Integer getJour() {
		return jour;
	}
	public void setJour(Integer jour) {
		this.jour = jour;
	}
	public Integer getHeure() {
		return heure;
	}
	public void setHeure(Integer heure) {
		this.heure = heure;
	}
	public Integer getMinute() {
		return minute;
	}
	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	private Integer minute;
	
	
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
	

}
