package api.EndPoints;

import static io.restassured.RestAssured.given;

import api.payLoad.StorePayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndPoints {

	public static Response createStore(StorePayload payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)

				.when().post(Routes.storePostURl);

		return response;
	}
	

	public static Response getStore(int id) {
		Response response = given()
				.pathParam("orderId",id)
				.when().get(Routes.storeGetURl);

		return response;
	}
	
	
	public static Response deleteStore(int id) {
		Response response = given()
				.pathParam("orderId",id)
				.when().delete(Routes.storeDeleteURl);

		return response;
	}
	
}
