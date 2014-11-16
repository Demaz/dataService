package seo.scanner.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics {

	private Map<String, Integer> responseStatusStats = new HashMap<String, Integer>();
	private List<UrlCheckResult> urlCheckResults = new ArrayList<UrlCheckResult>();

	public Map<String, Integer> getResponseStatusStats() {
		return responseStatusStats;
	}

	public List<UrlCheckResult> getUrlCheckResults() {
		return urlCheckResults;
	}

	public void setResponseStatusStats(Map<String, Integer> responseStatusStats) {
		this.responseStatusStats = responseStatusStats;
	}

	public void setUrlCheckResults(List<UrlCheckResult> urlCheckResults) {
		this.urlCheckResults = urlCheckResults;
	}

}
