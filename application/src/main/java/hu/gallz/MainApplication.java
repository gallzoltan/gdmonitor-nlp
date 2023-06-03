package hu.gallz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.gallz.appservice.AppService;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);
	
	@Autowired
	private AppService appService;	
		
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		logger.info("Console: started");
		logger.info("AppService: {}", appService.startService());
		
//		logger.info("EmailService: {}", ewsService.initService());
//		if(!appService.startService().equals("")) {
//			ewsService.sendEmail(null);
//		}
	}

}
