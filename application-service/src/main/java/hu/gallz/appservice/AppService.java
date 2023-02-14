package hu.gallz.appservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.gallz.appservice.util.PersistProps;
import hu.gallz.emailservice.EwsService;
import hu.gallz.emailservice.model.MailContent;

@Service
public class AppService {
	
	@Autowired
	private EwsService ewsService;
	
	@Autowired
	private PersistProps persistProps;

	public String startService() {
		MailContent mailContent = new MailContent();
		mailContent.setBulletinLink("Link");
		mailContent.setBulletinNumber("1202/2023. (II. 14.)");
		
		HashMap<String, List<String>> mailToList = persistProps.readMailAddresses();		
		
		ewsService.sendEmail(mailContent, mailToList);
		return "is run ok";
	}
}
