package hu.gallz.appservice.repository;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;
import hu.gallz.appservice.model.FeedMessage;

@Repository
public interface FeedRepository {
	
	List<FeedMessage> findItems(URL feedUrl);
	List<FeedMessage> findItemsFromDate(URL feedUrl, LocalDateTime lastReadingDate);
	String findBuildDate(URL feedUrl);
}
