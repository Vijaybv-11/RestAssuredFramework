package api.FakeRestAPI;

import api.Utilities.FilePaths;
import api.Utilities.JSONUtil;
import api.Utilities.RequestSpecificationUtil;
import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.testng.Assert;

import static api.FakeRestAPI.EndPoints.getAuthorBook;
import static api.FakeRestAPI.EndPoints.getAuthors;

public class Authors {

    public static String baseURL = EndPoints.baseURL, jsonSchema, firstName, lastName;
    public static Faker faker = new Faker();
    public static int authorId, authorBookId;


    public static void createAuthor() {
        authorId = faker.number().numberBetween(100, 120);
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        authorBookId = faker.book().hashCode();
        JsonObject payload = new JsonObject();
        payload.addProperty("id", authorId);
        payload.addProperty("idBook", authorBookId);
        payload.addProperty("firstName", firstName);
        payload.addProperty("lastName", lastName);
        jsonSchema = JSONUtil.getSchemaAsString(FilePaths.CREATE_AUTHORS_SCHEMA);
        Response response = RequestSpecificationUtil.performPost(baseURL, getAuthors, null, payload.toString(), null, 200, jsonSchema);
        Assert.assertEquals(firstName, response.jsonPath().getString("firstName"));
        Assert.assertEquals(lastName, response.jsonPath().getString("lastName"));
    }

    public static void getAuthor(int authorId) {
        jsonSchema = JSONUtil.getSchemaAsString(FilePaths.CREATE_AUTHORS_SCHEMA);
        Response response = RequestSpecificationUtil.performGet(baseURL, getAuthors + authorId, null, null, jsonSchema, 200);
        assert response.jsonPath().getString("firstName").contains("First Name");
        assert response.jsonPath().getString("lastName").contains("Last Name");
    }


    public static void getAuthorBooks(int bookId) {
        jsonSchema = JSONUtil.getSchemaAsString(FilePaths.CREATE_AUTHORS_SCHEMA);
        Response response = RequestSpecificationUtil.performGet(baseURL, getAuthorBook + bookId, null, null, jsonSchema, 200);
       assert response.jsonPath().getString("firstName").contains("First Name");
       assert response.jsonPath().getString("lastName").contains("Last Name");
    }

    public static void main(String[] args) {
        createAuthor();
        getAuthor(authorId);
        getAuthorBooks(authorBookId);
    }
}
