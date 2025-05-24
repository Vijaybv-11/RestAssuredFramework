package api.UserTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.EndPoints.UserEndPoints;
import api.Utilities.Dataprovider;
import api.payLoad.UserPayload;
import io.restassured.response.Response;

public class DataproviderTest {

	UserPayload payload;

	@BeforeClass

	public void createData() {

	}

	@Test(priority = 1, dataProvider = "UserData", dataProviderClass = Dataprovider.class)
	public void createUser(String id, String username, String firstname, String lastname, String email, String password,
			String phone, String userstatus) {
		System.out.println(
				"-------------------------------------------------*Create User*--------------------------------------------------------------------------------------------");
		payload = new UserPayload();
		int userid = Integer.parseInt(id);
		payload.setId(userid);
		payload.setUsername(username);
		payload.setFirstname(firstname);
		payload.setLastname(lastname);
		payload.setPassword(password);
		payload.setPhone(phone);
		payload.setEmail(email);

		Response response = UserEndPoints.createUser(payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 2, dataProvider = "userNames", dataProviderClass = Dataprovider.class)
	public void deleteuser(String userName) {

		System.out.println(
				"-------------------------------------------------*Delete User*--------------------------------------------------------------------------------------------");

		Response response = UserEndPoints.deleteUser(userName);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);

	}
}
