package hu.gallz.appservice.repository;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import hu.gallz.appservice.model.Feed;
import hu.gallz.appservice.model.FeedHeader;
import hu.gallz.appservice.model.FeedMessage;
import hu.gallz.appservice.util.FeedNodes;

@Repository
public class FeedRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(FeedRepository.class);

	public Feed searchFeed(URL feedUrl){		
        Feed feedResult = null;
        try {
        	boolean isFeedHeader = true;
            FeedHeader header = new FeedHeader();
            
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in = read(feedUrl);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            while (eventReader.hasNext()) {
            	XMLEvent event = eventReader.nextEvent();
            	if (event.isStartElement()) {
            		String localPart = event.asStartElement().getName().getLocalPart();
            		switch (localPart) {
            			case FeedNodes.ITEM-> {
	                        if (isFeedHeader) {
	                            isFeedHeader = false;
	                            feedResult = new Feed(header.getTitle(), header.getLastbuilddate());
	                        }
	                        event = eventReader.nextEvent();
        				}
            			case FeedNodes.TITLE -> header.setTitle(getCharacterData(event, eventReader));
                        case FeedNodes.DESCRIPTION -> header.setDescription(getCharacterData(event, eventReader));
                        case FeedNodes.LINK -> header.setLink(getCharacterData(event, eventReader));
                        case FeedNodes.GUID -> header.setGuid(getCharacterData(event, eventReader));
                        case FeedNodes.LANGUAGE -> header.setLanguage(getCharacterData(event, eventReader));
                        case FeedNodes.AUTHOR -> header.setAuthor(getCharacterData(event, eventReader));
                        case FeedNodes.PUB_DATE -> header.setPubdate(getCharacterData(event, eventReader));
                        case FeedNodes.LASTBUILD_DATE -> header.setLastbuilddate(getCharacterData(event, eventReader));
                        case FeedNodes.COPYRIGHT -> header.setCopyright(getCharacterData(event, eventReader));
            		} 
            	} else if(event.isEndElement()){
            		if (event.asEndElement().getName().getLocalPart() == null ? (FeedNodes.ITEM) == null : event.asEndElement().getName().getLocalPart().equals(FeedNodes.ITEM)) {
                        FeedMessage message = new FeedMessage();
                        message.setTitle(header.getTitle());
                        message.setLink(header.getLink());
                        message.setPubdate(header.getPubdate());
                        feedResult.addMessage(message);
                        event = eventReader.nextEvent();
                    }
            	}
            }                       
		} catch (Exception e) {
			logger.error("Error on Feed search");
            throw new RuntimeException(e);
		} 
        return feedResult;
	}
	
	private InputStream read(URL url) {
        try {
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            return conn.getInputStream();
            //return url.openStream();
        } catch (IOException e) {
        	logger.error("Error on read URL: {}", url);
            throw new RuntimeException(e);
        }
    }
	
	private String getCharacterData(XMLEvent event, XMLEventReader eventReader) {
        String result = "";
        try {
			event = eventReader.nextEvent();
			if (event instanceof Characters) {
	            result = event.asCharacters().getData();
	        }
			return result;
		} catch (XMLStreamException e) {
			logger.error("Error on read feed xml");
			throw new RuntimeException(e);
		}               
    }
}
