package hu.gallz.appservice.service;

import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.gallz.appservice.model.Feed;
import hu.gallz.appservice.repository.FeedRepository;

@Service
public class FeedService {

	private static final Logger logger = LoggerFactory.getLogger(FeedService.class);
	
	@Autowired
	private FeedRepository feedRepository;
	
	public Feed getFeed(String url) {
		URL feedUrl = isValid(url);
		return feedRepository.searchFeed(feedUrl);
	}
	
	public URL isValid(String url){
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
        	logger.error("Invalid URL: {}", url);
            throw new RuntimeException(e);
        }
    }
}
