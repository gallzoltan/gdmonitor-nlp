package hu.gallz.appservice.service;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import hu.gallz.appservice.model.FeedMessage;
import hu.gallz.appservice.model.PdfContent;
import hu.gallz.appservice.util.StringConstants;

@Service
public class PdfService {
	
	private static final Logger logger = LoggerFactory.getLogger(PdfService.class);
	
    public Boolean downloadPDF(String url, String saveDirectory, String fileName) {
        URL pdfUrl;
		try {
			pdfUrl = new URL(url);
		
	        Path savePath = Path.of(saveDirectory, fileName);
	        
	        try (BufferedInputStream in = new BufferedInputStream(pdfUrl.openStream());
	        	FileOutputStream out = new FileOutputStream(savePath.toFile())) {
	            byte[] dataBuffer = new byte[1024];
	            int bytesRead;
	            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
	                out.write(dataBuffer, 0, bytesRead);
	            }
	        }
	        
	        if (!fileName.endsWith(".pdf")) {
	            Path newFilePath = Path.of(saveDirectory, fileName + ".pdf");
	            Files.move(savePath, newFilePath, StandardCopyOption.REPLACE_EXISTING);
	            savePath = newFilePath;
	        }
	        
	        return true;
		} catch (IOException e) {
			logger.info("downloadPDF() {}", e);
			return false;
		}			
    }

	public List<FeedMessage> readPages(List<FeedMessage> feeds) {
		List<FeedMessage> feedMessages = new ArrayList<>();
		for(FeedMessage feed: feeds) {
			Boolean flag = false;
	        try {
	            PdfReader reader = new PdfReader(feed.getPdfPath().toString());
	            try (PdfDocument pdfDoc = new PdfDocument(reader)) {
					int pcount = pdfDoc.getNumberOfPages();
					for (int i = 1; i <= pcount; i++) {
					    String content = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(i)).toLowerCase();
					    if (content.contains(StringConstants.SEARCH_1) || content.contains(StringConstants.SEARCH_2) || content.contains(StringConstants.SEARCH_3) || content.contains(StringConstants.SEARCH_4)) {
					        PdfContent pdfcontent = new PdfContent(i, content);
					        feed.addPdfContent(pdfcontent);
					        flag = true;
					    }
					}
				}
	            if(flag)
	            	feedMessages.add(feed);
	        } catch (IOException ex) {
	            throw new RuntimeException(ex);
	        }
		}
		
		return feedMessages;
	}
}
