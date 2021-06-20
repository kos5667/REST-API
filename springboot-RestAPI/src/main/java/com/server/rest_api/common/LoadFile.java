package com.server.rest_api.common;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import com.server.rest_api.exception.FileDownloadException;

public class LoadFile {
	
	private static final Logger log = LoggerFactory.getLogger(LoadFile.class);
	
	public Resource LoadFileAsResource(Path path, String file) throws Exception {
		log.debug("Start Load File As Resource...");
		try {
			Path filePath = path.resolve(file).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			
			if(resource.exists()) {
				return resource;
			}else {
				throw new FileDownloadException(file + " not found...");
			}
		} catch (MalformedURLException e) {
			throw new FileDownloadException(file + " not found...", e);
		}
	}
	
	public File LoadFileAsByte(Path path, String fileName) throws Exception {
		log.debug("Start Load File As Byte[]...");
		
		byte[] fileContent = null;
		File file = null;
		
		try {
			file = path.resolve(fileName).normalize().toFile();
			
//			fileContent = FileUtils.readFileToByteArray(file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file;
	}
}
