package seo.scanner.domain;

import java.util.ArrayList;
import java.util.List;

public class ProjetListUrl {
	
	private Integer uid;
	private Integer projetUid;
	private String name;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getProjetUid() {
		return projetUid;
	}
	public void setProjetUid(Integer projetUid) {
		this.projetUid = projetUid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<UrlToCheck> getUrlToCheckList() {
		if(urlToCheckList == null) {
			urlToCheckList = new ArrayList<UrlToCheck>();
		}
		return urlToCheckList;
	}
	public void setUrlToCheckList(List<UrlToCheck> urlToCheckList) {
		this.urlToCheckList = urlToCheckList;
	}
	private String description;
	private List<UrlToCheck> urlToCheckList;

}
