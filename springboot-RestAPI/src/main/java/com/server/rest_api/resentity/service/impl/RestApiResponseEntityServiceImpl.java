package com.server.rest_api.resentity.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.server.common.FileProperties;
import com.server.rest_api.exception.FileUploadException;
import com.server.rest_api.resentity.service.RestApiResponseEntityService;

@Service
public class RestApiResponseEntityServiceImpl implements RestApiResponseEntityService {

	
	private final Path downloadPath;
	
	private final Path uploadPath;
	
	@Autowired
	public RestApiResponseEntityServiceImpl(FileProperties prop) {
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
	public String storeFile(MultipartFile file) throws Exception {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        
        try {
            // ���ϸ� ������ ���ڰ� �ִ��� Ȯ���Ѵ�.
            if(fileName.contains(".."))
                throw new FileUploadException("���ϸ� ������ ���ڰ� ���ԵǾ� �ֽ��ϴ�. " + fileName);
            
            Path targetLocation = this.uploadPath.resolve(fileName);
            
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            
            return fileName;
        }catch(Exception e) {
            throw new FileUploadException("["+fileName+"] ���� ���ε忡 �����Ͽ����ϴ�. �ٽ� �õ��Ͻʽÿ�.",e);
        }
	}
	@Override
	public Map<String, Object> SendImageFile(String fileName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource LoadFileAsResource(String fileName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File LoadFileAsByte(String fileName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
