package com.server.leadtools.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import leadtools.LTLibrary;
import leadtools.LeadtoolsException;
import leadtools.Platform;
import leadtools.RasterImage;
import leadtools.RasterImageFormat;
import leadtools.RasterSupport;
import leadtools.codecs.RasterCodecs;

@RestController
@RequestMapping(value = "/LEADTOOLS")
public class LeadToolsRestController {

	private static final Logger logger = LoggerFactory.getLogger(LeadToolsRestController.class);
	
	private static final String LibPath = "C:\\LEADTOOLS21\\Bin\\CDLL\\x64";
	
	private static final String LicenseFile = "C:\\LEADTOOLS21\\Support\\Common\\License\\LEADTOOLS.LIC";
	
	private static final String DeveloperKey = "C:\\LEADTOOLS21\\Support\\Common\\License\\LEADTOOLS.LIC.KEY";
	
	private static final String FilePath = "S:\\workspace\\springboot-RestAPI\\src\\main\\webapp\\resources\\img\\convert";
	
	private static final String ConvertPath = "S:\\workspace\\springboot-RestAPI\\src\\main\\webapp\\resources\\img\\convert";
	

	/**
	 * - Image(JPG, PNG) => Tif
	 * - TIF => CMP
	 * - CMP => TIF
	 * 
	 * @param File Name
	 * @param File Types(Image(jpg, png), tif, cmp)
	 * @param File Types(Image(jpg, png), tif, cmp)
	 * @param Save Path(Image(jpg, png), tif, cmp)
	 * @param File Path(Image(jpg, png), tif, cmp)
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/convert", method = {RequestMethod.GET, RequestMethod.POST})
	public String LEADTOOLS_CONVERT(String role, String fileName, String filePath, String savePath) throws Exception {
		logger.debug("Start LEADTOOLS Image Convert.");
		
		Platform.setLibPath(LibPath);
		Platform.loadLibrary(LTLibrary.LEADTOOLS);
		Platform.loadLibrary(LTLibrary.CODECS);
		
		//File IO ±¸Ãà
		SetLicense();
		
		RasterCodecs codecs = new RasterCodecs();
		
		//TIF -> CMP
//		String inputFile = "C:\\LEADTOOLS21\\Resources\\Images\\cmc7.jpg";
//		String outputFile = "D:\\01.workspace\\brain.cmp";
		
		//CMP -> Tir
//		String inputFile = "C:\\LEADTOOLS21\\Resources\\Images\\redeye.cmp";
//		String outputFile = "D:\\01.workspace\\redeye.tif";
		
		//Image(JPG, PNG) -> Tif
//		String inputFile = "C:\\LEADTOOLS21\\Resources\\Images\\image2.jpg"; 
		String inputFile = "C:\\LEADTOOLS21\\Resources\\Images\\littlegflyingalpha.png"; 
		String outputFile = ConvertPath+"\\test";
		
		RasterImage image = LoadImage(inputFile, codecs); 
		
		if("insert".equals("dd")) {
			
		}
		
		else if("update".equals("dd")) {
			
		}
		
		
//		SaveImage(image, outputFile, codecs);
		SaveImageToTif(image, outputFile, codecs);
//		SaveImageToCmp(image, outputFile, codecs); 
//		SaveCmpToTif(image, outputFile, codecs); 

		JSONObject json = new JSONObject();
		
		json.put("success", true);
		json.put("fail", true);
		
		return json.toString(4);
	}
	
	static void SetLicense() throws IOException {
		String licenseFile 	= LicenseFile; 
		String developerKey = Files.readString(Paths.get(DeveloperKey)); 
		
		RasterSupport.setLicense(licenseFile, developerKey);
		
		if(RasterSupport.getKernelExpired()) 
			System.out.println("License file invalid or expired."); 
		else 
			System.out.println("License file set successfully."); 
	}
	
	static RasterImage LoadImage(String inputFile, RasterCodecs codecs){ 
		RasterImage _image = codecs.load(inputFile); 
		System.out.println("Image loaded successfully."); 
		return _image; 
	}
	
	static void SaveImage(RasterImage _image, String outputFile, RasterCodecs _codecs) {
		_codecs.save(_image, outputFile, RasterImageFormat.TIF, _image.getBitsPerPixel()); 
		System.out.println("Image saved successfully."); 
	}
	
	static void SaveImageToCmp(RasterImage _image, String outputFile, RasterCodecs _codecs) { 
		_codecs.save(_image, outputFile, RasterImageFormat.CMP, _image.getBitsPerPixel()); 
		System.out.println("Image saved successfully."); 
	}
	
	static void SaveImageToTif(RasterImage _image, String outputFile, RasterCodecs _codecs) { 
		_codecs.save(_image, outputFile, RasterImageFormat.TIF, _image.getBitsPerPixel()); 
		System.out.println("Image saved successfully."); 
	}
	
	static void SaveCmpToTif(RasterImage _image, String outputFile, RasterCodecs _codecs) { 
		_codecs.save(_image, outputFile, RasterImageFormat.TIF, _image.getBitsPerPixel()); 
		System.out.println("Image saved successfully."); 
	}
	
	public String FileRename(String _fileNm, String _fileTypes) throws Exception {
		String CMP = ".CMP";
		String TIF = ".TIF";
		
		int type = _fileNm.lastIndexOf(".");
		String fileNm = _fileNm.substring(type);
		
		if("tif".equals(_fileTypes)) {
			fileNm += TIF;
		} 
		
		else if("cmp".equals(_fileTypes)) {
			fileNm += CMP;
		}
		
		return fileNm;
	}
	
}
