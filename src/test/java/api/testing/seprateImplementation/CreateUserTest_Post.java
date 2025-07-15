package api.testing.seprateImplementation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserTest_Post {
    String requestBody = "{ \"name\": \"Daljeet\", \"job\": \"QA Lead\" }";

    @Test
    public void createUser() {
        Response response = RestAssured
                .given()
                .header("x-api-key", "reqres-free-v1")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .extract().response();
        System.out.println("Post Body Response:" + response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertTrue(response.asString().contains("Daljeet"));
    }


}
