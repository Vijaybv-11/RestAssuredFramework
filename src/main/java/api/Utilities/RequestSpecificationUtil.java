package api.Utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;



public class RequestSpecificationUtil {

    private static RequestSpecification baseSpec;

    public static RequestSpecification getBaseSpec() {
        if (baseSpec == null) {
            synchronized (RequestSpecification.class) {
                if (baseSpec == null) {
                    baseSpec = new RequestSpecBuilder().setContentType(ContentType.JSON).addHeader("Accept", "application/json")
                            .setRelaxedHTTPSValidation()
                            .setConfig(RestAssured.config().httpClient(HttpClientConfig.httpClientConfig().setParam("http.connection.timeout", 5000).setParam("http.socket.timeout", 5000).setParam("http.connection-manager.timeout", 5000L))).build();
                }
            }
        }
        return baseSpec;
    }

    private static void addSafeHeaders(RequestSpecBuilder builder, Map<String, String> headers) {
        if (headers != null && !headers.isEmpty()) {
            builder.addHeaders(headers);
        }
    }

    private static void addSafeQueryParams(RequestSpecBuilder builder, Map<String, String> queryParams) {
        if (queryParams != null && !queryParams.isEmpty()) {
            builder.addQueryParams(queryParams);
        }
    }

    private static void addSafeBody(RequestSpecBuilder builder, String payload) {
        if (payload != null && !payload.isEmpty()) {
            builder.setBody(payload);
        }
    }


    public static RequestSpecification getJsonRequestSpec(String baseURL, Map<String, String> headers, String payload, Map<String, String> queryParams) {
        RequestSpecification base = getBaseSpec();
        RequestSpecBuilder builder = new RequestSpecBuilder().addRequestSpecification(base).setBaseUri(baseURL);
        addSafeHeaders(builder, headers);
        addSafeQueryParams(builder, queryParams);
        addSafeBody(builder, payload);
        return builder.build();

    }

    // Form Data Request
    public static RequestSpecification getFormRequestSpec(String baseURL, Map<String, String> headers, Map<String, String> formParams, Map<String, String> queryParams, String payload) {
        RequestSpecification base = getBaseSpec();
        RequestSpecBuilder builder = new RequestSpecBuilder().addRequestSpecification(base).setBaseUri(baseURL);
        builder.addFormParams(formParams);
        addSafeHeaders(builder, headers);
        addSafeQueryParams(builder, queryParams);
        addSafeBody(builder, payload);
        return builder.build();
    }

    // Multipart Request (without file for now)
    public static RequestSpecification getMultipartRequestSpec(String baseURL, Map<String, String> headers, Map<String, String> queryParams) {
        RequestSpecification base = getBaseSpec();
        RequestSpecBuilder builder = new RequestSpecBuilder().addRequestSpecification(base).setBaseUri(baseURL);
        addSafeHeaders(builder, headers);
        addSafeQueryParams(builder, queryParams);
        builder.setContentType(ContentType.MULTIPART);
        return builder.build();

    }

    // GET Request
    public static RequestSpecification getGetRequestSpec(String baseURL, Map<String, String> headers, Map<String, String> queryParams) {
        RequestSpecification base = getBaseSpec();
        RequestSpecBuilder builder = new RequestSpecBuilder().addRequestSpecification(base).setBaseUri(baseURL);
        addSafeHeaders(builder, headers);
        addSafeQueryParams(builder, queryParams);
        return builder.build();
    }


    public static Response performPost(String baseUrl, String endPoint,Map<String, String> headers, String payload, Map<String, String> queryParams, int statusCode, String jsonSchema) {
        RequestSpecification requestSpecification = getJsonRequestSpec(baseUrl, headers, payload, queryParams);
        Response response = RestAssured.given(requestSpecification).post(endPoint);
        response.then().log().all();
        response.then().statusCode(statusCode);
        response.then().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
        return response;
    }

    public static Response performGet(String baseUrl,String endpoint, Map<String, String> headers, Map<String, String> queryParams, String jsonSchema, int statusCode) {
        RequestSpecification requestSpecification = getGetRequestSpec(baseUrl, headers, queryParams);
        Response response = RestAssured.given(requestSpecification).get(endpoint);
        response.then().log().all();
        response.then().assertThat().statusCode(statusCode);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
        return response;
    }


    public static Response performDelete(String baseUrl,String endPoint, Map<String, String> headers, String payload, Map<String, String> queryParams, int statusCode) {
        RequestSpecification requestSpecification = getJsonRequestSpec(baseUrl, headers, payload, queryParams);
        Response response = RestAssured.given(requestSpecification).delete(endPoint);
        response.then().log().all();
        response.then().statusCode(statusCode);
        return response;
    }

    public static Response performPut(String baseUrl,String endPoint, Map<String, String> headers, String payload, Map<String, String> queryParams, int statusCode, String jsonSchema) {
        RequestSpecification requestSpecification = getJsonRequestSpec(baseUrl, headers, payload, queryParams);
        Response response = RestAssured.given(requestSpecification).put(endPoint);
        response.then().log().all();
        response.then().statusCode(statusCode);
        response.then().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
        return response;
    }


}
