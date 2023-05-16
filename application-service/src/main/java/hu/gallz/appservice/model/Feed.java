package hu.gallz.appservice.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Feed {
	private String title;
    private String lastbuilddate;
    private List<FeedMessage> messages = new ArrayList<>();
    
    public Feed(String title, String lastbuilddate) {
		super();
		this.title = title;
		this.lastbuilddate = lastbuilddate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLastbuilddate() {
		return lastbuilddate;
	}

	public void setLastbuilddate(String lastbuilddate) {
		this.lastbuilddate = lastbuilddate;
	}
	
	public LocalDateTime getLastBuildDateTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
		if(this.lastbuilddate == null) return null;
		return LocalDateTime.parse(this.lastbuilddate, formatter);
	}

	public List<FeedMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<FeedMessage> messages) {
		this.messages = messages;
	}

	public void addMessage(FeedMessage message){
        if(this.messages == null){
            this.messages = new ArrayList<>();
        }
        this.messages.add(message);
    }

//	@Override
//	public String toString() {
//		return "Feed [title=" + title + ", lastbuilddate=" + lastbuilddate + ", messages=" + messages + "]";
//	}
		
}
