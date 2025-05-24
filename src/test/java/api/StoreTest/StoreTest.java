package api.StoreTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.EndPoints.StoreEndPoints;
import api.payLoad.StorePayload;
import io.restassured.response.Response;

public class StoreTest {

	Faker faker;
	StorePayload store;

	@BeforeClass
	void createData() {
		faker = new Faker();
		store = new StorePayload();
		store.setId(faker.number().randomDigit());
		store.setPetId(faker.number().numberBetween(1, 5));
		store.setQuantity(faker.number().randomDigit());
		store.setShipdate(faker.date().toString());
		store.setStatus("placed");
		store.setComplete(faker.bool().bool());
	}

	@Test(priority = 1)
	void placeorder() {

		System.out.println(
				"----------------------------------------------------------* Create Store *-----------------------------------------------------------------------------------");
		Response response = StoreEndPoints.createStore(store);

		response.jsonPath().get("status").equals("placed");
		Assert.assertEquals(response.getStatusCode(), 200);
		response.then().log().body();

	}

	@Test(priority = 2)
	void getOrder() {

		System.out.println(
				"----------------------------------------------------------* Get Store *-----------------------------------------------------------------------------------");
		Response response = StoreEndPoints.getStore(store.getId());

		Assert.assertEquals(response.getStatusCode(), 200);
		response.then().log().body();
	}

	@Test(priority = 3)
	void deleteOrder() {

		System.out.println(
				"----------------------------------------------------------* Delete Store *-----------------------------------------------------------------------------------");
		Response response = StoreEndPoints.deleteStore(store.getId());
		Assert.assertEquals(response.getStatusCode(), 200);
		response.then().log().all();
	}
}
