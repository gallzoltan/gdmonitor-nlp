package hu.gallz.appservice.service;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hu.gallz.appservice.util.StringConstants;

@Service
public class GovernmentDecreeService {
	private static final Logger logger = LoggerFactory.getLogger(GovernmentDecreeService.class);	
	
	public Set<Integer> findKeywords(File pdfFile){
		Set<Integer> foundPages = new HashSet<Integer>();
		PDDocument document;
		int numberOfPages = 0;
		try {
			PDFTextStripper stripper = new PDFTextStripper();
			document = PDDocument.load(pdfFile);			
	        numberOfPages = document.getNumberOfPages();
			for (int i = 1; i <= numberOfPages; i++) {
				stripper.setStartPage(i);
	            stripper.setEndPage(i);
	            
	            String content = stripper.getText(document).toLowerCase();
				if (content.contains(StringConstants.SEARCH_1) || content.contains(StringConstants.SEARCH_2) || content.contains(StringConstants.SEARCH_3) || content.contains(StringConstants.SEARCH_4)) {
					foundPages.add(i);
				}
			}
			document.close();
			
			return foundPages;
		} catch (IOException e) {
			logger.info("Pdf read error: {}", e.getMessage());
		}
		return null;
	}
	
	public String extractGovernmentDecree(File pdfFile, int startPage) {
		PDDocument document;
		try {
			PDFTextStripper stripper = new PDFTextStripper();
			document = PDDocument.load(pdfFile);
	        
	        stripper.setStartPage(startPage);
            stripper.setEndPage(startPage);
            String pageText = stripper.getText(document);
            
	        document.close();
	        return findDecreeNumber(pageText);
		} catch (IOException e) {
			logger.info("Pdf read error: {}", e.getMessage());
		}
        
        return "";
	}
	
	private String findDecreeNumber(String content) {
	    Pattern pattern = Pattern.compile(StringConstants.REG_DECREE_NUMBER);
	    Matcher matcher = pattern.matcher(content);
	    String closestNumber = null;
	    int closestDistance = Integer.MAX_VALUE;
	    int keywordPosition = Integer.MAX_VALUE;

	    while (matcher.find()) {
	        int matchStart = matcher.start();
	        //int matchEnd = matcher.end();
	        int distance = Math.abs(matchStart - keywordPosition);

	        if (distance < closestDistance) {
	            closestDistance = distance;
	            closestNumber = matcher.group();
	        }
	    }

	    return closestNumber;
	}
}
