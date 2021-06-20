package com.server.rest_api.resentity.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.rest_api.exception.FileUploadException;
import com.server.rest_api.resentity.service.RestApiResponseEntityService;

@RestController
public class RestApi_ResponseEntity_Controller {
	
	private static final Logger log = LoggerFactory.getLogger(RestApi_ResponseEntity_Controller.class);

	@Autowired
	private RestApiResponseEntityService restApiResponseEntityService;
	
	@GetMapping(value = "/upload")
	public String uploadFile() throws FileUploadException {
		
		return "";
	}
	
	
	/**
	 * ResponseEntity File Download
	 * 
	 * @param fileName
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/download_res")
	public ResponseEntity<Resource> Send_File(@RequestParam String fileName, HttpServletRequest request) throws Exception {
		log.debug("Start Download File...");
		log.debug("File Name : {}", fileName);
		
		// Load file as Resource
		Resource resource = restApiResponseEntityService.LoadFileAsResource(fileName);
		
		// Try to determine file's content type 
		String contentType = null;
		
		
//		byte[] fileContent = FileUtils.readFileToByteArray(resource.getFile());
//		log.debug("Java Before Encode : {}", fileContent.length);
//		//Base 64 Encode file
//		byte[] encodeFile = new Base64_JavaUtil().Base64_Encode(fileContent);
//		new Base64_JavaUtil().Base64_ED(fileContent); 
		
		try {
            contentType = request.getServletContext()
            					 .getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }
		
        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        
        log.debug("ContentType : {}", contentType);
        
//        new Base64_JavaUtil().Base64_ED(resource.getFilename());
//        new Base64_ApacheCodec().Base64_ED(resource.getFilename());
        
		return ResponseEntity.ok()
							 .contentType(MediaType.parseMediaType(contentType))
							 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				             .body(resource);
	}
	
	@GetMapping(value = "/download_byte/{fileName}")
	public ResponseEntity<byte[]> DownloadFile(@PathVariable String fileName, HttpServletRequest request) throws Exception {
		log.debug("Start Download File...");
		log.debug("File Name : {}", fileName);
		
		//
//		File file = responseEntityService.LoadFileAsByte(fileName);
		File file = null;
		
		byte[] fileContent = FileUtils.readFileToByteArray(file);
		
		//Base 64 Encode file
//		byte[] encodeFile = new Base64_JavaUtil().Base64_Encode(fileContent);
		
		// Try to determine file's content type 
		String contentType = null;
		
		try {
            contentType = request.getServletContext()
            					 .getMimeType(file.getAbsolutePath());
        } catch (Exception ex) {
            log.info("Could not determine file type.");
        }
		
		 // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
		
        log.debug("ContentType : {}", contentType);
        
        return ResponseEntity.ok()
				 			 .contentType(MediaType.parseMediaType(contentType))
				 			 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
				 			 .body(fileContent);
	}
	
}
