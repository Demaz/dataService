package seo.scanner.domain;

public class Projet {
	
	private String name;
	
	private String Description;
	
	private String Domaine;
	
	private Integer userUid;
	
	
	public Integer getUserUid() {
		return userUid;
	}

	public void setUserUid(Integer userUid) {
		this.userUid = userUid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	private Integer uid;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getDomaine() {
		return Domaine;
	}

	public void setDomaine(String domaine) {
		Domaine = domaine;
	}


}
