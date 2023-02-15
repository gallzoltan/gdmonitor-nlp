package hu.gallz.appservice.model;

import java.util.ArrayList;
import java.util.List;

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
}
