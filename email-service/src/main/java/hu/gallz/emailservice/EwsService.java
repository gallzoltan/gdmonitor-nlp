package hu.gallz.emailservice;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import hu.gallz.emailservice.model.MailContent;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

@Service
public class EwsService {
	
	private static final Logger logger = LoggerFactory.getLogger(EwsService.class);
		
	@Autowired
	@Qualifier("ews")
	private ExchangeService exchangeService;
		
	public Boolean sendEmail(MailContent content, HashMap<String, List<String>> mailToList) {
		try {			
			EmailMessage msg = new EmailMessage(exchangeService);
			
			msg.setSubject("IX. fejezettel kapcsolatos kormányhatározat");
			MessageBody mb = MessageBody.getMessageBodyFromText(createBody(content));
			msg.setBody(mb);
			
			for(String to: mailToList.get("tomails")){
				msg.getToRecipients().add(to);
			}
			for(String cc: mailToList.get("ccmails")){
				msg.getCcRecipients().add(cc);
			}
			for(String bcc: mailToList.get("bccmails")){
				msg.getBccRecipients().add(bcc);
			}
			
			msg.send();	
			logger.info("Email is sent.");
		} catch (Exception e) {
			logger.error("Error on mail sending {}", e);
			return false;
		}
		return true;
	}
	
	public String createBody(MailContent msg) {
		return "body";
//		return String.format("<p><a href=\"%s\">%s</a></p>"
//                + "<p>Dátuma: %s</p>"
//                + "<p>Oldalszámok: %s</p>", msg.getBulletinLink(), msg.getBulletinNumber(), msg.getPubDate(), msg.getBulletinPages());
	}

}
