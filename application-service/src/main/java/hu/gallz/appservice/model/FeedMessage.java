package hu.gallz.appservice.model;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FeedMessage {
	private String title;
    private String link;
    private String pubdate; 
    private String pdf;
    private Path pdfPath;
    
	public FeedMessage() {
		super();
	}
		
	public FeedMessage(String title, String link, String pubdate) {
		super();
		this.title = title;
		this.link = link;
		this.pubdate = pubdate;
	}

	public String getTitle() {
		return title;
	}
	
	public String getTitleNumber() {
		int from = this.title.indexOf("évi");
		int to = this.title.indexOf("szám");
		from = (from == -1) ? 0 : from + 4;
		to = (to == -1) ? 6 : to - 2;
		return title.substring(from, to);
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLink() {
		return link;
	}
	
	public String getDownloadLink() {
		if(this.link.isEmpty()) return null;
		return this.link.replace("megtekintes", "letoltes");
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

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public Path getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(Path pdfPath) {
		this.pdfPath = pdfPath;
	}

	@Override
	public String toString() {
		return "FeedMessage [title=" + title + ", pubdate=" + pubdate + ", pdf="+ pdf + "]";
	}
    
}
