package hu.gallz.appservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GovernmentDecreeServiceTest {

	@InjectMocks
	private GovernmentDecreeService governmentDecreeService;
	
	@Test
	public void testThatPdfReadIsOK() {
		Path path = Paths.get("/home/gallz/Letöltések/MK_23_075.pdf");
		assertEquals(governmentDecreeService.extractGovernmentDecree(path.toFile()), "29");
	}
	
	@Test
	public void testThatPdfContentKeyWords() {
		Path path = Paths.get("/home/gallz/Letöltések/MK_23_075.pdf");
		Set<Integer> setA = new HashSet<Integer>();
		setA.add(13);
		assertEquals(governmentDecreeService.findKeywords(path.toFile()), setA);
	}
}
