package hu.gallz.appservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.gallz.appservice.model.Decree;

@ExtendWith(MockitoExtension.class)
public class GovernmentDecreeServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(GovernmentDecreeServiceTest.class);
	private String f;
	private int p;

	@InjectMocks
	private GovernmentDecreeService governmentDecreeService;
		
	@BeforeEach 
    void init() {
		//f = "/home/gallz/Letöltések/MK_23_075.pdf";
		
//		f = "c:\\Users\\gallz\\Downloads\\MK_23_078.pdf";
//		p = 119;
		
//		f = "c:\\Users\\gallz\\Downloads\\MK_23_028.pdf";
//		p = 35;
		
		//f = "c:\\Users\\gallz\\Downloads\\MK_23_076.pdf";
		//p = 79;
		
		f = "c:\\Users\\gallz\\Downloads\\MK_23_067.pdf";
		p = 27;
	}
	
	@Disabled("TODO: Still need to work on it")
	@Test
	public void testThatFoundDecreeNumber() {		
		Path path = Paths.get(f);
		String result = governmentDecreeService.extractGovernmentDecree(path.toFile(), p);
		logger.info("Decree number: {}", result);
		
		assertEquals(result, "A Kormány 1207/2023. (V. 25.) Korm. határozata");
	}
	
	@Disabled
	@Test
	public void testDecreeService() {
		Path path = Paths.get(f);
		List<Decree> result = governmentDecreeService.extractDecreesFromPDF(path.toFile()); 
		logger.info("Decrees: {}", result);
	}
	
	@Disabled("TODO: Still need to work on it")
	@Test
	public void testThatPdfContentKeyWords() {			
		Path path = Paths.get(f);
		Set<Integer> setA = new HashSet<Integer>();
		setA.add(79);
		//setA.add(41);
		logger.info("setA: {}", setA.toString());
		assertEquals(governmentDecreeService.findKeywords(path.toFile()), setA);
	}
}
