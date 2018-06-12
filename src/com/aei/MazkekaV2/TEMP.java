package com.aei.MazkekaV2;

public enum TEMP{
	WARMING("Warming"),
	METHANOL("Methanol"),
	ETHANOL("Ethanol"),
	TAILS("Tails"),
	FINISH("Finish");
	
	private String str;
	
	TEMP(String str){
		this.str = str;
	}
	
	public String str() {
		return this.str;
	}
}
