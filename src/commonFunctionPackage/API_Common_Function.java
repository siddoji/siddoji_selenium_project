package commonFunctionPackage;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class API_Common_Function {
	
	public static int res_statusCode(String baseURI,String requestBody,String resource) {
		
		RestAssured.baseURI=baseURI;
		
		int statusCode=given().header("Content-Type","application/json").body(requestBody).when().post(resource).then().extract().statusCode();
		return statusCode;
	}
	
	public static String post_responseBody(String baseURI,String requestBody,String resource) {
		
		RestAssured.baseURI=baseURI;
		
		String responseBody=given().header("Content-Type","application/json").body(requestBody).when().post(resource).then().extract().response().asString();
		return responseBody;
		
		
	}

}
