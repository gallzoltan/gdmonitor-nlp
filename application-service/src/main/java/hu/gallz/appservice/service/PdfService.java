package hu.gallz.appservice.service;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hu.gallz.appservice.model.FeedMessage;

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

	public Set<Integer> readPages(List<FeedMessage> feeds) {
		for(FeedMessage feed: feeds) {
			feed.getPdfPath();
		}
		return null;
	}
}
