package hu.gallz.appservice.service;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GovernmentDecreeService {
	private static final Logger logger = LoggerFactory.getLogger(GovernmentDecreeService.class);	
	
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
	        return governmentDecreeBuilder.toString();
		} catch (IOException e) {
			logger.info("Pdf read error: {}", e.getMessage());
		}
        
        return String.valueOf(numberOfPages);
	}
}
