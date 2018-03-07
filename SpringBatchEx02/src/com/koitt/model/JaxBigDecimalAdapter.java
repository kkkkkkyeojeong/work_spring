package com.koitt.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class JaxBigDecimalAdapter extends XmlAdapter<String, BigDecimal> {

	// unmarshal: xml 데이터를 vo로 변환 
	@Override
	public BigDecimal unmarshal(String v) throws Exception {
		// 값에 포함된 콤파(,)를 제거
		return new BigDecimal(v.replaceAll(",", ""));
	}

	// marshal: vo를 xml 데이터로 변환
	@Override
	public String marshal(BigDecimal v) throws Exception {
		return v.toString();
	}
	
	

}
