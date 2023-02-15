package hu.gallz.appservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.gallz.appservice.model.Feed;
import hu.gallz.appservice.service.FeedService;
import hu.gallz.configuration.GdMonitorConfig;

@Service
public class AppService {
	
	private static final Logger logger = LoggerFactory.getLogger(AppService.class);
	
	@Autowired
	private GdMonitorConfig config;
	
	@Autowired
	private FeedService feedService;
	
//	@Autowired
//	private EwsService ewsService;
//	
//	@Autowired
//	private PersistProps persistProps;

	public String startService() {
		
		Feed feed = feedService.getFeed(config.getLinks().getRssfeed());
		String lastbdate = feed.getLastbuilddate();
		logger.info("Last build date: {}", lastbdate);
		
//		MailContent mailContent = new MailContent();
//		mailContent.setBulletinLink("Link");
//		mailContent.setBulletinNumber("1202/2023. (II. 14.)");		
//		HashMap<String, List<String>> mailToList = persistProps.readMailAddresses();		
//		ewsService.sendEmail(mailContent, mailToList);
		
		return "end.";
	}
}
