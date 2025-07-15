package api.testing.seprateImplementation.tests;

import api.testing.seprateImplementation.base.BaseAPI;
import api.testing.seprateImplementation.specs.RequestSpec;
import api.testing.seprateImplementation.specs.ResponseSpec;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUserTest extends BaseAPI {
    @Test
    public void creatUser() {
        String requestBody = "{ \"name\": \"Daljeet\", \"job\": \"QA Architect\" }";
        Response response = given()
                .spec(RequestSpec.getRequestSpec())
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .spec(ResponseSpec.getResponseSpec201())
                .extract()
                .response();
        System.out.println(" Response: " + response.asPrettyString());
        Assert.assertEquals(response.jsonPath().getString("name"), "Daljeet");
        Assert.assertEquals(response.jsonPath().getString("job"), "QA Architect");
        Assert.assertEquals(response.getStatusCode(), 201);
    }
}
