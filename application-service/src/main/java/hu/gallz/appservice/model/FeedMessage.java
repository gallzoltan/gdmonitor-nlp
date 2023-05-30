package hu.gallz.appservice.model;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FeedMessage {
	private String title;
    private String link;
    private String pubdate; 
    private String pdfFile;
    private Path pdfPath;
    private List<Decree> decrees;
    
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

	public String getPdfFile() {
		return pdfFile;
	}

	public void setPdfFile(String pdfFile) {
		this.pdfFile = pdfFile;
	}

	public Path getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(Path pdfPath) {
		this.pdfPath = pdfPath;
	}

	public List<Decree> getDecrees() {
		return decrees;
	}

	public void setDecrees(List<Decree> decrees) {
		this.decrees = decrees;
	}
	
	public void addDecree(Decree decree) {
		if(this.decrees == null)
			this.decrees = new ArrayList<>();
		this.decrees.add(decree);
	}
    
}
