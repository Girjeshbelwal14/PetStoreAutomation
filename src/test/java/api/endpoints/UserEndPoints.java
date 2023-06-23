package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import api.payload.User;

//user endpoints.java
//created to perform Create, Read, Update, Delete(CRUD) requests to the user API


public class UserEndPoints {
	
	public static Response createUser(User payload)
	{
		Response res = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(Routes.post_url);
		
		return res;
	}
	
	
	public static Response readUser(String userName)
	{
		Response res = given()
		.pathParam("username", userName)		
		.when()
		.get(Routes.get_url);
		
		return res;
	}
	
	public static Response updateUser(String userName, User payload)
	{
		Response res = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", userName)
		
		.when()
		.put(Routes.update_url);
		
		return res;
	}
	
	public static Response deleteUser(String userName)
	{
		Response res = given()
		.pathParam("username", userName)		
		.when()
		.delete(Routes.delete_url);
		
		return res;
	}

}
