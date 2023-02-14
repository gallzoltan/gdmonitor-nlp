package hu.gallz.emailservice.model;

import java.util.List;

public class MailContent {
	private String pubDate;
    private String bulletinNumber;
    private String bulletinLink;
    private List<Integer> bulletinPages;
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getBulletinNumber() {
		return bulletinNumber;
	}
	public void setBulletinNumber(String bulletinNumber) {
		this.bulletinNumber = bulletinNumber;
	}
	public String getBulletinLink() {
		return bulletinLink;
	}
	public void setBulletinLink(String bulletinLink) {
		this.bulletinLink = bulletinLink;
	}
	public List<Integer> getBulletinPages() {
		return bulletinPages;
	}
	public void setBulletinPages(List<Integer> bulletinPages) {
		this.bulletinPages = bulletinPages;
	}
    
}
