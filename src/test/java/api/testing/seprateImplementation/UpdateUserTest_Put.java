package api.testing.seprateImplementation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateUserTest_Put {
    @Test
    public void updateUser() {
        String payload = "{ \"name\": \"Daljeet\", \"job\": \"QA Lead\" }";
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .body(payload)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .extract()
                .response();
        System.out.println("Put Response Body :" + response.asPrettyString());
        Assert.assertEquals(response.jsonPath().getString("name"), "Daljeet");
        Assert.assertEquals(response.jsonPath().getString("job"), "QA Lead");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.asString().contains("updatedAt"));

    }
}
