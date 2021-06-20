package com.server.rest_api.resentity.service;

import java.io.File;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface RestApiResponseEntityService {

	public String storeFile(MultipartFile file) throws Exception;
	
	public Map<String, Object> SendImageFile(String fileName) throws Exception;
	
	public Resource LoadFileAsResource(String fileName) throws Exception;
	
	public File LoadFileAsByte(String fileName) throws Exception;
	
}
