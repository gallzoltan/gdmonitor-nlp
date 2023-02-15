package hu.gallz.appservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FeedServiceTest {
	
	@Autowired
	FeedService feedService;

	@Test
	public void testThatUrlIsValid() {
		//String url = "https://magyarkozlony.hu/feed";
		//assertThat(feedService.isValid(url).getPath().equals(url));
	}
}
