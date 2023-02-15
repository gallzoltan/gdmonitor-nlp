package hu.gallz.appservice.model;

public class FeedHeader {
	private String description = "";
    private String title = "";
    private String link = "";
    private String language = "";
    private String copyright = "";
    private String author = "";
    private String lastbuilddate = "";
    private String pubdate = "";
    private String guid = "";
    
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getLastbuilddate() {
		return lastbuilddate;
	}
	public void setLastbuilddate(String lastbuilddate) {
		this.lastbuilddate = lastbuilddate;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
    
}
