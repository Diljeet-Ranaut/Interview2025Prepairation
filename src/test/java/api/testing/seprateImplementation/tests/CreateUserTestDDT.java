package api.testing.seprateImplementation.tests;

import api.testing.seprateImplementation.base.BaseAPI;
import api.testing.seprateImplementation.specs.RequestSpec;
import api.testing.seprateImplementation.specs.ResponseSpec;
import api.testing.seprateImplementation.utils.JsonReader;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreateUserTestDDT extends BaseAPI {

    @Test(dataProvider = "userData", dataProviderClass = JsonReader.class)
    public void createUser(String name, String job) {
        String payload = String.format("{\"name\":\"%s\", \"job\":\"%s\"}", name, job);

        Response response = given()
                .spec(RequestSpec.getRequestSpec())
                .body(payload)
                .post("/api/users")
                .then()
                .spec(ResponseSpec.getResponseSpec201())
                .extract().response();

        System.out.println("Response: " + response.asPrettyString());

        assertEquals(response.jsonPath().getString("name"), name);
        assertEquals(response.jsonPath().getString("job"), job);
    }
}
