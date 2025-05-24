package api.UserTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.EndPoints.UserEndPoints;
import api.payLoad.UserPayload;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	UserPayload payload;
	
	public Logger logger;

	@BeforeClass
	public void createData() {

		faker = new Faker();
		payload = new UserPayload();

		payload.setId(faker.idNumber().hashCode());
		payload.setUsername(faker.name().username());
		payload.setFirstname(faker.name().firstName());
		payload.setLastname(faker.name().lastName());
		payload.setPassword(faker.internet().password(5, 10));
		payload.setPhone(faker.phoneNumber().cellPhone());
		payload.setEmail(faker.internet().safeEmailAddress());
		logger=LogManager.getLogger(this.getClass());
	}

	@Test(priority = 1)
	public void createUser() {
logger.info("Creating User");
		Response response = UserEndPoints.createUser(payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("User Created");
		System.out.println(
				"-------------------------------------------------*GetUser*--------------------------------------------------------------------------------------------");
	}

	@Test(priority = 2,dependsOnMethods = {"createUser"})
	public void getUser() {

		Response response = UserEndPoints.getUser(payload.getUsername());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().get("username"), payload.getUsername());
		System.out.println(
				"-------------------------------------------------*Updateuser*--------------------------------------------------------------------------------------------");

	}

	@Test(priority = 3)
	public void updateUser() {

		payload.setEmail(faker.internet().emailAddress());
		payload.setPhone(faker.phoneNumber().cellPhone());
		Response response = UserEndPoints.UpdateUser(payload, payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		// Validation after Update
		Response responseAfterUpdate = UserEndPoints.getUser(payload.getUsername());
		Assert.assertEquals(responseAfterUpdate.jsonPath().get("email"), payload.getEmail());
		Assert.assertEquals(responseAfterUpdate.jsonPath().get("phone"), payload.getPhone());

		System.out.println(
				"-------------------------------------------------*DeleteUser*--------------------------------------------------------------------------------------------");

	}

	@Test(priority = 4)
	public void deleteUser() {
		

		Response response = UserEndPoints.deleteUser(payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println(
				"-------------------------------------------------*End of the Execution*--------------------------------------------------------------------------------------------");
	}

}
