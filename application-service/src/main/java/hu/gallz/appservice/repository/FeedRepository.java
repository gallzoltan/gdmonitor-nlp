package hu.gallz.appservice.repository;

import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Repository;
import hu.gallz.appservice.model.FeedMessage;

@Repository
public interface FeedRepository {
	
	List<FeedMessage> findItems(URL feedUrl);
	String findBuildDate(URL feedUrl);
}
