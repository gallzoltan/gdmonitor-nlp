package hu.gallz.appservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.gallz.appservice.model.Feed;
import hu.gallz.appservice.model.FeedMessage;
import hu.gallz.appservice.service.FeedService;
import hu.gallz.appservice.util.PersistProps;
import hu.gallz.configuration.GdMonitorConfig;

@Service
public class AppService {
	
	private static final Logger logger = LoggerFactory.getLogger(AppService.class);	
	
	@Autowired
	private GdMonitorConfig config;
	
	@Autowired
	private FeedService feedService;
	
	@Autowired
	private PersistProps persistProps;

//	@Autowired
//	private EwsService ewsService;
	
//	@Autowired
//	private NlpService nlpService;

	public String startService() {
		Feed feed = feedService.getFeed(config.getLinks().getRssfeed()); 
		if(feed == null) return "There is no feed";	
				
		List<FeedMessage> feedMessages = feedService.getLastFeedMessages(feed, persistProps.readMonitorLatest());
		logger.info("RSS Messages: {}", feedMessages.toString());
		
//		MailContent mailContent = new MailContent();
//		mailContent.setBulletinLink("Link");
//		mailContent.setBulletinNumber("1202/2023. (II. 14.)");		
//		HashMap<String, List<String>> mailToList = persistProps.readMailAddresses();		
//		ewsService.sendEmail(mailContent, mailToList);
		
//		logger.info("NLP test: {}", nlpService.makeSummary("").toString());
		
		return "end.";
	}
}
