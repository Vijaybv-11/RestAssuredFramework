package api.petTest;

import java.util.Arrays;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.EndPoints.PetEndPoints;
import api.payLoad.PetPayload;
import io.restassured.response.Response;

public class petTest {

	Faker faker;
	PetPayload payload;
	PetPayload.Category category; 
	PetPayload.Tag tag;

	@BeforeClass
	void createData() {
		faker = new Faker();
		payload = new PetPayload();
		category= new PetPayload.Category();
		tag = new PetPayload.Tag();
		
		
		  payload.setId(faker.number().hashCode());
		 
		 category.setId(faker.number().randomNumber());
		 category.setStoreName(faker.funnyName().toString());
		 payload.setName(faker.animal().name());
		 payload.setPhotoUrls(Arrays.asList(faker.internet().emailAddress()));
		 tag.setId(faker.random().nextLong());
		 tag.setName(faker.name().firstName());
		  category.setStoreName(faker.nation().capitalCity());
		  payload.setStatus("available"); 
		 
	}

	@Test(priority = 1)
	void createPet() {

		System.out.println(
				"---------------------------------------------------------------* Create Pet *---------------------------------------------------------------------------------------");
		Response response = PetEndPoints.createPet(payload);
		response.then().log().body();

	}
	
	@Test(priority = 2)
	void uploadPetImage() {
		String filePath ="G:\\English\\tenses.pdf";
		System.out.println(
				"---------------------------------------------------------------* Upload Pet Image *---------------------------------------------------------------------------------------");
		Response response = PetEndPoints.uploadPet(payload.getId(),filePath);
		response.then().log().body();
		response.then().statusCode(200);
		
	}
	
	@Test(priority = 3)
	void GetPet() {
		System.out.println(
				"---------------------------------------------------------------* Get Pet *---------------------------------------------------------------------------------------");
		
		Response response = PetEndPoints.getPet(payload.getId());
		String photourl =response.jsonPath().get("photoUrls[0]");
		response.then().body("photoUrls[0]", equalTo(photourl));
		response.then().statusCode(200);
		response.then().log().body();
		
	}
	
	@Test(priority = 4)
	
	void deletePet() {
		System.out.println(
				"---------------------------------------------------------------* Delete Pet *---------------------------------------------------------------------------------------");
		
		Response response = PetEndPoints.deletePet(payload.getId());
		
		response.then().statusCode(200);
		
		
	}

}
