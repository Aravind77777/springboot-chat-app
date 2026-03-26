package com.example.aiproject.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsService {
	
	
	private final String apiKey;
	
	public SmsService(@Value("${fast2sms.api.key}") String apiKey) {
		this.apiKey=apiKey;
		
	}
	
	public void sendOtp(String phone, String otp) {
		RestTemplate rt =  new RestTemplate();
		
		String url = "https://www.fast2sms.com/dev/bulkV2"
				+ "?authorization=" + apiKey
				+ "&route=otp"
				+ "&variables_value=" + otp
				+ "&number=" + phone;
		rt.getForObject(url, String.class);
	}

}
