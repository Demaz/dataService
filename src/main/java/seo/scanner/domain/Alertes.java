package seo.scanner.domain;

public class Alertes {
	
	private Integer uid;
	private String description;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getProjetListUrlUid() {
		return projetListUrlUid;
	}
	public void setProjetListUrlUid(Integer projetListUrlUid) {
		this.projetListUrlUid = projetListUrlUid;
	}
	private Integer projetListUrlUid;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(Integer pourcentage) {
		this.pourcentage = pourcentage;
	}
	private String type;
	private Integer pourcentage = 0;

}
