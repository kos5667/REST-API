package com.server.rest_api.common;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Base64_JavaUtil {
	
	private static final Logger log = LoggerFactory.getLogger(Base64_JavaUtil.class);

	//Test
	public void Base64_ED(String data) throws Exception {
			
		byte[] byteTemp = data.getBytes();
		
		//Java Util Base64 Encode
		Encoder encoder = Base64.getEncoder();
		byte[] encodedBytes = encoder.encode(byteTemp);
		
		//Java Util Base64 Decode
		Decoder decoder = Base64.getDecoder();
		byte[] decodedBytes = decoder.decode(encodedBytes);
		
		String decodeString = new String(decodedBytes);
		
		log.debug("Java Before Encode : {}", byteTemp);
		log.debug("Java After Encode : {}", encodedBytes);
		log.debug("Java After Decode : {}", decodedBytes);
		log.debug("Java After Decode String : {}", decodeString);
	}
	
	//File Byte encode to String
	public String Base64_Encode_To_String(byte[] file) throws Exception {
		String resultString = null;
		
		try {
			
			resultString = Base64.getEncoder().encodeToString(file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}

	//File Byte encode to String
	public byte[] Base64_Encode_To_Byte(byte[] file) throws Exception {
		byte[] resultFile = null;
		
		try {
			//Java Util Base64 Encode
			Encoder encoder = Base64.getEncoder();
			resultFile = encoder.encode(file);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultFile;
	}
	
	public byte[] Base64_Decode(byte[] file) throws Exception {
		byte[] resultFile = null;
		
		try {
			Decoder decoder = Base64.getDecoder();
			resultFile = decoder.decode(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultFile;
	}
}
