package com.techm.cadt.pact;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Configuration
@Component
public class ConsumerPort {

	private String url;
	private RestTemplate restTemplate;
	public ConsumerPort() {
		
	}
	@Autowired
	public ConsumerPort(String url) {
		this.url = url;	
		System.out.println("printing url-------"+url);
		this.restTemplate = new RestTemplate();
	}
	
	
	public String getEmployeeDetails()
	{
		ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>(){};
		String res =restTemplate.exchange(url + "/employee",HttpMethod.GET,null,responseType).getBody();
		return res;
	}
	
	
}
