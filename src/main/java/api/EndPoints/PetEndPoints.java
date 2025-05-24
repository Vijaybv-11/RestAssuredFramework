package api.EndPoints;

import static io.restassured.RestAssured.given;

import api.payLoad.PetPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndPoints {

	public static Response createPet(PetPayload payload) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(Routes.petPostURl);
		return response;
	}

	public static Response uploadPet(int petId, String filePath) {

		Response response = given().multiPart("file", filePath).contentType(ContentType.MULTIPART)
				.pathParam("petId", petId).when().post(Routes.petPostUploadImageURl);
		return response;
	}

	public static Response getPet(int petId) {

		Response response = given().pathParam("petId", petId).when().get(Routes.petgetURl);
		return response;
	}

	public static Response deletePet(int petId) {

		Response response = given().pathParam("petId", petId).when().delete(Routes.petgetURl);
		return response;
	}

}
