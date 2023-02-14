package hu.gallz.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "gdmonitor")
public class GdMonitorConfig {
	
	private Links links = new Links();
    private String username;
    private String password;
	
	public class Links {
    	private String rssfeed;
    	private String ewsuri;
        private String downpdf;
        private String appini;
        
		public String getRssfeed() {
			return rssfeed;
		}
		public void setRssfeed(String rssfeed) {
			this.rssfeed = rssfeed;
		}		
		public String getEwsuri() {
			return ewsuri;
		}
		public void setEwsuri(String ewsuri) {
			this.ewsuri = ewsuri;
		}
		public String getDownpdf() {
			return downpdf;
		}
		public void setDownpdf(String downpdf) {
			this.downpdf = downpdf;
		}
		public String getAppini() {
			return appini;
		}
		public void setAppini(String appini) {
			this.appini = appini;
		}        
    }
	
	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
