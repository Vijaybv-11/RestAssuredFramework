package api.EndPoints;

import static io.restassured.RestAssured.*;

import api.payLoad.UserPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response createUser(UserPayload Payload) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(Payload)

				.when().post(Routes.post_Url);

		return response;

	}

	public static Response getUser(String userName) {

		Response response = given().pathParam("username", userName)

				.when().get(Routes.get_Url);

		return response;

	}

	public static Response UpdateUser(UserPayload Payload, String Username) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(Payload)
				.pathParam("username", Username)

				.when().put(Routes.Put_Url);

		return response;

	}

	public static Response deleteUser(String username) {

		Response response = given().pathParam("username", username)

				.when().delete(Routes.Delete_Url);

		return response;

	}

}
