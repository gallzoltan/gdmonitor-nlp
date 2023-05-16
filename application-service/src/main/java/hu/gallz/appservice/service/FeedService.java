package hu.gallz.appservice.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.gallz.appservice.model.Feed;
import hu.gallz.appservice.model.FeedMessage;
import hu.gallz.appservice.repository.FeedRepository;

@Service
public class FeedService {

	private static final Logger logger = LoggerFactory.getLogger(FeedService.class);	
	
	@Autowired
	private FeedRepository feedRepository;	
		
	public Feed getFeed(String url) {		
		URL feedUrl = isValid(url);
		return feedRepository.searchFeed(feedUrl).orElse(null);
	}
	
	public List<FeedMessage> getLastFeedMessages(Feed feed, LocalDateTime lastReadingDate) {		
		List<FeedMessage> result = new ArrayList<>();
		
		if (lastReadingDate.isBefore(feed.getLastBuildDateTime())) {
			for(FeedMessage fm: feed.getMessages()) {
				if (lastReadingDate.isBefore(fm.getPubDateTime())) {
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
