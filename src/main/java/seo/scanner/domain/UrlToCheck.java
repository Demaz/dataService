package seo.scanner.domain;

import com.googlecode.jcsv.annotations.MapToColumn;

public class UrlToCheck {
	
	private Integer uid;
	private Integer projetListUrlUid;
	@MapToColumn(column=0)
	private String url;
	@MapToColumn(column=1)
	private String redirectionUrl1;
	@MapToColumn(column=3)
	private String redirectionUrl2;
	@MapToColumn(column=5)
	private String redirectionUrl3;
	@MapToColumn(column=2)
	private String redirectionUrlCode1;
	@MapToColumn(column=4)
	private String redirectionUrlCode2;
	@MapToColumn(column=6)
	private String redirectionUrlCode3;
	
	public Integer getProjetListUrlUid() {
		return projetListUrlUid;
	}

	public void setProjetListUrlUid(Integer projetListUrlUid) {
		this.projetListUrlUid = projetListUrlUid;
	}
	
	public UrlToCheck() {
		
	}

	public UrlToCheck(String url,String redirectionUrl1,String redirectionUrlCode1,String redirectionUrl2,String redirectionUrlCode2, String redirectionUrl3,String redirectionUrlCode3) {
		this.url = url;
		this.redirectionUrl1 = redirectionUrl1;
		this.redirectionUrlCode1 = redirectionUrlCode1;
		
		this.redirectionUrl2 = redirectionUrl2;
		this.redirectionUrlCode2 = redirectionUrlCode2;
		
		this.redirectionUrl3 = redirectionUrl3;
		this.redirectionUrlCode3 = redirectionUrlCode3;
	}
	
	public UrlToCheck(String url) {
		setUrl(url);
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
