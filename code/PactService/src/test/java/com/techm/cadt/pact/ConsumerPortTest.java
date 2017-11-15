package com.techm.cadt.pact;

import au.com.dius.pact.consumer.*;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import net.sf.json.test.JSONAssert;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/*import java.net.URI;
 import java.util.ArrayList;
 import java.util.Arrays;*/
import java.util.HashMap;
import java.util.Map;


public class ConsumerPortTest {
	double salary=3000.1;
	@Autowired
	ConsumerPort c;

	@Rule
	public PactProviderRule rule = new PactProviderRule("EmployeeDetails_Provider",this);

	                     
	@Pact(provider = "EmployeeDetails_Provider", consumer = "Mock_Consumer")
	public PactFragment createFragment(PactDslWithProvider builder) {

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json;charset=UTF-8");
		
		return builder
				
				.uponReceiving("a request")
				.path("/employee")
				//.headers(headers)
				.method("GET")
				.willRespondWith()
				.headers(headers)
				.status(200)
				.body("{\"empId\":\"1\",\"name\":\"emp1\",\"designation\":\"manager\","
						+ "\"salary\":"+salary+"}")
				.toFragment();

	}

	@SuppressWarnings("deprecation")
	@Test
	@PactVerification(value = "EmployeeDetails_Provider", fragment = "createFragment")
	public void runTest() {

		try {			
            /*System.out.println("rule.getConfig().url()---"+rule.getConfig().url());*/
			JSONAssert
					.assertEquals(
							new ConsumerPort(rule.getConfig().url()).getEmployeeDetails(),
							"{\"empId\":\"1\",\"name\":\"emp1\",\"designation\":\"manager\","
									+ "\"salary\":"+salary+"}");

		} catch (Exception e) {
			
			/*System.out.println("rule.getConfig().url()---"+rule.getConfig().url());*/
			
			System.out.println("Caught Exception");
		}

	}

	

	                           
         
}
