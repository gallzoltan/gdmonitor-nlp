package hu.gallz.appservice;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.gallz.appservice.model.FeedMessage;
import hu.gallz.appservice.service.FeedService;
import hu.gallz.appservice.service.PdfService;
import hu.gallz.appservice.util.PersistProps;
import hu.gallz.configuration.GdMonitorConfig;
import hu.gallz.emailservice.EwsService;
import hu.gallz.emailservice.model.MailContent;

@Service
public class AppService {
	
	private static final Logger logger = LoggerFactory.getLogger(AppService.class);	
	
	@Autowired
	private GdMonitorConfig config;
	
	@Autowired
	private FeedService feedService;
	
	@Autowired
	private PersistProps persistProps;
	
	@Autowired
	private PdfService pdfService;

	@Autowired
	private EwsService ewsService;
	
//	@Autowired
//	private NlpService nlpService;

	public String startService() {
		List<FeedMessage> feedMessages = searchFeedMessages();
		List<FeedMessage> afterFeedMessages = new ArrayList<>();
		
		if(feedMessages.size() > 0 ) {			
			afterFeedMessages = pdfService.readPages(feedMessages);
			logger.info("to examine: {}", feedMessages.size());
			logger.info("examine: {}", afterFeedMessages.size());
		}
		
		if(afterFeedMessages.size() > 0) {
			sendEmail(afterFeedMessages);			
		}
		
		
//		logger.info("NLP test: {}", nlpService.makeSummary("").toString());
		
		return "end.";
	}
	
	private void sendEmail(List<FeedMessage> afterFeedMessages) {
		for(FeedMessage feed: afterFeedMessages) {
			MailContent mailContent = new MailContent();
			
			mailContent.setBulletinNumber(feed.getTitle());
			mailContent.setPubDate(feed.getPubdate());
			mailContent.setBulletinLink(feed.getLink());
			feed.getPdfContents().forEach(c -> {                                    
				mailContent.addBulletinPage(c.getPgnumber());
            });
			HashMap<String, List<String>> mailToList = persistProps.readMailAddresses();		
			if(ewsService.sendEmail(mailContent, mailToList)) {
				logger.info("ready.");
			}
		}		
	}
	
	private List<FeedMessage> searchFeedMessages() {
		String rssfeed = config.getLinks().getRssfeed();
		LocalDateTime readMonitorLatest = persistProps.readMonitorLatest();
		List<FeedMessage> result = new ArrayList<>();
		
		if(feedService.isNewFeed(rssfeed, readMonitorLatest)) {			
			result = feedService.getFeedMessages(rssfeed, readMonitorLatest);
			for(FeedMessage feedmessage: result) {
				String fileName = String.format("MK_%s.szám.pdf", feedmessage.getTitleNumber());
				if(pdfService.downloadPDF(feedmessage.getDownloadLink(), config.getLinks().getDownpdf(), fileName)) {
					feedmessage.setPdf(fileName);
					feedmessage.setPdfPath(Path.of(config.getLinks().getDownpdf(), fileName));
				}
			}
		}
		
		return result;
	}
}
