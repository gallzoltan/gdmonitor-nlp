package hu.gallz.appservice;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.gallz.appservice.model.FeedMessage;
import hu.gallz.appservice.model.PdfContent;
import hu.gallz.appservice.service.FeedService;
import hu.gallz.appservice.service.GovernmentDecreeService;
import hu.gallz.appservice.service.DownloadService;
import hu.gallz.appservice.util.PersistProps;
import hu.gallz.configuration.GdMonitorConfig;
import hu.gallz.emailservice.EwsService;
import hu.gallz.emailservice.model.DecreeInfo;
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
	private DownloadService downloadService;
	
	@Autowired
	private GovernmentDecreeService governmentDecreeService;

	@Autowired
	private EwsService ewsService;
	
//	@Autowired
//	private NlpService nlpService;

	public String startService() {
		List<FeedMessage> feedMessages = searchFeedMessages();
		List<FeedMessage> foundFeedMessages = new ArrayList<>();
		
		if(feedMessages.size() > 0 ) {
			for(FeedMessage feedMessage: feedMessages) {
				Boolean flag = false;
				Set<Integer> foundPages = governmentDecreeService.findKeywords(feedMessage.getPdfPath().toFile());
				if(foundPages != null) {
					for(int pageNumber: foundPages) {
						String decreeNumber = governmentDecreeService.extractGovernmentDecree(feedMessage.getPdfPath().toFile(), pageNumber);
						PdfContent content = new PdfContent(pageNumber, decreeNumber, "");
						feedMessage.addPdfContent(content);
						flag = true;
					}
				}
				if(flag)
					foundFeedMessages.add(feedMessage);
			}
			logger.info("Releváns / Megjelent közlöny: {}/{} db", foundFeedMessages.size(), feedMessages.size());
		}
		
		if(foundFeedMessages.size() > 0) {
			int sent = 0;
			for(FeedMessage feed: foundFeedMessages) {
				if(sendingEmail(feed)) {
					sent++;
				}
			}
			logger.info("Elküldve / Küldendő: {}/{} db", sent, foundFeedMessages.size());
			if(sent > 0)
				persistProps.writeMonitorLatest(LocalDateTime.now().plusHours(config.getTimeoffset()).withNano(0).toString());			
		} else {
			persistProps.writeMonitorLatest(LocalDateTime.now().plusHours(config.getTimeoffset()).withNano(0).toString());
		}
		
		
//		logger.info("NLP test: {}", nlpService.makeSummary("").toString());
		
		return "end.";
	}
	
	private Boolean sendingEmail(FeedMessage feed) {
		MailContent mailContent = new MailContent();		
		mailContent.setBulletinNumber(feed.getTitle());
		mailContent.setPubDate(feed.getPubdate());
		mailContent.setBulletinLink(feed.getLink());
		feed.getPdfContents().forEach(c -> {				
			mailContent.addDecreeInfo(new DecreeInfo(c.getPgnumber(), c.getDecree()));
        });
		HashMap<String, List<String>> mailToList = persistProps.readMailAddresses();		
		if(ewsService.sendEmail(mailContent, mailToList)) {
			logger.info("ready.");
			return true;
		}		
		return false;
	}
	
	private List<FeedMessage> searchFeedMessages() {
		String rssfeed = config.getLinks().getRssfeed();
		LocalDateTime readMonitorLatest = persistProps.readMonitorLatest();
		List<FeedMessage> result = new ArrayList<>();
		
		if(feedService.isNewFeed(rssfeed, readMonitorLatest)) {			
			result = feedService.getFeedMessages(rssfeed, readMonitorLatest);
			for(FeedMessage feedmessage: result) {
				String fileName = String.format("MK_%s.szám.pdf", feedmessage.getTitleNumber());
				if(downloadService.downloadPDF(feedmessage.getDownloadLink(), config.getLinks().getDownpdf(), fileName)) {
					feedmessage.setPdfFile(fileName);
					feedmessage.setPdfPath(Path.of(config.getLinks().getDownpdf(), fileName));
				}
			}
		}
		
		return result;
	}
}
