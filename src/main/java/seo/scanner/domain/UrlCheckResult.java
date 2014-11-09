package seo.scanner.domain;

import java.util.Date;

public class UrlCheckResult extends UrlToCheck {

	private Date checkDate;
	private Integer eventUid;

	public Date getCheckDate() {
		return checkDate;
	}

	public Integer getEventUid() {
		return eventUid;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public void setEventUid(Integer eventUid) {
		this.eventUid = eventUid;
	}

}
