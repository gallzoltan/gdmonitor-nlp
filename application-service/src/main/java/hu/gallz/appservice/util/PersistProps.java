package hu.gallz.appservice.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ini4j.Profile.Section;
import org.ini4j.Wini;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.gallz.configuration.GdMonitorConfig;

@Component
public class PersistProps {
	
	private static final Logger logger = LoggerFactory.getLogger(PersistProps.class);

	@Autowired
	private GdMonitorConfig config;
	
	public void writeMonitorLatest(String lastdate) {
        try {
            Wini ini = new Wini(new File(config.getLinks().getAppini()));
            ini.put("monitor", "latest", lastdate);
            ini.store();
        } catch (IOException ex) {
        	logger.error("Write monitor error: {}", ex);
            throw new RuntimeException(ex);
        }
    }

    public String readMonitorLatest() {
        String lastdate;
        try {
            Wini ini = new Wini(new File(config.getLinks().getAppini()));
            lastdate = ini.get("monitor", "latest");
        } catch (IOException ex) {
        	logger.error("Read monitor error: {}", ex);
            throw new RuntimeException(ex);
        }
        return lastdate;
    }
     
    public HashMap<String, List<String>> readMailAddresses() {
		HashMap<String, List<String>> sendingAddresses = new HashMap<>();
		sendingAddresses.put("tomails", getAddresses("tomails"));
		sendingAddresses.put("ccmails", getAddresses("ccmails"));
		sendingAddresses.put("bccmails", getAddresses("bccmails"));
		
		return sendingAddresses;    	
    }
    
    private List<String> getAddresses(String key){
    	List<String> maillist = new ArrayList<>();
    	try {
    		Wini ini = new Wini(new File(config.getLinks().getAppini()));
    		Section section = ini.get(key);
    		for (String name: section.keySet()) {
    			maillist.add(ini.get(key, name));    		    
    		}
    		
    		return maillist;
		} catch (Exception e) {
			logger.error("Read emails error: {}", e);
			throw new RuntimeException(e);
		}    	    
    }
}
