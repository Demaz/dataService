package seo.scanner.domain;

import java.util.ArrayList;
import java.util.List;

public class ProjetListUrl {

	private String description;
	private List<Event> eventsDone;
	private List<Event> eventsToDo;
	private String name;
	private Integer nbUrl;
	private Integer projetUid;

	private Integer typeUid;

	private Integer uid;

	private List<UrlToCheck> urlToCheckList;

	public String getDescription() {
		return description;
	}

	public List<Event> getEventsDone() {
		return eventsDone;
	}

	public List<Event> getEventsToDo() {
		return eventsToDo;
	}

	public String getName() {
		return name;
	}

	public Integer getNbUrl() {
		return nbUrl;
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

	public void setEventsDone(List<Event> eventsDone) {
		this.eventsDone = eventsDone;
	}

	public void setEventsToDo(List<Event> eventsToDo) {
		this.eventsToDo = eventsToDo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNbUrl(Integer nbUrl) {
		this.nbUrl = nbUrl;
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
