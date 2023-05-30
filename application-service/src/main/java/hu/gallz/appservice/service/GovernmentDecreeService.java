package hu.gallz.appservice.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hu.gallz.appservice.model.Decree;
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
	
	public String extractGovernmentDecree(File pdfFile, int pageNumber) {
		PDDocument document;
		try {
			PDFTextStripper stripper = new PDFTextStripper();
			document = PDDocument.load(pdfFile);
	        
	        stripper.setStartPage(pageNumber);
            stripper.setEndPage(pageNumber);
            String pageText = stripper.getText(document);
            document.close();
            
            int keyWord1 = pageText.toLowerCase().indexOf(StringConstants.SEARCH_1, 0);
            int keyWord2 = pageText.toLowerCase().indexOf(StringConstants.SEARCH_2, 0);
            int keyWord3 = pageText.toLowerCase().indexOf(StringConstants.SEARCH_3, 0);
            int keyWord4 = pageText.toLowerCase().indexOf(StringConstants.SEARCH_4, 0);
            
            if(keyWord1 > -1) {
            	return findDecreeNumber(pageText, keyWord1);
            }
            if(keyWord2 > -1) {
            	return findDecreeNumber(pageText, keyWord2);
            }
            if(keyWord3 > -1) {
            	return findDecreeNumber(pageText, keyWord3);
            }
            if(keyWord4 > -1) {
            	return findDecreeNumber(pageText, keyWord4);
            }
            
		} catch (IOException e) {
			logger.info("Pdf read error: {}", e.getMessage());
		}
        
        return "";
	}
	
	public List<Decree> extractDecreesFromPDF(File filePath) {
		List<Decree> decrees = new ArrayList<>();
		int numberOfPages = 0;
		
		int pageNumber = 0;
		String decreeNumber = null;		
		StringBuilder decreeText = new StringBuilder();		
		
        try {
            PDDocument document = PDDocument.load(filePath);
            PDFTextStripper stripper = new PDFTextStripper();
            numberOfPages = document.getNumberOfPages();
            
            for(int i = 1; i <= numberOfPages; i++) {
            	stripper.setStartPage(i);
	            stripper.setEndPage(i);
	            String content = stripper.getText(document);
	            String[] lines = content.split("\\r?\\n"); // Sorok szétválasztása
	            for (String line : lines) {
	            	if (line.startsWith("A Kormány") && line.endsWith("Korm. határozata")) {
	            		decreeNumber = line;
	            		pageNumber = i;	            		
	            	}
	            	if(decreeNumber != null) {
	            		decreeText.append(line).append(System.lineSeparator());
	            	}
	            	if (line.endsWith("Orbán Viktor s. k.,") && decreeNumber != null) {
	            		Decree decree = new Decree(pageNumber, decreeNumber, decreeText.toString());
	            		decrees.add(decree);
	            		
	            		decreeText.setLength(0); // Reset decreeText
	            		decreeNumber = null;
	            		pageNumber = 0;
	            	}
	            }
            }

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return decrees;
	}
	
	private String findDecreeNumber(String content, int keywordPosition) {
	    Pattern pattern = Pattern.compile(StringConstants.REG_DECREE_NUMBER);
	    Matcher matcher = pattern.matcher(content);
	    String closestNumber = "";
	    int closestDistance = Integer.MAX_VALUE;
	    //int keywordPosition = Integer.MAX_VALUE;

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
