package com.behere.common.enums;

public enum GuidTypeEnum {
	
	ArrestStandard("1","逮捕标准指引"),
	EvidenceList("2","基本证据清单"),
	StandardOfEvidence("3","证据标准指引"),
	RulesOfEvidence("4","证据规则指引"),
	EvidenceChain("5","证据链指引");
	
	private String type;
	private String des;
	
	GuidTypeEnum(String type,String des){
		this.type = type;
		this.des = des;
	}

	public String getType() {
		return type;
	}

	public String getDes() {
		return des;
	}

}
