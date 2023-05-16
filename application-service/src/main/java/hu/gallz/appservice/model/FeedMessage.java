package hu.gallz.appservice.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FeedMessage {
	private String title;
    private String link;
    private String pubdate;
    
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
	
	public String getPubdate() {
		return pubdate;
	}
	
	public LocalDateTime getPubDateTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
		if(this.pubdate == null) return null;
		return LocalDateTime.parse(this.pubdate, formatter);
	}
	
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	
	@Override
	public String toString() {
		return "FeedMessage [title=" + title + ", pubdate=" + pubdate + "]";
	}
    
}
