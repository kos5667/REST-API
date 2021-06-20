package com.server.rest_api.common;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Base64_ApacheCodec {
	
	private static final Logger log = LoggerFactory.getLogger(Base64_ApacheCodec.class);

	public void Base64_ED(String data) throws Exception {
			
		byte[] byteTemp = data.getBytes();
		
		//Apache Commons Codec Base64 Encode
		byte[] encodedBytes = Base64.encodeBase64(byteTemp);
		
		//Apache Commons Codec Base64 Decode
		byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
		
		log.debug("Apache Before Encode : {}", byteTemp);
		log.debug("Apache After Encode : {}", encodedBytes);
		log.debug("Apache After Decode : {}", decodedBytes);
	}
	
	public void Base64_ED(byte[] byteTemp) throws Exception {
		
		//Apache Commons Codec Base64 Encode
		byte[] encodedBytes = Base64.encodeBase64(byteTemp);
		
		//Apache Commons Codec Base64 Decode
		byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
		
		log.debug("Apache Before Encode : {}", byteTemp);
		log.debug("Apache After Encode : {}", encodedBytes);
		log.debug("Apache After Decode : {}", decodedBytes);
	}
}
