package hu.gallz.appservice.repository;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import hu.gallz.appservice.model.FeedMessage;
import hu.gallz.appservice.util.FeedNodes;
import hu.gallz.appservice.util.StringConstants;

@Component("openai")
public class OpenaiFeedRepository implements FeedRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(OpenaiFeedRepository.class);
	
	@Override
	public String findBuildDate(URL feedUrl) {
		String lastBuildDate = "";
		try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(feedUrl.openStream());

            doc.getDocumentElement().normalize();
            Element channelElement = (Element) doc.getElementsByTagName(FeedNodes.CHANNEL).item(0);
            lastBuildDate = getElementValue(channelElement, FeedNodes.LASTBUILD_DATE);
            
		} catch (ParserConfigurationException | SAXException | IOException e) {
			logger.error("Error on findBuildDate(): {}", e);
        }
		
		return lastBuildDate;
	}

	@Override
	public List<FeedMessage> findItemsFromDate(URL feedUrl, LocalDateTime lastReadingDate) {
		List<FeedMessage> items = new ArrayList<>();
		
		try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(feedUrl.openStream());

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName(FeedNodes.ITEM);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String type = getAttributeValue(element, FeedNodes.ENCLOSURE, "type");
                    if (StringConstants.ENCLOSURE_TYPE.equals(type)) {
                    	String title = getElementValue(element, FeedNodes.TITLE);
                        String link = getElementValue(element, FeedNodes.LINK);
                        String pubDate = getElementValue(element, FeedNodes.PUB_DATE);
                        FeedMessage item = new FeedMessage(title, link, pubDate);
                        if (lastReadingDate.isBefore(item.getPubDateTime())) {                        	
                        	items.add(item);
                        }
                    }                    
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
        	logger.error("Error on findItems(): {}", e);
        }
		
		return items;
	}
	
	@Override
	public List<FeedMessage> findItems(URL feedUrl) {
		List<FeedMessage> items = new ArrayList<>();
		
		try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(feedUrl.openStream());

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName(FeedNodes.ITEM);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String type = getAttributeValue(element, FeedNodes.ENCLOSURE, "type");
                    if (StringConstants.ENCLOSURE_TYPE.equals(type)) {
                    	String title = getElementValue(element, FeedNodes.TITLE);
                        String link = getElementValue(element, FeedNodes.LINK);
                        String pubDate = getElementValue(element, FeedNodes.PUB_DATE);
                        
                        FeedMessage item = new FeedMessage(title, link, pubDate);
                        items.add(item);
                    }                    
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
        	logger.error("Error on findItems(): {}", e);
        }
		
		return items;
	}
	
	private String getElementValue(Element parentElement, String elementName) {
        NodeList nodeList = parentElement.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
	
	private String getAttributeValue(Element parentElement, String elementName, String attributeName) {
        NodeList nodeList = parentElement.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            return element.getAttribute(attributeName);
        }
        return null;
    }

}
