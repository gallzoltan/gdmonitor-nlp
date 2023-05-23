package hu.gallz.appservice.service;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
	
	public String extractGovernmentDecree(File pdfFile) {
		PDDocument document;
		int numberOfPages = 0;
		try {
			PDFTextStripper stripper = new PDFTextStripper();
			document = PDDocument.load(pdfFile);			
	        numberOfPages = document.getNumberOfPages();
	        
	        StringBuilder governmentDecreeBuilder = new StringBuilder();
	        boolean foundGovernmentDecree = false;
	        boolean startedGovernmentDecree = false;
	        
	        for (int i = 1; i <= numberOfPages; i++) {
	        	stripper.setStartPage(i);
	            stripper.setEndPage(i);
	            
	            String pageText = stripper.getText(document);
	            
	            // Az oldal szövegében keresd meg a kormányhatározat kezdetét és végét jelző szövegeket.
	            if (pageText.contains(".) Korm. rendelete")) {
	                foundGovernmentDecree = true;
	                startedGovernmentDecree = true;
	                governmentDecreeBuilder.append(pageText);
	            } else if (startedGovernmentDecree) {
	                governmentDecreeBuilder.append(pageText);

	                // Ellenőrizzük, hogy az oldal tartalmazza-e a kormányhatározat végét jelző szöveget.
	                if (pageText.contains("Orbán Viktor s. k., miniszterelnök")) {
	                    startedGovernmentDecree = false;
	                }
	            }
	        }
	        document.close();
	        //return governmentDecreeBuilder.toString();
		} catch (IOException e) {
			logger.info("Pdf read error: {}", e.getMessage());
		}
        
        return String.valueOf(numberOfPages);
	}
	
//	private PDDocument getDocument(File pdfFile) {
//		PDDocument document = null;
//		try {
//			return PDDocument.load(pdfFile);
//		} catch (IOException e) {
//			logger.info("Pdf read error: {}", e.getMessage());
//		}
//		return document;
//	}
//	
//	private String getPageText(int startOfPage, int endOfPage, PDDocument document) {
//		try {
//			PDFTextStripper stripper = new PDFTextStripper();
//			stripper.setStartPage(startOfPage);
//            stripper.setEndPage(endOfPage);
//            return stripper.getText(document);
//		} catch (IOException e) {
//			logger.info("Pdf stripper error: {}", e.getMessage());
//		}
//		return null;
//	}
}
