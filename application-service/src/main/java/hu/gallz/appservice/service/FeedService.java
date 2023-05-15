package hu.gallz.appservice.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.gallz.appservice.model.Feed;
import hu.gallz.appservice.model.FeedMessage;
import hu.gallz.appservice.repository.FeedRepository;
import hu.gallz.appservice.util.PersistProps;

@Service
public class FeedService {

	private static final Logger logger = LoggerFactory.getLogger(FeedService.class);
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
	
	@Autowired
	private FeedRepository feedRepository;
	
	@Autowired
	private PersistProps persistProps;
		
	public Feed getFeed(String url) {		
		URL feedUrl = isValid(url);
		return feedRepository.searchFeed(feedUrl).orElse(null);
	}
	
	public List<FeedMessage> getLastFeedMessages(Feed feed) {
		LocalDateTime lastReadingDate = LocalDateTime.parse(persistProps.readMonitorLatest());
		LocalDateTime lastRssBuildDate = LocalDateTime.parse(feed.getLastbuilddate(), formatter);
		List<FeedMessage> result = new ArrayList<>();
		
		if (lastReadingDate.isBefore(lastRssBuildDate)) {
			for(FeedMessage fm: feed.getMessages()) {
				LocalDateTime lastPubDate = LocalDateTime.parse(fm.getPubdate(), formatter);
				if (lastReadingDate.isBefore(lastPubDate)) {
					if(fm.getTitle().contains("Magyar Közlöny")) {
						result.add(fm);
					}
				}
			}
		}
		return result;
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
