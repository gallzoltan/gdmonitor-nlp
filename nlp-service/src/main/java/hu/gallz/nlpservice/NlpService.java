package hu.gallz.nlpservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class NlpService {
	
	private static final Logger logger = LoggerFactory.getLogger(NlpService.class);
	
	public List<String> makeSummary(String content) {
		List<String> results = null;
		ProcessBuilder processBuilder = new ProcessBuilder("python", resolvePythonScriptPath("nlp_summary.py"));
		processBuilder.redirectErrorStream(true);
		try {
			Process process = processBuilder.start();
			results = readProcessOutput(process.getInputStream());
		} catch (IOException e) {
			logger.error("Error when the python script is running : {}", e);
		}
		return results;
	}
	
	private String resolvePythonScriptPath(String filename) {
        File file = new File("src/main/resources/python/" + filename);
        return file.getAbsolutePath();
    }
	
	private List<String> readProcessOutput(InputStream inputStream) throws IOException {
        try (BufferedReader output = new BufferedReader(new InputStreamReader(inputStream))) {
            return output.lines().collect(Collectors.toList());
        }
    }
}
