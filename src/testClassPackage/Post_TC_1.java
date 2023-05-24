package testClassPackage;

import java.io.IOException;

import java.time.LocalDate;

import org.testng.Assert;

import commonFunctionPackage.API_Common_Function;
import commonFunctionPackage.utility_Common_Function;
import io.restassured.path.json.JsonPath;
import requestRepositoryPackage.Post_req_repository;

public class Post_TC_1 {
	
	public static void execute() throws IOException {
		
		for(int i=0;i<5;i++) {
			String baseURI=Post_req_repository.post_baseURI();
			String resource=Post_req_repository.post_resource();
			String requestBody=Post_req_repository.post_request1();
			
			int statusCode=API_Common_Function.res_statusCode(baseURI, requestBody, resource);
			
			if (statusCode==201) {
				String responseBody=API_Common_Function.post_responseBody(baseURI, requestBody, resource);
				System.out.println(responseBody);
				Post_TC_1.validator(requestBody, responseBody, statusCode);
				utility_Common_Function.evidencecreator("Post_TC_1", requestBody, responseBody);
				break;
			}
			else
			{
				System.out.println("correct status code not found hense retrying");
			}
			
		}
	}
		
		public static void validator (String requestBody,String responseBody,int statusCode) {
		
		JsonPath jspreq=new JsonPath(requestBody);
		String req_name=jspreq.getString("name");
		String req_job=jspreq.getString("job");
		String currentdate=LocalDate.now().toString();
		
		JsonPath jspres=new JsonPath(responseBody);
		String res_name=jspres.getString("name");
		String res_job=jspres.getString("job");
		String res_id=jspres.getString("id");
		String res_createdAt=jspres.getString("createdAt");
		
		Assert.assertEquals(statusCode, 201);
		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertNotNull(res_id);
		Assert.assertEquals(res_createdAt.substring(0,10),currentdate);
	}

}
