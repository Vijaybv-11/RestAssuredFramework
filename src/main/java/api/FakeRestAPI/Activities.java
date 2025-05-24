package api.FakeRestAPI;

import api.Utilities.FilePaths;
import api.Utilities.JSONUtil;
import api.Utilities.RequestSpecificationUtil;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static api.FakeRestAPI.EndPoints.baseURL;
import static api.FakeRestAPI.EndPoints.getActivities;

public class Activities {

    private static final String baseUrl = baseURL;
    private static String jsonSchema, title, payload;
    public static int statusCode, id;
    public static Faker faker = new Faker();


    public static void createActivities() {
        title = faker.artist().name();
        id = faker.number().numberBetween(100, 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("title", title);
        statusCode = 200;
        payload = JSONUtil.parseJson(FilePaths.CREATE_ACTIVITY_PAYLOAD, map);
        jsonSchema = JSONUtil.getSchemaAsString(FilePaths.CREATE_ACTIVITY_SCHEMA);
        Response response = RequestSpecificationUtil.performPost(baseUrl, getActivities, null, payload, null, statusCode, jsonSchema);
        Assert.assertEquals(response.jsonPath().getInt("id"), id);
        Assert.assertEquals(response.jsonPath().getString("title"), title);
    }

    public static void getActivity(int activityId) {
        jsonSchema = JSONUtil.getSchemaAsString(FilePaths.CREATE_ACTIVITY_SCHEMA);
        Response response = RequestSpecificationUtil.performGet(baseUrl, getActivities + activityId, new HashMap<>(), new HashMap<>(), jsonSchema, 404);
        Assert.assertEquals(response.jsonPath().getString("title"), "Not Found");

    }

    public static void updateActivity(int activityId) {
        title = faker.crypto().md5();
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("title", title);
        payload = JSONUtil.parseJson(FilePaths.CREATE_ACTIVITY_PAYLOAD, map);
        jsonSchema = JSONUtil.getSchemaAsString(FilePaths.CREATE_ACTIVITY_SCHEMA);
        Response response = RequestSpecificationUtil.performPut(baseUrl, getActivities + activityId, null, payload, null, 200, jsonSchema);
        Assert.assertEquals(response.jsonPath().getString("title"), title);

    }

    public static void deleteActivity(int activityId) {
        RequestSpecificationUtil.performDelete(baseUrl, getActivities + activityId, null, payload, null, 200);
    }


    public static void getAllActivities() {
        jsonSchema = JSONUtil.getSchemaAsString(FilePaths.GET_ACTIVITY_SCHEMA);
        Response response = RequestSpecificationUtil.performGet(baseUrl, getActivities, new HashMap<>(), new HashMap<>(), jsonSchema, 200);

    }

}
