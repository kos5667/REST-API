package com.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.server.common.FileProperties;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableConfigurationProperties({FileProperties.class})
public class Application_RestAPI {

	private static final Logger logger = LoggerFactory.getLogger(Application_RestAPI.class);

	public static void main(String[] args) {
		SpringApplication.run(Application_RestAPI.class, args);
	}

//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		logger.info("Start REST API server...");
//		
//		Iterator<String> iterator = args.getOptionNames().iterator();
//		while (iterator.hasNext()) {
//			String key = iterator.next();
//			Object value = args.getOptionValues(key);
//			
//			logger.info("{} = {}", key, value);
//		}
//	}

}
