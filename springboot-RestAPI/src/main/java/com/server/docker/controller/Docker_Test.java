package com.server.docker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Docker_Test {

	private static final Logger log = LoggerFactory.getLogger(Docker_Test.class);

	@GetMapping(value = "/docker-test1-get")
	public String Docker_Test1_Get() throws Exception {
		log.debug("Docker_Test1_Get");
		return "sucess";
	}
	
	@GetMapping(value = "/docker-test2-get")
	public String Docker_Test2_Get() throws Exception {
		log.debug("Docker_Test2_Get");
		return "sucess";
	}
	
	@PostMapping(value = "/docker-test2-post")
	public String Docker_Test1_Post() throws Exception {
		log.debug("Docker_Test1_Post");
		return "sucess";
	}
	
	@PostMapping(value = "/docker-test2-post")
	public String Docker_Test2_Post() throws Exception {
		log.debug("Docker_Test2_Post");
		return "sucess";
	}
}
