package hu.gallz.emailservice.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hu.gallz.configuration.GdMonitorConfig;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;

@Configuration
public class EwsConfig {
	private static final Logger logger = LoggerFactory.getLogger(EwsConfig.class);
	
	@Autowired
	private GdMonitorConfig config;
	
	@Bean("ews")
	public ExchangeService ewsMailService() {
		ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
        ExchangeCredentials credentials = new WebCredentials(config.getUsername(), config.getPassword()); 
        service.setCredentials(credentials);
        try {
            service.setUrl(new URI(config.getLinks().getEwsuri()));
        } catch (URISyntaxException ex) {
            logger.info("Error on set exchange URI - {}", ex);
        }
        
        return service;
	}
}
