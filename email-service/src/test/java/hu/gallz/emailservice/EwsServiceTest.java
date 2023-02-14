package hu.gallz.emailservice;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hu.gallz.configuration.GdMonitorConfig;

//@ExtendWith(SpringExtension.class)
//@EnableConfigurationProperties(value = GdMonitorConfig.class)
//@TestPropertySource("classpath:application.properties")
public class EwsServiceTest {
	
	@Autowired
	GdMonitorConfig config;
	
	@Test
	public void testGetEnvironmentVariables() throws Exception {
//		System.out.println(config.getUsername());
//		assertThat(config.getUsername() == "zoltan.gall@bm.gov.hu");
	}
}
