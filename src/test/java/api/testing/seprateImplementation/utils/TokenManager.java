package api.testing.seprateImplementation.utils;

import api.testing.seprateImplementation.specs.RequestSpec;
import api.testing.seprateImplementation.specs.ResponseSpec;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TokenManager {

    public static String getToken() {
        String loginPayload = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        Response response = RestAssured
                .given()
                .spec(RequestSpec.getRequestSpec())
                .body(loginPayload)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .spec(ResponseSpec.getResponseSpec200())
                .extract().response();

        return response.jsonPath().getString("token");
    }
}