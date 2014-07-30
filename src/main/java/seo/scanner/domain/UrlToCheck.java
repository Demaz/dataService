package seo.scanner.domain;

public class UrlToCheck {
	
	private Integer uid;
	private Integer projetListUrlUid;
	private String url;
	private String redirectionUrl1;
	private String redirectionUrl2;
	private String redirectionUrl3;
	private String redirectionUrlCode1;
	private String redirectionUrlCode2;
	private String redirectionUrlCode3;
	
	public Integer getProjetListUrlUid() {
		return projetListUrlUid;
	}

	public void setProjetListUrlUid(Integer projetListUrlUid) {
		this.projetListUrlUid = projetListUrlUid;
	}

	public UrlToCheck() {
		
	}
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRedirectionUrl1() {
		return redirectionUrl1;
	}
	public void setRedirectionUrl1(String redirectionUrl1) {
		this.redirectionUrl1 = redirectionUrl1;
	}
	public String getRedirectionUrl2() {
		return redirectionUrl2;
	}
	public void setRedirectionUrl2(String redirectionUrl2) {
		this.redirectionUrl2 = redirectionUrl2;
	}
	public String getRedirectionUrl3() {
		return redirectionUrl3;
	}
	public void setRedirectionUrl3(String redirectionUrl3) {
		this.redirectionUrl3 = redirectionUrl3;
	}
	public String getRedirectionUrlCode1() {
		return redirectionUrlCode1;
	}
	public void setRedirectionUrlCode1(String redirectionUrlCode1) {
		this.redirectionUrlCode1 = redirectionUrlCode1;
	}
	public String getRedirectionUrlCode2() {
		return redirectionUrlCode2;
	}
	public void setRedirectionUrlCode2(String redirectionUrlCode2) {
		this.redirectionUrlCode2 = redirectionUrlCode2;
	}
	public String getRedirectionUrlCode3() {
		return redirectionUrlCode3;
	}
	public void setRedirectionUrlCode3(String redirectionUrlCode3) {
		this.redirectionUrlCode3 = redirectionUrlCode3;
	}
	
}
