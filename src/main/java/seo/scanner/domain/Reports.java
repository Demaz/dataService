package seo.scanner.domain;

import java.util.List;

public class Reports {

	private List<UrlCheckResult> CheckUrlResultList;
	private Statistics statistics;

	public List<UrlCheckResult> getCheckUrlResultList() {
		return CheckUrlResultList;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public void setCheckUrlResultList(List<UrlCheckResult> checkUrlResultList) {
		CheckUrlResultList = checkUrlResultList;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

}
