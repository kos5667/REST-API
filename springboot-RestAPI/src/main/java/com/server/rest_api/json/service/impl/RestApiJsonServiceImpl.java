package com.server.rest_api.json.service.impl;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.server.common.FileProperties;
import com.server.rest_api.exception.FileDownloadException;
import com.server.rest_api.exception.FileUploadException;
import com.server.rest_api.json.service.RestApiJsonService;

@Service
public class RestApiJsonServiceImpl implements RestApiJsonService {
	
	private static final Logger log = LoggerFactory.getLogger(RestApiJsonServiceImpl.class);

	private final Path downloadPath;
	
	private final Path uploadPath;
	
	@Autowired
	public RestApiJsonServiceImpl(FileProperties prop) {
		this.downloadPath = Paths.get(prop.getDownloadPath())
							 	 .toAbsolutePath()
							 	 .normalize();
		
		this.uploadPath = Paths.get(prop.getUploadPath())
			 	 			   .toAbsolutePath()
			 	 			   .normalize();
		
		try {
			Files.createDirectories(this.downloadPath);
		} catch (Exception e) {
			throw new FileUploadException("������ ���ε��� ���丮�� �������� ���߽��ϴ�.", e);
		}
	}
	
	@Override
	public Map<String, Object> SendImageFile(String fileName) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			//Get Resource
			Resource resource = new UrlResource(this.downloadPath
													.resolve(fileName)
													.normalize()
													.toUri());
			
			//Resource Exists
			if(!resource.exists()) {
				throw new FileDownloadException(fileName + " not found...");
			}
			
			//File to byte[]
			byte[] fileContent = FileUtils.readFileToByteArray(resource.getFile());
			
			//byte[] to Base64 String
			String byteString = Base64.encodeBase64String(fileContent);
			
			resultMap.put("data1", 123);
			resultMap.put("data2", "test");
			resultMap.put("file", byteString);
		} catch (MalformedURLException e) {
			throw new FileDownloadException(fileName + " not found...", e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}


	

}
