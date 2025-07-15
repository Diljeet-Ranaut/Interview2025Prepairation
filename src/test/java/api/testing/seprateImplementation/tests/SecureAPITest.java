package api.testing.seprateImplementation.tests;


import api.testing.seprateImplementation.specs.RequestSpec;
import api.testing.seprateImplementation.specs.ResponseSpec;
import api.testing.seprateImplementation.utils.TokenManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SecureAPITest {

    @Test
    public void accessProtectedResource() {
        String token = TokenManager.getToken();
        System.out.println(STR."My Token:\{token}");
        Response response = RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://reqres.in/api/users/2")  // sample endpoint
                .then()
                .spec(ResponseSpec.getResponseSpec200())
                .extract().response();

        System.out.println("Secure GET Response:\n" + response.asPrettyString());
        assertEquals(response.jsonPath().getString("data.id"), "2");
    }
}