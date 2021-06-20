package com.server.oldland.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OldLandVO {
	
	private static final Logger log = LoggerFactory.getLogger(OldLandVO.class);

	private final int SIDO_CODE_SIZE = 2;
	
	private final int SIGUNGU_CODE_SIZE = 3;
	
	/**
	 * 구토지대장 파일 속성
	 */
	private String sido;
	private String sigungu;
	private String lidongdo;

	
	//Constructor
	public OldLandVO() {
		super();
	}
	
	
	//Method
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) throws Exception {
		
		if(sido.length() != SIDO_CODE_SIZE) {
			log.error("The Sido Code Error.");
			throw new Exception();
		}
		else {
			this.sido = sido;
		}
	}
	
	public String getSigungu() {
		return sigungu;
	}
	public void setSigungu(String sigungu) throws Exception {
		
		if(sigungu.length() != SIGUNGU_CODE_SIZE) {
			log.error("The Sigungu Code Error.");
			throw new Exception();
		}
		else {
			this.sigungu = sigungu;
		}
	}
	
	public String getLidongdo() {
		return lidongdo;
	}
	public void setLidongdo(String lidongdo) {
		this.lidongdo = lidongdo;
	}
	
}
