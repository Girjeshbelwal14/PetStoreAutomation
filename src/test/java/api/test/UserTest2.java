package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest2 {
	
	Faker faker;
	User userPayload;
	
	public Logger logger;
	@BeforeClass
	public void setup()
	{
		faker = new Faker();
		userPayload =new User();
		userPayload.setId(faker.idNumber().hashCode());  //Here hashcode is used to generate random id
//	Here we are generating the id using faker and passing it to payload by calling setId method
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		
		logger = LogManager.getLogger(this.getClass());
		logger.debug("debugging**************");
	}

	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("******************Creating User***************");
		Response res = UserEndPoints2.createUser(userPayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("**********User info is displayed*******");
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("************************Getting user info***************************");
		Response res = UserEndPoints2.readUser(this.userPayload.getUsername());
		
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("****************user info getted by get*****************");
}
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		// Update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		logger.info("************************Updating user info***************************");
		Response res = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		res.then().log().all();
// 		res.then().log().body().statusCode(200);
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("************************user updated***************************");

		// checking data after updation
		
 Response resAfter = UserEndPoints2.readUser(this.userPayload.getUsername());
		
		resAfter.then().log().all();
		Assert.assertEquals(resAfter.getStatusCode(), 200);
	}
	
	
	@Test(priority=4)
	public void deleteUserByName()
	{
		logger.info("************************deleting user info***************************");

		Response res = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("***********************user deleted***************************");
 
	}
}


