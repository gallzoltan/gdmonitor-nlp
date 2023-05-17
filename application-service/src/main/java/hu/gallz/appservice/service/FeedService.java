package hu.gallz.appservice.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import hu.gallz.appservice.model.FeedMessage;
import hu.gallz.appservice.repository.FeedRepository;

@Service
public class FeedService {

	private static final Logger logger = LoggerFactory.getLogger(FeedService.class);	
	
	@Autowired
	@Qualifier("openai")
	private FeedRepository feedRepository;
	
	public Boolean isNewFeed(String url, LocalDateTime lastReadingDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
		Boolean result = false;
		URL feedUrl = isValid(url);
		String lastBuildDate = feedRepository.findBuildDate(feedUrl);
		if(!lastBuildDate.isEmpty()) {
			LocalDateTime builddate = LocalDateTime.parse(lastBuildDate, formatter);
			if (lastReadingDate.isBefore(builddate)) {
				result = true;
			}
		}
		
		return result;
	}
	
	public List<FeedMessage> getFeedMessages(String url, LocalDateTime lastReadingDate) {
		URL feedUrl = isValid(url);
		return feedRepository.findItemsFromDate(feedUrl, lastReadingDate);
	}
	
	private URL isValid(String url){
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
        	logger.error("Invalid URL: {}", url);
            throw new RuntimeException(e);
        }
    }
}
