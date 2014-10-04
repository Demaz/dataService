package seo.scanner.domain;

import java.util.ArrayList;
import java.util.List;

public class ProjetListUrl {

	private Integer uid;
	private Integer projetUid;
	private String name;
	private Integer typeUid;
	private Integer nbUrl;

	private String description;

	public Integer getNbUrl() {
		return nbUrl;
	}

	public void setNbUrl(Integer nbUrl) {
		this.nbUrl = nbUrl;
	}
	private List<UrlToCheck> urlToCheckList;

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public Integer getProjetUid() {
		return projetUid;
	}

	public Integer getTypeUid() {
		return typeUid;
	}

	public Integer getUid() {
		return uid;
	}

	public List<UrlToCheck> getUrlToCheckList() {
		if (urlToCheckList == null) {
			urlToCheckList = new ArrayList<UrlToCheck>();
		}
		return urlToCheckList;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setProjetUid(Integer projetUid) {
		this.projetUid = projetUid;
	}

	public void setTypeUid(Integer typeUid) {
		this.typeUid = typeUid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public void setUrlToCheckList(List<UrlToCheck> urlToCheckList) {
		this.urlToCheckList = urlToCheckList;
	}

}
