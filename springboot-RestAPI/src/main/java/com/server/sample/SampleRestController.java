package com.server.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleRestController {

	@GetMapping(value = "/message")
	public String LEADTOOLS_CONVET() throws Exception {
		String message = "Sample RestAPI TEST...";
		return message;
	}
	
}
