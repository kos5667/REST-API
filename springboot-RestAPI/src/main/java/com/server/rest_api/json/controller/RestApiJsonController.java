package com.server.rest_api.json.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.rest_api.common.ResponseModel;
import com.server.rest_api.json.service.RestApiJsonService;
import com.server.rest_api.json.vo.CodeType;

@RestController
public class RestApiJsonController {
	
	private static final Logger log = LoggerFactory.getLogger(RestApiJsonController.class);

	@Autowired
	private RestApiJsonService restApiJsonService ;
	
	@GetMapping(value = "/reception_test", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object Reception_Test(@RequestParam String fileName) throws Exception {
		ResponseModel<Map<String, Object>> responseModel = new ResponseModel<>(); 
		responseModel.setResult(restApiJsonService.SendImageFile(fileName));
		responseModel.setCode(CodeType.SUCCESS.getCode());
		responseModel.setMessage(CodeType.SUCCESS.getMessage());
		return responseModel;  
	}
	
	@PostMapping(value = "/okhttp_post_test")
	public Object OkHttp_Post_Test(@RequestBody Map<String, Object> body) throws Exception {
		
		log.debug("body : {}", body);
		
		return "sucess";
	}
	
}
