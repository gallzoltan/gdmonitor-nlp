package hu.gallz.emailservice.model;

import java.util.ArrayList;
import java.util.List;

public class MailContent {
	private String pubDate;
    private String bulletinNumber;
    private String bulletinLink;
    private List<DecreeInfo> decreeInfos;
    
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
	
	public List<DecreeInfo> getDecreeInfos() {
		return decreeInfos;
	}
	public void setDecreeInfos(List<DecreeInfo> decreeInfos) {
		this.decreeInfos = decreeInfos;
	}

	public void addDecreeInfo(DecreeInfo info){
        if(this.decreeInfos == null) {
            this.decreeInfos = new ArrayList<>();
        }
        this.decreeInfos.add(info);
    }
}
