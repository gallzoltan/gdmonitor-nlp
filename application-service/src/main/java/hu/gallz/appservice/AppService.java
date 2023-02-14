package hu.gallz.appservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.gallz.emailservice.EwsService;
import hu.gallz.emailservice.model.MailContent;

@Service
public class AppService {
	
	@Autowired
	private EwsService ewsService;

	public String startService() {
		MailContent content = new MailContent();
		ewsService.sendEmail(content);
		return "is run ok";
	}
}
