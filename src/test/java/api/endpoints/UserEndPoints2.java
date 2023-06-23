package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;

//user endpoints.java
//created to perform Create, Read, Update, Delete(CRUD) requests to the user API


public class UserEndPoints2 {
	
	//Method created for getting urls from properties xml file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //Load the properties file of xml type. routes in"" s file name
		return routes;
	}
	
	
	public static Response createUser(User payload)
	{
		String post_url = getURL().getString("post_url"); //Calling url
		
		Response res = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(post_url);
		
		return res;
	}
	
	
	public static Response readUser(String userName)
	{
		String get_url = getURL().getString("get_url");
		Response res = given()
		.pathParam("username", userName)		
		.when()
		.get(get_url);
		
		return res;
	}
	
	public static Response updateUser(String userName, User payload)
	{
		String update_url = getURL().getString("update_url");

		Response res = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", userName)
		
		.when()
		.put(update_url);
		
		return res;
	}
	
	public static Response deleteUser(String userName)
	{
		String delete_url = getURL().getString("delete_url");

		Response res = given()
		.pathParam("username", userName)		
		.when()
		.delete(delete_url);
		
		return res;
	}

}
