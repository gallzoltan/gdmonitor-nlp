package hu.gallz.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.gallz.configuration.GdMonitorConfig;

@Service
public class EwsService {
	
	@Autowired
	private GdMonitorConfig config;
	
	public String initService() {
		return "EWS Service is run ok " + config.getLinks().getAppini();
	}

}
