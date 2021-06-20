package com.server.oldland.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.server.common.CompressionUtil;
import com.server.common.FileIOUtil;
import com.server.oldland.FileType;
import com.server.oldland.vo.OldLandVO;

@RestController
@RequestMapping(value = "/ZipFile")
public class ZipFile {

	private static final Logger log = LoggerFactory.getLogger(ZipFile.class);
	
	private static final String ZipFilePath = "S:\\workspace\\springboot-RestAPI\\src\\main\\webapp\\resources\\img\\zipFiles\\";
	
	private static final String UnZipFilePath = "S:\\workspace\\springboot-RestAPI\\src\\main\\webapp\\resources\\img\\unzip\\";
	
	private static final String TestFile = "12_330_120_34_2_0002.tif";
	
	private static final int OldLand_Property_Size = 3;
	
	/**
     * Size of the buffer to read/write data
     */
    private static final int BUFFER_SIZE = 4096;
	
	/**
	 * Zip => Un Zip
	 * get Files Name in Zip
	 * 
	 * 1. zip 파일 등록
	 * 2. zip 파일 저장
	 * 3. zip 파일 unzip(폴터 생성 => 날짜 => zip file 명 => 해제
	 * 3. zip 파일
	 * 
	 * 확장자 체크 
	 */
	@RequestMapping(value = "/unzip", method = {RequestMethod.GET, RequestMethod.POST})
	public List<OldLandVO> getFilesNameInZip() throws Exception {
		log.debug("Start ZipFile unzip Test...");
		
		FileIOUtil fileIOUtil = new FileIOUtil();
		CompressionUtil util = new CompressionUtil();
		
		String FilePath = ZipFilePath + TestFile;
		
		//Path Validation Check.
		log.debug("Checking the existence of folders.");
		if(!fileIOUtil.exists(new File(FilePath))) {
			
			log.error("The file does not exist in the path.");
			throw new Exception();
			//fileIOUtil.createFolder("", false);
		}
		
		
		//File Type Validation Check.
		log.info("Validating FileType...");
		String extension = ValidationCheck_FileType(FilePath);
		
		
		//UNZIP.
		log.info("unzip...");
		if(FileType.ZIP.toString().equalsIgnoreCase(extension)) {
			
			util.unzip(new File(FilePath), new File(UnZipFilePath));
		}
		
		
		//Single or Multiple Separations.
		log.info("Validating File Names...");
		List<String> FileNames = new ArrayList<>();
		
		//Multiple.
		if(FileType.ZIP.toString().equalsIgnoreCase(extension)) {
			FileNames = getFileNames(FilePath);
		}
		//Single.
		else {
			FileNames.add(new File(FilePath).getName());
		}
		
		
		//File Names Validation Check.
		log.info("Validating File Names...");
		List<OldLandVO> oldLandProperty = ValidationCheck_FileNames(FileNames);
       
		System.gc();
		
		return oldLandProperty;
	}
	
    /**
     * 파일 확장자 유효성 검사
     * 지원 확장자 zip, jpg, png, tif
     * 
     * @param filePath
     * @return String 
     */
    public String ValidationCheck_FileType(String filePath) {
    	
		String FileName = new File(filePath).getName();
		String extension = FilenameUtils.getExtension(FileName);
		
		try {
			if(!(FileType.ZIP.toString().equalsIgnoreCase(extension) ||
				 FileType.JPG.toString().equalsIgnoreCase(extension) ||
				 FileType.PNG.toString().equalsIgnoreCase(extension) ||
				 FileType.TIF.toString().equalsIgnoreCase(extension) ))
			{
				throw new Exception();
			}
		} catch (Exception e) {
			log.error("The file type is not supported.");
			e.printStackTrace();
		}
		
		return extension;
    }
    
    
    /**
     * 파일이름 형식
     * ex) 00_000_000_00_0_0000_0000_0_0000.tif
     * 
     * @param String filePath
     * @throws Exception
     */
    public List<OldLandVO> ValidationCheck_FileNames(List<String> fileName) throws Exception {
    	
    	List<OldLandVO> resultList = new ArrayList<OldLandVO>();
    	
    	for(String item : fileName) {
    		
    		OldLandVO vo = new OldLandVO();
    		String[] items = item.substring(0, item.lastIndexOf(".")).split("_");
    		
    		if(items.length < OldLand_Property_Size) {
    			log.error("There is a problem with the file name.");
    			throw new Exception();
    		}
    		
    		try {
    		
				vo.setSido(items[0]);
				vo.setSigungu(items[1]);
				vo.setLidongdo(items[2]);
				
				resultList.add(vo);
				
			} catch (Exception e) {
				
				log.error("There is a problem with the code.");
				e.printStackTrace();
			}
    	}
    	
    	return resultList;
    }
    
    
    /**
     * ZIP 내부 파일이름 추출
     * 
     * @param filePath
     * @return
     */
    public List<String> getFileNames(String filePath) {
    	
    	List<String> FileNames = new ArrayList<>();
    	
    	FileInputStream fileInputStream = null;
    	ZipInputStream zipInputStream = null;
    	ZipEntry zipEntry = null;
    	
    	try {
    		fileInputStream = new FileInputStream(filePath);
    		
    		zipInputStream = new ZipInputStream(new BufferedInputStream(fileInputStream));
    		
    		while((zipEntry = zipInputStream.getNextEntry()) != null){
    			//log.debug("ZipEntry Get File Names : {}", zipEntry.getName());
    			FileNames.add(zipEntry.getName());
	        }
	        
	        zipInputStream.close();
    		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return FileNames;
    }
	
}
